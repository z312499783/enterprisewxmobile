package com.gag.enterprisewxmobile.system.menu.controller;

import com.gag.enterprisewxmobile.system.menu.entity.QywxSysMenu;
import com.gag.enterprisewxmobile.system.menu.service.QywxSysMenuService;
import com.gag.enterprisewxmobile.system.user.entity.QywxSysRole;
import com.gag.enterprisewxmobile.tool.common.BusinessType;
import com.gag.enterprisewxmobile.tool.common.JSONResult;
import com.gag.enterprisewxmobile.tool.common.Log;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

import static com.gag.enterprisewxmobile.tool.common.JSONResult.success;

/**
 * 菜单权限表(QywxSysMenu)表控制层
 *
 * @author makejava
 * @since 2020-12-28 13:40:15
 */
@Api(value = "菜单权限表(QywxSysMenu)")
@RestController
@RequestMapping("qywxSysMenu")
public class QywxSysMenuController {

    /**
     * 服务对象
     */
    @Resource
    private QywxSysMenuService qywxSysMenuService;

    @RequiresPermissions("qywxSysMenu:select")
    @GetMapping()
    public String menu()
    {
        return "qywxSysMenu";
    }

    @RequiresPermissions("qywxSysMenu:select")
    @GetMapping("/select")
    @ResponseBody
    @ApiOperation(value = "菜单权限表(QywxSysMenu)",notes = "查询菜单权限表",response = JSONResult.class,httpMethod = "GET")
    public JSONResult list(QywxSysMenu menu)
    {
        List<QywxSysMenu> menuList = qywxSysMenuService.selectMenuList(menu);
        return JSONResult.success(menuList);
    }

    /**
     * 删除菜单
     */
    @Log(title = "菜单管理", businessType = BusinessType.DELETE)
    @RequiresPermissions("qywxSysMenu:delete")
    @PostMapping("/delete/{menuId}")
    @ResponseBody
    @ApiOperation(value = "菜单权限表(QywxSysMenu)",notes = "删除菜单权限表",response = JSONResult.class,httpMethod = "POST")
    public JSONResult remove(@PathVariable("menuId") int menuId)
    {
        if (qywxSysMenuService.selectCountMenuByParentId(menuId) > 0)
        {
            return JSONResult.failMsg("存在子菜单,不允许删除");
        }
        if (qywxSysMenuService.selectCountRoleMenuByMenuId(menuId) > 0)
        {
            return JSONResult.failMsg("菜单已分配,不允许删除");
        }
        return JSONResult.custom(qywxSysMenuService.deleteMenuById(menuId),"");
    }


    /**
     * 新增保存菜单
     */
    @Log(title = "菜单管理", businessType = BusinessType.INSERT)
    @RequiresPermissions("qywxSysMenu:insert")
    @PostMapping("/insert")
    @ResponseBody
    @ApiOperation(value = "菜单权限表(QywxSysMenu)",notes = "添加菜单权限表",response = JSONResult.class,httpMethod = "POST")
    public JSONResult addSave(QywxSysMenu menu)
    {
        return JSONResult.custom(qywxSysMenuService.insertMenu(menu),menu);
    }


    /**
     * 修改保存菜单
     */
    @Log(title = "菜单管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("qywxSysMenu:update")
    @PostMapping("/update")
    @ResponseBody
    @ApiOperation(value = "菜单权限表(QywxSysMenu)",notes = "修改菜单权限表",response = JSONResult.class,httpMethod = "POST")
    public JSONResult editSave(QywxSysMenu menu)
    {
        return JSONResult.custom(qywxSysMenuService.updateMenu(menu),menu);
    }

    /**
     * 校验菜单名称
     */
    @PostMapping("/checkMenuNameUnique")
    @ResponseBody
    @ApiOperation(value = "菜单权限表(QywxSysMenu)",notes = "效验菜单名称",response = JSONResult.class,httpMethod = "POST")
    public JSONResult checkMenuNameUnique(QywxSysMenu menu)
    {
        return JSONResult.custom(qywxSysMenuService.checkMenuNameUnique(menu),menu);
    }

    /**
     * 加载角色菜单列表树
     */
    @GetMapping("/roleMenuTreeData")
    @ResponseBody
    @ApiOperation(value = "菜单权限表(QywxSysMenu)",notes = "加载角色菜单列表",response = JSONResult.class,httpMethod = "GET")
    public JSONResult roleMenuTreeData(QywxSysRole role)
    {
        List<Map<String, Object>> tree = qywxSysMenuService.roleMenuTreeData(role);
        return JSONResult.custom(tree);
    }

    /**
     * 加载所有菜单列表树
     */
    @GetMapping("/menuTreeData")
    @ResponseBody
    @ApiOperation(value = "菜单权限表(QywxSysMenu)",notes = "加载所有菜单列表",response = JSONResult.class,httpMethod = "GET")
    public JSONResult menuTreeData(QywxSysRole role)
    {
        List<Map<String, Object>> tree = qywxSysMenuService.menuTreeData();
        return JSONResult.custom(tree);
    }


}