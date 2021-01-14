package com.gag.enterprisewxmobile.system.user.entity;

import com.gag.enterprisewxmobile.framework.config.CustomRealm;

import java.io.Serializable;

/**
 * 企业微信用户信息表(QywxSysUser)实体类
 *
 * @author makejava
 * @since 2020-12-28 13:42:07
 */
public class QywxSysUser implements Serializable {
    private static final long serialVersionUID = -52942649721531100L;
    /**
    * 用户信息表id
    */
    private Integer usermsgId;
    /**
    * 用户账号userid
    */
    private String userid;
    /**
    * 用户姓名
    */
    private String name;
    /**
    * 所处部门Id
    */
    private Integer department;


    public boolean isAdmin(String roleKey)
    {
        return roleKey!=null && roleKey.equals("admin");
    }

    public Integer getUsermsgId() {
        return usermsgId;
    }

    public void setUsermsgId(Integer usermsgId) {
        this.usermsgId = usermsgId;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDepartment() {
        return department;
    }

    public void setDepartment(Integer department) {
        this.department = department;
    }

}