package com.gag.enterprisewxmobile.tool.util;

import com.gag.enterprisewxmobile.tool.common.*;
import com.gag.enterprisewxmobile.tool.common.utils.*;
import com.gag.enterprisewxmobile.tool.excel.ExcelController;
import com.gag.enterprisewxmobile.tool.gen.entity.ColumnInfo;
import com.gag.enterprisewxmobile.tool.gen.entity.TableInfo;
import com.gag.enterprisewxmobile.tool.gen.service.GenService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.*;

@Controller
@RequestMapping("CglibTest")
@Slf4j
public class CglibTest extends BaseController {

    @Resource
    private GenService genService;


    /**
     * 根据传入的表明动态生成Bean，属性和类型，并返回bean
     * @param response
     * @param tablename
     * @return
     * @throws Exception
     */
    public CglibBean getCglibBean(HttpServletResponse response, String tablename,@Nullable List<ColumnInfo> columns){
        //表的信息
        TableInfo tableInfo = this.genService.selectTableByName(tablename);
        if (columns==null){
            //查询表的列信息
            columns = this.genService.selectTableColumnsByName(tablename);
        }
        // 修改表列类型为java类型
        tableInfo.setColumns(GenUtils.transColums(columns));
        List<ColumnInfo> list = tableInfo.getColumns();
        // 设置类成员属性,使用Treemap将自动排序，使用HasMap只会出现一个相同的key
        Map propertyMap = new LinkedHashMap();
        //将列的属性名和列的类型添加到propertyMap中
        for (int i=0;i<list.size();i++){
            try {
                propertyMap.put(list.get(i).getColumnName(),Class.forName(list.get(i).getAttrType()));
            } catch (ClassNotFoundException e) {
                log.error("数据库类型转换java类型失败："+e);
                e.printStackTrace();
            }
        }

        // 生成动态 Bean,className是要生成动态的Bean的源路径,propertyMap是要生成动态bean的属性和类型
        CglibBean bean = new CglibBean("com.gag.enterprisewxmobile.tool.util."+tablename,propertyMap);

        return bean;
    }

    public CglibBean getCglibBean2(HttpServletResponse response, String tablename,@Nullable List<ColumnInfo> columns){
        //表的信息
        TableInfo tableInfo = this.genService.selectTableBySelectName(tablename);
        if (columns==null){
            //查询表的列信息
            columns = genService.selectTableCoulumnsBySelectName(tablename);
        }
        // 修改表列类型为java类型
        tableInfo.setColumns(GenUtils.transColums(columns));
        List<ColumnInfo> list = tableInfo.getColumns();
        // 设置类成员属性,使用Treemap将自动排序，使用HasMap只会出现一个相同的key
        Map propertyMap = new LinkedHashMap();
        //将列的属性名和列的类型添加到propertyMap中
        for (int i=0;i<list.size();i++){
            try {
                propertyMap.put(list.get(i).getColumnName(),Class.forName(list.get(i).getAttrType()));
            } catch (ClassNotFoundException e) {
                log.error("数据库类型转换java类型失败："+e);
                e.printStackTrace();
            }
        }

        // 生成动态 Bean,className是要生成动态的Bean的源路径,propertyMap是要生成动态bean的属性和类型
        CglibBean bean = new CglibBean("com.gag.enterprisewxmobile.tool.util."+tablename,propertyMap);

        return bean;
    }


    /**
     * 导出数据模板
     */
    @Log(title = "商品销售", businessType = BusinessType.EXPORT)
    @RequestMapping("exportExcel/{tableName}")
    @ResponseBody
    public void exportExcel(@PathVariable("tableName") String tableName,HttpServletResponse response){
        //获取动态生成的bean
        CglibBean bean = getCglibBean(response,tableName,null);
        //需要进行导出的数据集
        LinkedList<LinkedHashMap> list = genService.selectmseg("dbo."+tableName);
        //将需要进行导出的数据集进行泛型转换,list是需要导出的数据的集合,bean.getObject().getClass()是动态生成bean的.class
        LinkedList lis = new ExponseConstants().parse(list, bean.getObject().getClass());
        List<ColumnInfo> columns = this.genService.selectTableColumnsByName(tableName);
        /**
         * listBeanGeneratorObj  需要导出的数据的集合
         * classname 动态生成的bean.class
         * filename 生成的excel文件名
         */
        ExcelController.export(response,lis,columns,new Date().getTime()+tableName);
    }


    /**
     * 导入数据模板
     * @param file
     * @param response
     * @return
     */
    @Log(title = "商品销售", businessType = BusinessType.IMPORT)
    @RequestMapping("importExcel")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public Boolean importExcel(@RequestParam("file") MultipartFile file, HttpServletResponse response){
        boolean boo = false;
        //设置表名
        String tablename = "shop_pos";
        ExcelCustomCommon excelCustomCommon = new ExcelCustomCommon();
        //为了保证读取的数据是从第二行开始，避免读取表头
        try {
            // @RequestParam("file") MultipartFile file 是用来接收前端传递过来的文件
            // 1.创建workbook对象，读取整个文档
            InputStream inputStream = file.getInputStream();
            Workbook xb = excelCustomCommon.getWorkbookType(inputStream, file.getOriginalFilename());

            // 2.读取页脚sheet
            XSSFSheet sheetAt = (XSSFSheet) xb.getSheetAt(0);
            //获取动态生成的bean
            CglibBean bean = getCglibBean(response,tablename,null);
            Object object = bean.getObject();
            //通过反射获取bean的类
            Field[] fields = object.getClass().getDeclaredFields();
            //获取属性名并赋值给str数组，当做变量名使用
            String[] str = new String[fields.length];
            for (int j=0;j<str.length;j++){
                str[j] = fields[j].getName();
            }
            //通过反射获取所有的方法,并且把方法名存到数组中，然后调用set方法存值
            Method[] ms =  object.getClass().getMethods();
            String[] stri = new String[fields.length];
            int fie=-1;
            for (int g=0;g<ms.length;g++){
                String methedname = ms[g].getName();
                if (methedname.contains("set")){
                    stri[++fie] = methedname;
                }
            }
            List<ColumnInfo> columns = this.genService.selectTableColumnsByName(tablename);
            //循环读取excel文件从第二行开始
            for (int i=1;i<sheetAt.getPhysicalNumberOfRows();i++){
                Row row = sheetAt.getRow(i);
                for (int k=0;k<str.length;k++){
                    Cell cell = row.getCell(k);
                    String cellFormatValue = excelCustomCommon.getCellFormatValue(cell);
                    if (columns.get(k).getAttrType().equals("java.util.Date")){
                        if (columns.get(k).getDataType().equals("date")){
                            bean.setValue(columns.get(k).getColumnName(),excelCustomCommon.TransformDate(cellFormatValue));
                        }
                    } else if (columns.get(k).getAttrType().equals("java.lang.String")){
                        bean.setValue(columns.get(k).getColumnName(),excelCustomCommon.StrIfTransformInt(cellFormatValue));
                    }else if (columns.get(k).getAttrType().equals("java.lang.Integer")){
                        bean.setValue(columns.get(k).getColumnName(),new Integer(String.valueOf(excelCustomCommon.ifExistSuffix(cellFormatValue))));
                    }else if (columns.get(k).getAttrType().equals("java.math.BigDecimal")){
                        bean.setValue(columns.get(k).getColumnName(), BigDecimal.valueOf(Double.parseDouble("".equals(cellFormatValue)?"0.00":cellFormatValue)));
                    }
                }
                //调用添加数据的方法
                int insertmsg = this.genService.insertmsg(bean.beanMap);
                if (insertmsg>0){
                    boo = true;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            boo = false;
        }finally {
            if (boo){
                //获取上传文件路径
                String filepath = FileUploadUtils.getDefaultBaseDir();
                //每一个月创建一个新文件夹,获取文件夹名称
                String date = DateUtils.getDate();
                String fipath = filepath+date+"/";
                //创建一个新文件夹
                FileUploadUtils.mkdir(fipath);
                try {
                    //上传文件
                    FileUploadUtils.upload(fipath, file);
                } catch (IOException e) {
                    e.printStackTrace();
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    boo = false;
                }
            }
            return boo;
        }
    }

}
