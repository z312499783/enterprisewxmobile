package com.gag.enterprisewxmobile.system.ONLINEUSER.controller;

import com.gag.enterprisewxmobile.system.ONLINEUSER.entity.Timepush;
import com.gag.enterprisewxmobile.system.ONLINEUSER.service.TimepushService;
import com.gag.enterprisewxmobile.tool.common.BaseController;
import com.gag.enterprisewxmobile.tool.common.BusinessType;
import com.gag.enterprisewxmobile.tool.common.JSONResult;
import com.gag.enterprisewxmobile.tool.common.Log;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import java.util.List;
import static com.gag.enterprisewxmobile.tool.common.JSONResult.custom;

@Api(value = "工厂进出多长时间发送消息")
@Controller
@RequestMapping("timepush")
public class TimepushController  extends BaseController {


    @Resource
    private TimepushService timepushService;

    @RequiresPermissions("timepush:select")
    @GetMapping("select")
    @ResponseBody
    @ApiOperation(value = "查询工厂进出多长时间发送消息",notes = "查询工厂进出多长时间发送消息",response = JSONResult.class,httpMethod = "GET")
    public JSONResult select(){
        List<Timepush> timepushes = timepushService.queryAll(null);
        return custom(timepushes.size(),timepushes.get(0));
    }

    @Log(title = "进出提示", businessType = BusinessType.UPDATE)
    @RequiresPermissions("timepush:update")
    @PostMapping("update")
    @ResponseBody
    @ApiOperation(value = "修改工厂进出多长时间发送消息",notes = "根据参数修改多长时间发送消息",response = JSONResult.class,httpMethod = "POST")
    public JSONResult update(@ApiParam(required = true) Timepush timepush){
        int i = this.timepushService.update(timepush);
        return custom(i,timepush);
    }


}
