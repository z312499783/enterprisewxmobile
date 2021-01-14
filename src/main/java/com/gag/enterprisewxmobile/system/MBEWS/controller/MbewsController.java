package com.gag.enterprisewxmobile.system.MBEWS.controller;

import com.gag.enterprisewxmobile.system.MBEW.entity.Mbew;
import com.gag.enterprisewxmobile.system.MBEWS.entity.Mbews;
import com.gag.enterprisewxmobile.system.MBEWS.service.MbewsService;
import com.gag.enterprisewxmobile.tool.common.BaseController;
import com.gag.enterprisewxmobile.tool.common.JSONResult;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.gag.enterprisewxmobile.tool.common.JSONResult.success;

/**
 * (Mbews)表控制层
 *
 * @author makejava
 * @since 2020-05-14 10:31:57
 */
@Api(value = "mbews(v价报价查询)")
@Controller
@RequestMapping("mbews")
public class MbewsController extends BaseController {
    /**
     * 服务对象
     */
    @Resource
    private MbewsService mbewsService;


    @RequiresPermissions("mbews:select")
    @GetMapping("select/{autoid}")
    @ResponseBody
    @ApiOperation(value = "msegs(v价报价查询)",notes = "查询msegs(v价报价查询)",response = JSONResult.class,httpMethod = "GET")
    public JSONResult selectById(@ApiParam(value = "autoid",name = "根据autoid查询数据",required = true) @RequestParam(value = "autoid") Integer autoid){
        return success(mbewsService.queryById(autoid));
    }


    /**
     * 通过实体作为筛选条件查询数据
     *
     * @param mbews 实例对象
     * @return 对象列表
     */
    @RequiresPermissions("mbews:select")
    @GetMapping("select")
    @ResponseBody
    @ApiOperation(value = "msegs(v价报价查询)",notes = "进入msegs(v价报价查询)查询",response = JSONResult.class,httpMethod = "GET")
    public JSONResult select(@ApiParam(value = "Mbews",name = "根据Mbews对象查询信息") Mbews mbews){
        startPage();
        List<Mbews> list = this.mbewsService.queryMbews(mbews);
        return success(list);
    }

}