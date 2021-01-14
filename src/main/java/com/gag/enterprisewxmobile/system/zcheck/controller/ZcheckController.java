package com.gag.enterprisewxmobile.system.zcheck.controller;

import com.gag.enterprisewxmobile.system.zcheck.entity.Zcheck;
import com.gag.enterprisewxmobile.system.zcheck.service.ZcheckService;
import com.gag.enterprisewxmobile.tool.common.BaseController;
import com.gag.enterprisewxmobile.tool.common.BusinessType;
import com.gag.enterprisewxmobile.tool.common.JSONResult;
import com.gag.enterprisewxmobile.tool.common.Log;
import com.gag.enterprisewxmobile.tool.excel.ExcelController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

import static com.gag.enterprisewxmobile.tool.common.JSONResult.success;

@Api(value = "msg报错信息")
@Controller
@RequestMapping("zcheck")
public class ZcheckController  extends BaseController {

    @Resource
    private ZcheckService zcheckService;

    @GetMapping("select")
    @RequiresPermissions("zcheck:select")
    @ResponseBody
    @ApiOperation(value = "查询msg报错信息",notes = "查询msg报错信息",response = JSONResult.class,httpMethod = "GET")
    public JSONResult select(){
        List<Zcheck> list = zcheckService.selectZcheck();
        return success(list);
    }

    /**
     * 导出数据模板
     */
    @Log(title = "zcheck导出", businessType = BusinessType.EXPORT)
    @GetMapping("exportExcel/{tableName}")
    @RequiresPermissions("zcheck:export")
    @ResponseBody
    @ApiOperation(value = "导出msg报错信息",notes = "导出msg报错信息",response = void.class,httpMethod = "GET")
    public void exportExcel(@ApiParam(required = true,name = "tableName",value = "传一个参数写表名(此处只用来导出文件命名)") @PathVariable("tableName") String tableName,
                            HttpServletResponse response){
        List<Zcheck> list = zcheckService.selectZcheck();
        /**
         * listBeanGeneratorObj  需要导出的数据的集合
         * classname 动态生成的bean.class
         * filename 生成的excel文件名
         */
        ExcelController.export(response,list,Zcheck.class,new Date().getTime()+tableName);
    }
}
