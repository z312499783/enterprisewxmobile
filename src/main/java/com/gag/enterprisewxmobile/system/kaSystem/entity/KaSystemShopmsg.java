package com.gag.enterprisewxmobile.system.kaSystem.entity;

import java.io.Serializable;

/**
 * ka系统门店信息(KaSystemShopmsg)实体类
 *
 * @author makejava
 * @since 2020-10-30 17:22:00
 */
public class KaSystemShopmsg implements Serializable {
    private static final long serialVersionUID = -57481138101622306L;
    /**
    * autoID
    */
    private Integer autoid;
    /**
    * 门店编码
    */
    private String shopCode;
    /**
    * 管理小组
    */
    private String managementGroup;
    /**
    * 省
    */
    private String province;
    /**
    * 市
    */
    private String city;
    /**
    * 区
    */
    private String district;
    /**
    * 行政业务
    */
    private String administrativeBusiness;
    /**
    * 店系
    */
    private String shopDepartment;
    /**
    * 店名
    */
    private String shopName;
    /**
    * 地址
    */
    private String address;
    /**
    * 业态
    */
    private String commercialActivities;
    /**
    * 物流
    */
    private String logistics;

    /**
     * 是否导购
     */
    private String isnullGuide;

    /**
    * 导购人数
    */
    private String numberOfGuide;
    /**
    * 门店信息
    */
    private String shopMsg;


    public Integer getAutoid() {
        return autoid;
    }

    public void setAutoid(Integer autoid) {
        this.autoid = autoid;
    }

    public String getShopCode() {
        return shopCode;
    }

    public void setShopCode(String shopCode) {
        this.shopCode = shopCode;
    }

    public String getManagementGroup() {
        return managementGroup;
    }

    public void setManagementGroup(String managementGroup) {
        this.managementGroup = managementGroup;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAdministrativeBusiness() {
        return administrativeBusiness;
    }

    public void setAdministrativeBusiness(String administrativeBusiness) {
        this.administrativeBusiness = administrativeBusiness;
    }

    public String getShopDepartment() {
        return shopDepartment;
    }

    public void setShopDepartment(String shopDepartment) {
        this.shopDepartment = shopDepartment;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCommercialActivities() {
        return commercialActivities;
    }

    public void setCommercialActivities(String commercialActivities) {
        this.commercialActivities = commercialActivities;
    }

    public String getLogistics() {
        return logistics;
    }

    public void setLogistics(String logistics) {
        this.logistics = logistics;
    }

    public String getNumberOfGuide() {
        return numberOfGuide;
    }

    public void setNumberOfGuide(String numberOfGuide) {
        this.numberOfGuide = numberOfGuide;
    }

    public String getShopMsg() {
        return shopMsg;
    }

    public void setShopMsg(String shopMsg) {
        this.shopMsg = shopMsg;
    }

}