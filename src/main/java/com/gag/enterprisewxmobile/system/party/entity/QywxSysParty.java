package com.gag.enterprisewxmobile.system.party.entity;

import java.io.Serializable;

/**
 * 企业微信部门信息表(QywxSysParty)实体类
 *
 * @author makejava
 * @since 2020-12-28 13:40:32
 */
public class QywxSysParty implements Serializable {
    private static final long serialVersionUID = 805922577991227442L;
    /**
    * 部门表id
    */
    private Integer partyId;
    /**
    * 部门编号
    */
    private Integer id;
    /**
    * 部门名称
    */
    private String partyname;
    /**
    * 上级部门编号
    */
    private Integer parentid;
    /**
    * 订单号码
    */
    private String ordernum;


    public Integer getPartyId() {
        return partyId;
    }

    public void setPartyId(Integer partyId) {
        this.partyId = partyId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPartyname() {
        return partyname;
    }

    public void setPartyname(String partyname) {
        this.partyname = partyname;
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    public String getOrdernum() {
        return ordernum;
    }

    public void setOrdernum(String ordernum) {
        this.ordernum = ordernum;
    }

}