package com.gag.enterprisewxmobile.system.user.entity;

import java.io.Serializable;

/**
 * 企业微信用户和角色关联表(QywxSysUserRole)实体类
 *
 * @author makejava
 * @since 2020-12-28 13:41:47
 */
public class QywxSysUserRole implements Serializable {
    private static final long serialVersionUID = 741283824506869849L;
    /**
    * 用户账号userid
    */
    private String userId;
    /**
    * 角色id
    */
    private Integer roleId;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

}