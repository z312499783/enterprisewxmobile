package com.gag.enterprisewxmobile.system.ONLINEUSER.controller;

import com.gag.enterprisewxmobile.monitor.job.entity.EnterprisewxJob;
import com.gag.enterprisewxmobile.system.ONLINEUSER.entity.Onlineuser;
import com.gag.enterprisewxmobile.system.ONLINEUSER.entity.Timepush;
import com.gag.enterprisewxmobile.system.ONLINEUSER.service.OnlineuserService;
import com.gag.enterprisewxmobile.system.ONLINEUSER.service.TimepushService;
import com.gag.enterprisewxmobile.tool.common.BaseController;
import com.gag.enterprisewxmobile.tool.common.BusinessType;
import com.gag.enterprisewxmobile.tool.common.JSONResult;
import com.gag.enterprisewxmobile.tool.common.Log;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.gag.enterprisewxmobile.tool.common.JSONResult.custom;
import static com.gag.enterprisewxmobile.tool.common.JSONResult.success;

/**
 * (Onlineuser)表控制层
 *
 * @author makejava
 * @since 2020-09-25 13:53:58
 */
@Api(value = "工厂进出信息")
@Controller
@RequestMapping("onlineuser")
public class OnlineuserController extends BaseController {

    /**
     * 服务对象
     */
    @Resource
    private OnlineuserService onlineuserService;


    /**
     * 通过实体作为筛选条件查询
     *
     * @param onlineuser 实例对象
     * @return 对象列表
     */
    @RequiresPermissions("Onlineuser:select")
    @GetMapping("select")
    @ResponseBody
    @ApiOperation(value = "查询工厂进出信息",notes = "根据条件查询工厂进出信息",response = JSONResult.class,httpMethod = "GET")
    public JSONResult select(Onlineuser onlineuser){
        startPage();
        List<Onlineuser> list = onlineuserService.queryAll(onlineuser);
        return success(list);
    };

}