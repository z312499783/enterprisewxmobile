package com.gag.enterprisewxmobile.system.kaSystem.controller;

import com.gag.enterprisewxmobile.system.kaSystem.service.KaGagProductmsgService;
import com.gag.enterprisewxmobile.tool.common.*;
import com.gag.enterprisewxmobile.tool.common.utils.DateUtils;
import com.gag.enterprisewxmobile.tool.common.utils.ExcelCustomCommon;
import com.gag.enterprisewxmobile.tool.common.utils.ExponseConstants;
import com.gag.enterprisewxmobile.tool.common.utils.FileUploadUtils;
import com.gag.enterprisewxmobile.tool.excel.ExcelController;
import com.gag.enterprisewxmobile.tool.gen.entity.ColumnInfo;
import com.gag.enterprisewxmobile.tool.gen.service.GenService;
import com.gag.enterprisewxmobile.tool.util.CglibBean;
import com.gag.enterprisewxmobile.tool.util.CglibTest;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.*;

/**
 * ka系统盛宝产品信息(KaGagProductmsg)表控制层
 *
 * @author makejava
 * @since 2020-11-02 13:36:19
 */
@Api(value = "盛宝产品信息")
@Controller
@RequestMapping("kaGagProductmsg")
public class KaGagProductmsgController extends BaseController{
    /**
     * 服务对象
     */
    @Resource
    private KaGagProductmsgService kaGagProductmsgService;

    @Resource
    private GenService genService;

    @Resource
    private CglibTest cglibTest;

    /**
     * 导出数据模板
     */
    @ApiOperation(value = "盛宝产品信息",notes = "根据表名导出盛宝产品信息，这是一个公共导出数据模板",response = void.class,httpMethod = "GET")
    @Log(title = "盛宝产品信息导出", businessType = BusinessType.EXPORT)
    @RequiresPermissions("kaGagProductmsg:select")
    @GetMapping("exportExcel/{tableName}")
    @ResponseBody
    public void exportExcel(@PathVariable("tableName") String tableName,HttpServletResponse response){
        //获取动态生成的bean
        CglibBean bean = cglibTest.getCglibBean(response,tableName,null);
        //需要进行导出的数据集
        LinkedList<LinkedHashMap> list = genService.selectmseg("dbo."+tableName);
        //将需要进行导出的数据集进行泛型转换,list是需要导出的数据的集合,bean.getObject().getClass()是动态生成bean的.class
        LinkedList lis = new ExponseConstants().parse(list, bean.getObject().getClass());
        /**
         * listBeanGeneratorObj  需要导出的数据的集合
         * classname 动态生成的bean.class
         * filename 生成的excel文件名
         */
        ExcelController.CustomHeadexport(response,lis,head(),new Date().getTime()+tableName);
    }

    /**
     * 导入数据模板
     * @param file
     * @param response
     * @return
     */
    @Log(title = "盛宝产品信息导入", businessType = BusinessType.IMPORT)
    @RequiresPermissions("kaGagProductmsg:insert")
    @PostMapping("importExcel")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    @ApiOperation(value = "盛宝产品信息导入",notes = "根据导入的文件导入数据到盛宝产品信息表",response = JSONResult.class,httpMethod = "POST")
    public JSONResult importExcel(@ApiParam(name = "file",value = "导入盛宝产品信息文件file",required = true)@RequestParam("file") MultipartFile file, HttpServletResponse response){
        boolean boo = false;
        //设置表名
        String tablename = "ka_GAG_productmsg";
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
            CglibBean bean = cglibTest.getCglibBean(response,tablename,null);
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
            for (int i=2;i<sheetAt.getPhysicalNumberOfRows();i++){
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
                int msg;
                if (this.kaGagProductmsgService.selectmsg((String) bean.beanMap.get("article_number"))>=1){
                    msg = this.kaGagProductmsgService.updatemsg(bean.beanMap);
                }else {
                    //调用添加数据的方法
                    msg = this.kaGagProductmsgService.insertmsg(bean.beanMap);
                }
                if (msg>0){
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
            return JSONResult.custom(boo,boo);
        }
    }


    /**
     * 自定义表头
     * @return
     */
    private static List <List<String>> head(){
        List<List<String>> headTitles = Lists.newArrayList();
        String length="长";
        String wide="宽";
        String high="高";
        String side="侧边";
        String[] str = {"序号","条码","MSG商品名称","产品名称","保质期","品类","品牌","子品牌","规格","品牌类别","零售指导售价","生产状态","外包装材质"};
        String[] str2 = {"箱装包数","箱装条码","产品说明"};
        //第一列，1/2行
        for (String headname:str) {
            headTitles.add( Lists.newArrayList(headname));
        }

        headTitles.add(Lists.newArrayList("单包包装尺寸",length));
        headTitles.add(Lists.newArrayList("单包包装尺寸",wide));
        headTitles.add(Lists.newArrayList("单包包装尺寸",side));
        headTitles.add(Lists.newArrayList("单包成品尺寸",length));
        headTitles.add(Lists.newArrayList("单包成品尺寸",wide));
        headTitles.add(Lists.newArrayList("单包成品尺寸",high));
        headTitles.add(Lists.newArrayList("箱装尺寸",length));
        headTitles.add(Lists.newArrayList("箱装尺寸",wide));
        headTitles.add(Lists.newArrayList("箱装尺寸",high));
        for (String headname:str2) {
            headTitles.add( Lists.newArrayList(headname));
        }
        return headTitles;
    }

}