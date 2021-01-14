package com.gag.enterprisewxmobile.system.kaSystem.controller;

import com.gag.enterprisewxmobile.system.kaSystem.entity.KaSystemTime;
import com.gag.enterprisewxmobile.system.kaSystem.entity.ShopPos;
import com.gag.enterprisewxmobile.system.kaSystem.service.ShopPosService;
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
 * 商品销售(ShopPos)表控制层
 *
 * @author makejava
 * @since 2020-11-02 15:59:49
 */
@Api(value = "shopPos商品销售查询")
@Controller
@RequestMapping("shopPos")
public class ShopPosController extends BaseController {
    /**
     * 服务对象
     */
    @Resource
    private ShopPosService shopPosService;

    @Resource
    private CglibTest cglibTest;

    @Resource
    private GenService genService;


    @GetMapping("select")
    @RequiresPermissions("shopPos:select")
    @ResponseBody
    @ApiOperation(value = "查询shoppos",notes = "根据时间查询shoppos",response = JSONResult.class,httpMethod = "GET")
    public JSONResult selectmseg(@ApiParam(name = "shopPos",value = "传shopPos对象,shopPos对象父类params属性参数startTime,endTime") ShopPos shopPos){
        List<LinkedHashMap> list = this.shopPosService.selectmseg(shopPos);
        return JSONResult.success(list);
    };

    /**
     * 导出数据模板
     */
    @Log(title = "商品销售", businessType = BusinessType.EXPORT)
    @GetMapping("exportExcel")
    @RequiresPermissions("shopPos:select")
    @ApiOperation(value = "导出shoppos",notes = "根据参数KaSystemTime起始结束时间条件调用存储过程",response = void.class,httpMethod = "GET")
    public void exportExcel(@ApiParam(name = "kaSystemTime",value = "根据参数调用KA_system_shop存储过程",example = "StartTime和EndTime") KaSystemTime kaSystemTime, HttpServletResponse response){
        List<ColumnInfo> columns = shopPosService.selectTableColumnsByName();
        //获取动态生成的bean
        CglibBean bean = cglibTest.getCglibBean(response,"shop_pos",columns);
        //需要进行导出的数据集
        LinkedList<LinkedHashMap> list = shopPosService.kaSystem(kaSystemTime);
        //将需要进行导出的数据集进行泛型转换,list是需要导出的数据的集合,bean.getObject().getClass()是动态生成bean的.class
        LinkedList lis = new ExponseConstants().parse(list, bean.getObject().getClass());
        /**
         * listBeanGeneratorObj  需要导出的数据的集合
         * classname 动态生成的bean.class
         * filename 生成的excel文件名
         */
        ExcelController.export(response, lis, columns, new Date().getTime() + "shop_pos");
    }

    /**
     * 导入数据模板
     * @param file
     * @param response
     * @return
     */
    @Log(title = "商品销售", businessType = BusinessType.IMPORT)
    @PostMapping("importExcel")
    @ResponseBody
    @ApiOperation(value = "导入shoppos",notes = "根据表格导入数据",response = JSONResult.class,httpMethod = "POST")
    @Transactional(rollbackFor = Exception.class)
    public JSONResult importExcel(@RequestParam("file") MultipartFile file, HttpServletResponse response){
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
            return JSONResult.custom(boo,boo);
        }
    }
}