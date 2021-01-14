package com.gag.enterprisewxmobile.system.controlTemplate.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * 审批通用模板表(Controltemplate)实体类
 *
 * @author makejava
 * @since 2020-05-26 09:25:06
 */
public class Controltemplate implements Serializable {
    private static final long serialVersionUID = 116504119611117865L;
    /**
    * 审批模板表id
    */
    private Integer tableId;
    /**
    * 审批模板名字
    */
    private String spName;
    /**
    * 模板id
    */
    private String templateId;
    /**
    * 审批编号
    */
    private String approvalNum;
    /**
    * 提交时间
    */
    private Date submitTime;
    /**
    * 申请人id
    */
    private String userid;
    /**
    * 申请人部门id
    */
    private Integer partyId;
    /**
    * 审批状态(1审批中，2已通过，3已驳回，4已撤销，6通过后撤销，7已删除，10已支付,)
    */
    private Integer spStatus;
    /**
    * 文本text
    */
    private String titleText;
    /**
    * 通用模板文本
    */
    private String templateText;
    /**
    * 文本textarea
    */
    private String titleTextarea;
    /**
    * 通用模板多行文本
    */
    private String templateTextarea;
    /**
    * 文本number
    */
    private String titleNumber;
    /**
    * 通用模板数字
    */
    private String templateNumber;
    /**
    * 文本money
    */
    private String titleMoney;
    /**
    * 通用模板金额
    */
    private String templateMoney;
    /**
    * 文本date
    */
    private String titleDate;
    /**
    * 通用模板日期
    */
    private String templateDate;
    /**
    * 文本datetime
    */
    private String titleDatetime;
    /**
    * 通用模板日期+时间
    */
    private String templateDatetime;
    /**
    * 文本new_begin
    */
    private String titleNewBegin;
    /**
    * 通用模板开始时间
    */
    private String templateNewBegin;
    /**
    * 文本new_end
    */
    private String titleNewEnd;
    /**
    * 通用模板结束时间
    */
    private String templateNewEnd;
    /**
    * 文本new_duration
    */
    private String titleNewDuration;
    /**
    * 通用模板时长
    */
    private String templateNewDuration;
    /**
    * 文本selector_single
    */
    private String titleSelectorSingle;
    /**
    * 通用模板单选
    */
    private String templateSelectorSingle;
    /**
    * 文本selector_multi
    */
    private String titleSelectorMulti;
    /**
    * 通用模板多选
    */
    private String templateSelectorMulti;
    /**
    * 文本contact_member
    */
    private String titleContactMember;
    /**
    * 通用模板成员
    */
    private String templateContactMember;
    /**
    * 文本contact_department
    */
    private String titleContactDepartment;
    /**
    * 通用模板部门
    */
    private String templateContactDepartment;
    /**
    * 文本file
    */
    private String titleFile;
    /**
    * 通用模板附件
    */
    private String templateFile;
    /**
    * 文本tips
    */
    private String titleTips;
    /**
    * 通用模板说明
    */
    private String templateTips;
    /**
    * 文本table
    */
    private String titleTable;
    /**
    * 通用模板明细
    */
    private String templateTable;
    /**
    * 审批人
    */
    private String approver;
    /**
    * 抄送人
    */
    private String copyPerson;
    /**
    * 备注
    */
    private String remarks;


    public Integer getTableId() {
        return tableId;
    }

    public void setTableId(Integer tableId) {
        this.tableId = tableId;
    }

    public String getSpName() {
        return spName;
    }

    public void setSpName(String spName) {
        this.spName = spName;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getApprovalNum() {
        return approvalNum;
    }

    public void setApprovalNum(String approvalNum) {
        this.approvalNum = approvalNum;
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public Integer getPartyId() {
        return partyId;
    }

    public void setPartyId(Integer partyId) {
        this.partyId = partyId;
    }

    public Integer getSpStatus() {
        return spStatus;
    }

    public void setSpStatus(Integer spStatus) {
        this.spStatus = spStatus;
    }

    public String getTitleText() {
        return titleText;
    }

    public void setTitleText(String titleText) {
        this.titleText = titleText;
    }

    public String getTemplateText() {
        return templateText;
    }

    public void setTemplateText(String templateText) {
        this.templateText = templateText;
    }

    public String getTitleTextarea() {
        return titleTextarea;
    }

    public void setTitleTextarea(String titleTextarea) {
        this.titleTextarea = titleTextarea;
    }

    public String getTemplateTextarea() {
        return templateTextarea;
    }

    public void setTemplateTextarea(String templateTextarea) {
        this.templateTextarea = templateTextarea;
    }

    public String getTitleNumber() {
        return titleNumber;
    }

    public void setTitleNumber(String titleNumber) {
        this.titleNumber = titleNumber;
    }

    public String getTemplateNumber() {
        return templateNumber;
    }

    public void setTemplateNumber(String templateNumber) {
        this.templateNumber = templateNumber;
    }

    public String getTitleMoney() {
        return titleMoney;
    }

    public void setTitleMoney(String titleMoney) {
        this.titleMoney = titleMoney;
    }

    public String getTemplateMoney() {
        return templateMoney;
    }

    public void setTemplateMoney(String templateMoney) {
        this.templateMoney = templateMoney;
    }

    public String getTitleDate() {
        return titleDate;
    }

    public void setTitleDate(String titleDate) {
        this.titleDate = titleDate;
    }

    public String getTemplateDate() {
        return templateDate;
    }

    public void setTemplateDate(String templateDate) {
        this.templateDate = templateDate;
    }

    public String getTitleDatetime() {
        return titleDatetime;
    }

    public void setTitleDatetime(String titleDatetime) {
        this.titleDatetime = titleDatetime;
    }

    public String getTemplateDatetime() {
        return templateDatetime;
    }

    public void setTemplateDatetime(String templateDatetime) {
        this.templateDatetime = templateDatetime;
    }

    public String getTitleNewBegin() {
        return titleNewBegin;
    }

    public void setTitleNewBegin(String titleNewBegin) {
        this.titleNewBegin = titleNewBegin;
    }

    public String getTemplateNewBegin() {
        return templateNewBegin;
    }

    public void setTemplateNewBegin(String templateNewBegin) {
        this.templateNewBegin = templateNewBegin;
    }

    public String getTitleNewEnd() {
        return titleNewEnd;
    }

    public void setTitleNewEnd(String titleNewEnd) {
        this.titleNewEnd = titleNewEnd;
    }

    public String getTemplateNewEnd() {
        return templateNewEnd;
    }

    public void setTemplateNewEnd(String templateNewEnd) {
        this.templateNewEnd = templateNewEnd;
    }

    public String getTitleNewDuration() {
        return titleNewDuration;
    }

    public void setTitleNewDuration(String titleNewDuration) {
        this.titleNewDuration = titleNewDuration;
    }

    public String getTemplateNewDuration() {
        return templateNewDuration;
    }

    public void setTemplateNewDuration(String templateNewDuration) {
        this.templateNewDuration = templateNewDuration;
    }

    public String getTitleSelectorSingle() {
        return titleSelectorSingle;
    }

    public void setTitleSelectorSingle(String titleSelectorSingle) {
        this.titleSelectorSingle = titleSelectorSingle;
    }

    public String getTemplateSelectorSingle() {
        return templateSelectorSingle;
    }

    public void setTemplateSelectorSingle(String templateSelectorSingle) {
        this.templateSelectorSingle = templateSelectorSingle;
    }

    public String getTitleSelectorMulti() {
        return titleSelectorMulti;
    }

    public void setTitleSelectorMulti(String titleSelectorMulti) {
        this.titleSelectorMulti = titleSelectorMulti;
    }

    public String getTemplateSelectorMulti() {
        return templateSelectorMulti;
    }

    public void setTemplateSelectorMulti(String templateSelectorMulti) {
        this.templateSelectorMulti = templateSelectorMulti;
    }

    public String getTitleContactMember() {
        return titleContactMember;
    }

    public void setTitleContactMember(String titleContactMember) {
        this.titleContactMember = titleContactMember;
    }

    public String getTemplateContactMember() {
        return templateContactMember;
    }

    public void setTemplateContactMember(String templateContactMember) {
        this.templateContactMember = templateContactMember;
    }

    public String getTitleContactDepartment() {
        return titleContactDepartment;
    }

    public void setTitleContactDepartment(String titleContactDepartment) {
        this.titleContactDepartment = titleContactDepartment;
    }

    public String getTemplateContactDepartment() {
        return templateContactDepartment;
    }

    public void setTemplateContactDepartment(String templateContactDepartment) {
        this.templateContactDepartment = templateContactDepartment;
    }

    public String getTitleFile() {
        return titleFile;
    }

    public void setTitleFile(String titleFile) {
        this.titleFile = titleFile;
    }

    public String getTemplateFile() {
        return templateFile;
    }

    public void setTemplateFile(String templateFile) {
        this.templateFile = templateFile;
    }

    public String getTitleTips() {
        return titleTips;
    }

    public void setTitleTips(String titleTips) {
        this.titleTips = titleTips;
    }

    public String getTemplateTips() {
        return templateTips;
    }

    public void setTemplateTips(String templateTips) {
        this.templateTips = templateTips;
    }

    public String getTitleTable() {
        return titleTable;
    }

    public void setTitleTable(String titleTable) {
        this.titleTable = titleTable;
    }

    public String getTemplateTable() {
        return templateTable;
    }

    public void setTemplateTable(String templateTable) {
        this.templateTable = templateTable;
    }

    public String getApprover() {
        return approver;
    }

    public void setApprover(String approver) {
        this.approver = approver;
    }

    public String getCopyPerson() {
        return copyPerson;
    }

    public void setCopyPerson(String copyPerson) {
        this.copyPerson = copyPerson;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

}