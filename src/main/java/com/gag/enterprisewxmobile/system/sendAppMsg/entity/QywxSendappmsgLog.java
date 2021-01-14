package com.gag.enterprisewxmobile.system.sendAppMsg.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * 定时任务调度日志表(QywxSendappmsgLog)实体类
 *
 * @author makejava
 * @since 2020-06-01 10:27:54
 */
public class QywxSendappmsgLog implements Serializable {
    private static final long serialVersionUID = -39797610450712845L;
    /**
    * 任务日志ID
    */
    private Integer jobLogId;
    /**
    * 任务名称
    */
    private String jobName;
    /**
    * 任务组名
    */
    private String jobGroup;
    /**
    * 任务方法
    */
    private String methodName;
    /**
    * 方法参数
    */
    private String methodParams;
    /**
    * 日志信息
    */
    private String jobMessage;
    /**
    * 执行状态（0正常 1失败）
    */
    private String status;
    /**
    * 异常信息
    */
    private String exceptionInfo;
    /**
    * 创建时间
    */
    private Date createTime;


    public Integer getJobLogId() {
        return jobLogId;
    }

    public void setJobLogId(Integer jobLogId) {
        this.jobLogId = jobLogId;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobGroup() {
        return jobGroup;
    }

    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getMethodParams() {
        return methodParams;
    }

    public void setMethodParams(String methodParams) {
        this.methodParams = methodParams;
    }

    public String getJobMessage() {
        return jobMessage;
    }

    public void setJobMessage(String jobMessage) {
        this.jobMessage = jobMessage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getExceptionInfo() {
        return exceptionInfo;
    }

    public void setExceptionInfo(String exceptionInfo) {
        this.exceptionInfo = exceptionInfo;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}