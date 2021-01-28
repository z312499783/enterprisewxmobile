package com.gag.enterprisewxmobile.system.controlTemplate.controller;


import com.gag.enterprisewxmobile.system.controlTemplate.entity.Controltemplate;
import com.gag.enterprisewxmobile.system.controlTemplate.service.ControltemplateService;
import com.gag.enterprisewxmobile.tool.common.BaseController;
import com.gag.enterprisewxmobile.tool.common.JSONResult;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 审批通用模板表(Controltemplate)表控制层
 *
 * @author makejava
 * @since 2020-05-26 09:25:08
 */
@Api(value = "企业微信审批信息")
@Controller
@RequestMapping("controltemplate")
public class ControltemplateController extends BaseController{

    /**
     * 服务对象
     */
    @Resource
    private ControltemplateService controltemplateService;

    @RequiresPermissions("controltemplate:select")
    @GetMapping("select")
    @ResponseBody
    @ApiOperation(value = "企业微信审批信息",notes = "查询企业微信审批信息",response = JSONResult.class,httpMethod = "GET")
    public JSONResult select(Controltemplate controltemplate){
        startPage();
        return JSONResult.tablePageLayui(this.controltemplateService.queryAll(controltemplate));
    }


}