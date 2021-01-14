package com.gag.enterprisewxmobile.system.approval.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * 企业微信相关数据(Approval)实体类
 *
 * @author makejava
 * @since 2020-04-20 17:20:10
 */
public class Approval implements Serializable {
    private static final long serialVersionUID = 824004098503975734L;
    /**
    * 企业微信许可id
    */
    private Integer approvalId;
    /**
    * 应用Id
    */
    private String agentid;
    /**
    * 企业id
    */
    private String corpid;
    /**
    * 审批secret
    */
    private String corpsecret;
    /**
    * access_token
    */
    private String accessToken;
    /**
    * 凭证的有效时间
    */
    private Integer expiresIn;
    /**
    * 获取凭证开始时间
    */
    private Date startTime;
    /**
    * 获取凭证结束时间
    */
    private Date endTime;


    public Integer getApprovalId() {
        return approvalId;
    }

    public void setApprovalId(Integer approvalId) {
        this.approvalId = approvalId;
    }

    public String getAgentid() {
        return agentid;
    }

    public void setAgentid(String agentid) {
        this.agentid = agentid;
    }

    public String getCorpid() {
        return corpid;
    }

    public void setCorpid(String corpid) {
        this.corpid = corpid;
    }

    public String getCorpsecret() {
        return corpsecret;
    }

    public void setCorpsecret(String corpsecret) {
        this.corpsecret = corpsecret;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Integer getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

}