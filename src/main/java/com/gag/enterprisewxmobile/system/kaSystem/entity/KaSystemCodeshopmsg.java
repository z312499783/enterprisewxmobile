package com.gag.enterprisewxmobile.system.kaSystem.entity;

import java.io.Serializable;

/**
 * ka系统条码商品号信息(KaSystemCodeshopmsg)实体类
 *
 * @author makejava
 * @since 2020-10-30 15:07:11
 */
public class KaSystemCodeshopmsg implements Serializable {
    private static final long serialVersionUID = -65708708480764617L;
    /**
    * autoID
    */
    private Integer autoid;
    /**
    * 客户
    */
    private String clientName;
    /**
    * 商品条码
    */
    private String articleNumber;
    /**
    * 商品编码
    */
    private String articleCode;
    /**
    * 客户产品名称
    */
    private String customerProductName;
    /**
    * 品牌
    */
    private String brand;
    /**
    * 名称
    */
    private String name;
    /**
    * 规格
    */
    private String specification;


    public Integer getAutoid() {
        return autoid;
    }

    public void setAutoid(Integer autoid) {
        this.autoid = autoid;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getArticleNumber() {
        return articleNumber;
    }

    public void setArticleNumber(String articleNumber) {
        this.articleNumber = articleNumber;
    }

    public String getArticleCode() {
        return articleCode;
    }

    public void setArticleCode(String articleCode) {
        this.articleCode = articleCode;
    }

    public String getCustomerProductName() {
        return customerProductName;
    }

    public void setCustomerProductName(String customerProductName) {
        this.customerProductName = customerProductName;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

}