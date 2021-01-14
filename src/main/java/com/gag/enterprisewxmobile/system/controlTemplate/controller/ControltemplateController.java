package com.gag.enterprisewxmobile.system.controlTemplate.controller;


import com.gag.enterprisewxmobile.system.controlTemplate.entity.Controltemplate;
import com.gag.enterprisewxmobile.system.controlTemplate.service.ControltemplateService;
import com.gag.enterprisewxmobile.tool.common.BaseController;
import com.gag.enterprisewxmobile.tool.common.JSONResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import java.util.List;

/**
 * 审批通用模板表(Controltemplate)表控制层
 *
 * @author makejava
 * @since 2020-05-26 09:25:08
 */
@Controller
@RequestMapping("controltemplate")
public class ControltemplateController extends BaseController {
    /**
     * 服务对象
     */
    @Resource
    private ControltemplateService controltemplateService;

    @RequestMapping("queryall")
    @ResponseBody
    public JSONResult queryall(Controltemplate controltemplate){
        startPage();
        return JSONResult.tablePageLayui(this.controltemplateService.queryAll(controltemplate));
    }


}