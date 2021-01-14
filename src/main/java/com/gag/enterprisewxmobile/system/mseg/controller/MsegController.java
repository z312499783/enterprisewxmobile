package com.gag.enterprisewxmobile.system.mseg.controller;

import com.gag.enterprisewxmobile.system.kaSystem.entity.KaSystemTime;
import com.gag.enterprisewxmobile.system.mseg.entity.Mseg;
import com.gag.enterprisewxmobile.system.mseg.service.MsegService;
import com.gag.enterprisewxmobile.tool.common.*;
import com.gag.enterprisewxmobile.tool.excel.ExcelController;
import com.gag.enterprisewxmobile.tool.gen.entity.ColumnInfo;
import com.gag.enterprisewxmobile.tool.gen.service.GenService;
import com.gag.enterprisewxmobile.tool.util.CglibBean;
import com.gag.enterprisewxmobile.tool.util.CglibTest;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

import static com.gag.enterprisewxmobile.tool.common.JSONResult.success;

/**
 * (Mseg)表控制层
 *
 * @author makejava
 * @since 2020-11-19 09:36:31
 */
@Api(value = "mseg信息")
@Controller
@RequestMapping("mseg")
public class MsegController extends BaseController {
    /**
     * 服务对象
     */
    @Resource
    private MsegService msegService;

    @RequiresPermissions("mseg:select")
    @GetMapping("select")
    @ResponseBody
    @ApiOperation(value = "查询mseg信息",notes = "根据时间段mseg信息",response = JSONResult.class,httpMethod = "GET")
    public JSONResult select(@ApiParam(value = "起始时间和结束时间") @RequestBody KaSystemTime kaSystemTime){
        startPage();
        LinkedList<Mseg> list = msegService.msegSystem(kaSystemTime);
        return success(list);
    }


    /**
     * 导出数据模板
     */
    @Log(title = "mseg导出", businessType = BusinessType.EXPORT)
    @GetMapping("exportExcel/{tableName}")
    @RequiresPermissions("selectmseg:select")
    @ResponseBody
    @ApiOperation(value = "导出mseg信息",notes = "根据时间段mseg信息",response = void.class,httpMethod = "GET")
    public void exportExcel(@PathVariable("tableName") String tableName,@ApiParam(value = "起始时间和结束时间") @RequestBody KaSystemTime kaSystemTime,HttpServletResponse response){
        //需要进行导出的数据集
        LinkedList<Mseg> list = msegService.msegSystem(kaSystemTime);
        /**
         * listBeanGeneratorObj  需要导出的数据的集合
         * classname 动态生成的bean.class
         * filename 生成的excel文件名
         */
        ExcelController.export(response,list,Mseg.class,new Date().getTime()+tableName);
    }

}