package com.gag.enterprisewxmobile.system.MBEW.controller;

import com.gag.enterprisewxmobile.system.MBEW.entity.Mbew;
import com.gag.enterprisewxmobile.system.MBEW.service.MbewService;
import com.gag.enterprisewxmobile.tool.common.BaseController;
import com.gag.enterprisewxmobile.tool.common.utils.DateUtils;
import com.gag.enterprisewxmobile.tool.common.JSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.*;

/**
 * (Mbew)表控制层
 *
 * @author makejava
 * @since 2020-05-09 11:19:06
 */
@Api(value = "mbew查询")
@Controller
@RequestMapping("mbew")
public class MbewController extends BaseController {

    /**
     * 服务对象
     */
    @Resource
    private MbewService mbewService;

    /**
     * 通过实体作为筛选条件查询数据
     *
     * @param mbew 实例对象
     * @return 对象列表
     */
    @RequiresPermissions("mbew:select")
    @GetMapping("select")
    @ResponseBody
    @ApiOperation(value = "mbew查询",notes = "根据时间查询mbew",response = JSONResult.class,httpMethod = "GET")
    public JSONResult select(Mbew mbew){
        //设置查询的时间为今天
        mbew.setDt(DateUtils.getDate());
        //Startdt当空时设置Startdt为当前时间
        if (mbew.getStartdt()==null){
            mbew.setStartdt(DateUtils.getDate());
        }else {
            mbew.setStartdt(DateUtils.dateTime(new Date(mbew.getStartdt())));
        }

        startPage();
        List<Mbew> list = this.mbewService.querymoney(mbew);
        return JSONResult.success(list);
    }

}