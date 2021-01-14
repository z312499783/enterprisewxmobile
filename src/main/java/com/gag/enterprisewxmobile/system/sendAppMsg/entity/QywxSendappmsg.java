package com.gag.enterprisewxmobile.system.sendAppMsg.entity;

import java.io.Serializable;
import java.util.List;

/**
 * (QywxSendappmsg)实体类
 *
 * @author makejava
 * @since 2020-06-01 10:27:27
 */
public class QywxSendappmsg implements Serializable {
    private static final long serialVersionUID = -77631910767424681L;

    /**
    * 指定接收消息的成员
    */
    private String touser;
    /**
    * 指定接收消息的部门
    */
    private String toparty;
    /**
    * 指定接收消息的标签
    */
    private String totag;
    /**
    * 消息类型
    */
    private String msgtype;
    /**
    * 企业应用的id
    */
    private Integer agentid;
    
    private QywxSendappmsgContent text;
    /**
    * 表示是否是保密消息，0表示否，1表示是，默认0
    */
    private Integer safe;
    /**
    * 表示是否开启id转译，0表示否，1表示是，默认0
    */
    private Integer enable_id_trans;
    /**
    * 	表示是否开启重复消息检查，0表示否，1表示是，默认0
    */
    private Integer enable_duplicate_check;
    /**
    * 	表示是否重复消息检查的时间间隔，默认1800s，最大不超过4小时
    */
    private Integer duplicate_check_interval;


    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public String getToparty() {
        return toparty;
    }

    public void setToparty(String toparty) {
        this.toparty = toparty;
    }

    public String getTotag() {
        return totag;
    }

    public void setTotag(String totag) {
        this.totag = totag;
    }

    public String getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }

    public Integer getAgentid() {
        return agentid;
    }

    public void setAgentid(Integer agentid) {
        this.agentid = agentid;
    }

    public QywxSendappmsgContent getText() {
        return text;
    }

    public void setText(QywxSendappmsgContent text) {
        this.text = text;
    }

    public Integer getSafe() {
        return safe;
    }

    public void setSafe(Integer safe) {
        this.safe = safe;
    }

    public Integer getEnable_id_trans() {
        return enable_id_trans;
    }

    public void setEnable_id_trans(Integer enable_id_trans) {
        this.enable_id_trans = enable_id_trans;
    }

    public Integer getEnable_duplicate_check() {
        return enable_duplicate_check;
    }

    public void setEnable_duplicate_check(Integer enable_duplicate_check) {
        this.enable_duplicate_check = enable_duplicate_check;
    }

    public Integer getDuplicate_check_interval() {
        return duplicate_check_interval;
    }

    public void setDuplicate_check_interval(Integer duplicate_check_interval) {
        this.duplicate_check_interval = duplicate_check_interval;
    }
}