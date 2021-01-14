package com.gag.enterprisewxmobile.tool.gen.controller;

import com.gag.enterprisewxmobile.tool.common.BaseController;
import com.gag.enterprisewxmobile.tool.common.JSONResult;
import com.gag.enterprisewxmobile.tool.gen.service.GenService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import java.util.*;

import static com.gag.enterprisewxmobile.tool.common.JSONResult.success;


@Slf4j
@Controller
@RequestMapping("gen")
public class GenController extends BaseController {

    /**
     * 服务对象
     */
    @Resource
    private GenService genService;

    @RequiresPermissions("excel:select")
    @RequestMapping("select/{tableName}")
    @ResponseBody
    public JSONResult select(@PathVariable("tableName") String tableName){
        LinkedList<LinkedHashMap> list = this.genService.selectmseg(tableName);
        return success(list);
    };
}