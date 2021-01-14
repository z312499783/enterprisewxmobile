package com.gag.enterprisewxmobile.system.user.entity;

import java.io.Serializable;

/**
 * 企业微信用户信息表(Usermsg)实体类
 *
 * @author makejava
 * @since 2020-04-20 10:50:47
 */
public class Usermsg implements Serializable {
    private static final long serialVersionUID = -28170141010454990L;
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
    * 所处部门id
    */
    private Integer department;


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