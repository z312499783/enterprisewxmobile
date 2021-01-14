package com.gag.enterprisewxmobile.system.user.controller;

import com.gag.enterprisewxmobile.system.menu.entity.QywxSysMenu;
import com.gag.enterprisewxmobile.system.menu.service.QywxSysMenuService;
import com.gag.enterprisewxmobile.system.user.entity.QywxSysUser;
import com.gag.enterprisewxmobile.tool.common.BaseController;
import com.gag.enterprisewxmobile.tool.common.JSONResult;
import com.gag.enterprisewxmobile.tool.common.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Api(value = "获取用户登陆的信息")
@Controller
public class IndexController extends BaseController {

    @Resource
    private QywxSysMenuService qywxSysMenuService;

    @GetMapping("getUserMsg")
    @ApiOperation(value = "获取用户登陆的信息",notes = "获取用户登陆的信息以及所拥有的权限菜单",response = JSONResult.class,httpMethod = "GET")
    public JSONResult getUserMsg(Map map){
        QywxSysUser user = getSysUser();
        if (StringUtils.isNotNull(user)){
            List<QywxSysMenu> menus = qywxSysMenuService.selectMenusByUser(user);
            map.put("menus",menus);
            map.put("user",user);
            return JSONResult.success(map);
        }
        return JSONResult.failMsg("未登录或登录超时,请重新登录");
    }

}
