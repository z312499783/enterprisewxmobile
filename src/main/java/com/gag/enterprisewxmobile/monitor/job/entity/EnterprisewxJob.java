package com.gag.enterprisewxmobile.monitor.job.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * 定时任务调度表(EnterprisewxJob)实体类
 *
 * @author makejava
 * @since 2020-04-22 11:08:56
 */
public class EnterprisewxJob implements Serializable {
    private static final long serialVersionUID = -97336469427666427L;
    /**
    * 任务ID
    */
    private Integer jobId;
    /**
    * 任务名称(类名)
    */
    private String jobName;
    /**
    * 任务组名(唯一)
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
    * cron执行表达式
    */
    private String cronExpression;
    /**
    * 状态（0正常 1暂停）
    */
    private String status;
    /**
    * 创建者
    */
    private String createBy;
    /**
    * 创建时间
    */
    private Date createTime;
    /**
    * 更新者
    */
    private String updateBy;
    /**
    * 更新时间
    */
    private Date updateTime;
    /**
    * 备注信息
    */
    private String remark;


    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
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

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}