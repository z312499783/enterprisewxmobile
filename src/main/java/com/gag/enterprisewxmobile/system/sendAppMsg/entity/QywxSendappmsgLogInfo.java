package com.gag.enterprisewxmobile.system.sendAppMsg.entity;

import java.io.Serializable;

/**
 * (QywxSendappmsgLogInfo)实体类
 *
 * @author makejava
 * @since 2020-06-08 11:14:55
 */
public class QywxSendappmsgLogInfo implements Serializable {
    private static final long serialVersionUID = 299887297564364985L;
    
    private Integer jobLogInfoId;
    
    private String jobInfoName;
    
    private String jobInfoGroup;
    
    private String methodInfoName;
    
    private String methodInfoParams;
    
    private String jobInfoMessage;
    
    private String infoStatus;
    
    private String infoExceptionInfo;


    public Integer getJobLogInfoId() {
        return jobLogInfoId;
    }

    public void setJobLogInfoId(Integer jobLogInfoId) {
        this.jobLogInfoId = jobLogInfoId;
    }

    public String getJobInfoName() {
        return jobInfoName;
    }

    public void setJobInfoName(String jobInfoName) {
        this.jobInfoName = jobInfoName;
    }

    public String getJobInfoGroup() {
        return jobInfoGroup;
    }

    public void setJobInfoGroup(String jobInfoGroup) {
        this.jobInfoGroup = jobInfoGroup;
    }

    public String getMethodInfoName() {
        return methodInfoName;
    }

    public void setMethodInfoName(String methodInfoName) {
        this.methodInfoName = methodInfoName;
    }

    public String getMethodInfoParams() {
        return methodInfoParams;
    }

    public void setMethodInfoParams(String methodInfoParams) {
        this.methodInfoParams = methodInfoParams;
    }

    public String getJobInfoMessage() {
        return jobInfoMessage;
    }

    public void setJobInfoMessage(String jobInfoMessage) {
        this.jobInfoMessage = jobInfoMessage;
    }

    public String getInfoStatus() {
        return infoStatus;
    }

    public void setInfoStatus(String infoStatus) {
        this.infoStatus = infoStatus;
    }

    public String getInfoExceptionInfo() {
        return infoExceptionInfo;
    }

    public void setInfoExceptionInfo(String infoExceptionInfo) {
        this.infoExceptionInfo = infoExceptionInfo;
    }

}