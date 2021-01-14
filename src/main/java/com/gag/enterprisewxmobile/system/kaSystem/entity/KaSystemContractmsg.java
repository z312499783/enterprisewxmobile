package com.gag.enterprisewxmobile.system.kaSystem.entity;

import java.io.Serializable;

/**
 * ka系统合同信息(KaSystemContractmsg)实体类
 *
 * @author makejava
 * @since 2020-10-30 15:07:35
 */
public class KaSystemContractmsg implements Serializable {
    private static final long serialVersionUID = 677529779872906664L;
    /**
    * autoID
    */
    private Integer autoid;
    /**
    * 系统
    */
    private String system;
    /**
    * 供应商号
    */
    private String supplyFirm;
    /**
    * 合同类型
    */
    private String contractType;
    /**
    * 合同编号
    */
    private String contractCode;
    /**
    * 公司主体
    */
    private String companySubject;
    /**
    * 备注
    */
    private String remarks;


    public Integer getAutoid() {
        return autoid;
    }

    public void setAutoid(Integer autoid) {
        this.autoid = autoid;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public String getSupplyFirm() {
        return supplyFirm;
    }

    public void setSupplyFirm(String supplyFirm) {
        this.supplyFirm = supplyFirm;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public String getContractCode() {
        return contractCode;
    }

    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }

    public String getCompanySubject() {
        return companySubject;
    }

    public void setCompanySubject(String companySubject) {
        this.companySubject = companySubject;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

}