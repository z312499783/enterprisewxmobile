package com.gag.enterprisewxmobile.system.user.entity;

import java.io.Serializable;

/**
 * 企业微信角色和菜单关联表(QywxSysRoleMenu)实体类
 *
 * @author makejava
 * @since 2020-12-28 13:41:35
 */
public class QywxSysRoleMenu implements Serializable {
    private static final long serialVersionUID = 153571965056542251L;
    /**
    * 角色ID
    */
    private Integer roleId;
    /**
    * 菜单ID
    */
    private Integer menuId;


    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

}