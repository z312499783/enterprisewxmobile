package com.gag.enterprisewxmobile.system.kaSystem.entity;

import java.io.Serializable;

/**
 * ka系统盛宝产品信息(KaGagProductmsg)实体类
 *
 * @author makejava
 * @since 2020-11-02 13:36:19
 */
public class KaGagProductmsg implements Serializable {
    private static final long serialVersionUID = -34712069517836027L;
    /**
    * autoID
    */

    private Integer autoid;
    /**
    * 条码
    */
    private String articleNumber;
    /**
    * MSG商品名称
    */
    private String msgProductName;
    /**
    * 产品名称
    */
    private String productName;
    /**
    * 保质期
    */
    private String expirationDate;
    /**
    * 品类
    */
    private String category;
    /**
    * 品牌
    */
    private String brand;
    /**
    * 子品牌
    */
    private String sonBrand;
    /**
    * 规格
    */
    private String specification;
    /**
    * 品牌类别
    */
    private String brandCategory;
    /**
    * 零售指导售价
    */
    private String retailGuide;
    /**
    * 生产状态
    */
    private String generateState;
    /**
    * 外包装材质
    */
    private String outerPackingMaterial;
    /**
    * 包装尺寸长
    */
    private String packSizeLength;
    /**
    * 包装尺寸宽
    */
    private String packSizeWide;
    /**
    * 包装尺寸高
    */
    private String packSizeHigh;
    /**
    * 成品尺寸长
    */
    private String finishedSizeLength;
    /**
    * 成品尺寸宽
    */
    private String finishedSizeWide;
    /**
    * 成品尺寸高
    */
    private String finishedSizeHigh;
    /**
    * 箱装尺寸长
    */
    private String boxSizeLength;
    /**
    * 箱装尺寸宽
    */
    private String boxSizeWide;
    /**
    * 箱装尺寸高
    */
    private String boxSizeHigh;
    /**
    * 箱装包数
    */
    private String boxPackNumber;
    /**
    * 箱装条码
    */
    private String boxCode;
    /**
    * 产品说明
    */
    private String descriptionProducts;


    public Integer getAutoid() {
        return autoid;
    }

    public void setAutoid(Integer autoid) {
        this.autoid = autoid;
    }

    public String getArticleNumber() {
        return articleNumber;
    }

    public void setArticleNumber(String articleNumber) {
        this.articleNumber = articleNumber;
    }

    public String getMsgProductName() {
        return msgProductName;
    }

    public void setMsgProductName(String msgProductName) {
        this.msgProductName = msgProductName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getSonBrand() {
        return sonBrand;
    }

    public void setSonBrand(String sonBrand) {
        this.sonBrand = sonBrand;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getBrandCategory() {
        return brandCategory;
    }

    public void setBrandCategory(String brandCategory) {
        this.brandCategory = brandCategory;
    }

    public String getRetailGuide() {
        return retailGuide;
    }

    public void setRetailGuide(String retailGuide) {
        this.retailGuide = retailGuide;
    }

    public String getGenerateState() {
        return generateState;
    }

    public void setGenerateState(String generateState) {
        this.generateState = generateState;
    }

    public String getOuterPackingMaterial() {
        return outerPackingMaterial;
    }

    public void setOuterPackingMaterial(String outerPackingMaterial) {
        this.outerPackingMaterial = outerPackingMaterial;
    }

    public String getPackSizeLength() {
        return packSizeLength;
    }

    public void setPackSizeLength(String packSizeLength) {
        this.packSizeLength = packSizeLength;
    }

    public String getPackSizeWide() {
        return packSizeWide;
    }

    public void setPackSizeWide(String packSizeWide) {
        this.packSizeWide = packSizeWide;
    }

    public String getPackSizeHigh() {
        return packSizeHigh;
    }

    public void setPackSizeHigh(String packSizeHigh) {
        this.packSizeHigh = packSizeHigh;
    }

    public String getFinishedSizeLength() {
        return finishedSizeLength;
    }

    public void setFinishedSizeLength(String finishedSizeLength) {
        this.finishedSizeLength = finishedSizeLength;
    }

    public String getFinishedSizeWide() {
        return finishedSizeWide;
    }

    public void setFinishedSizeWide(String finishedSizeWide) {
        this.finishedSizeWide = finishedSizeWide;
    }

    public String getFinishedSizeHigh() {
        return finishedSizeHigh;
    }

    public void setFinishedSizeHigh(String finishedSizeHigh) {
        this.finishedSizeHigh = finishedSizeHigh;
    }

    public String getBoxSizeLength() {
        return boxSizeLength;
    }

    public void setBoxSizeLength(String boxSizeLength) {
        this.boxSizeLength = boxSizeLength;
    }

    public String getBoxSizeWide() {
        return boxSizeWide;
    }

    public void setBoxSizeWide(String boxSizeWide) {
        this.boxSizeWide = boxSizeWide;
    }

    public String getBoxSizeHigh() {
        return boxSizeHigh;
    }

    public void setBoxSizeHigh(String boxSizeHigh) {
        this.boxSizeHigh = boxSizeHigh;
    }

    public String getBoxPackNumber() {
        return boxPackNumber;
    }

    public void setBoxPackNumber(String boxPackNumber) {
        this.boxPackNumber = boxPackNumber;
    }

    public String getBoxCode() {
        return boxCode;
    }

    public void setBoxCode(String boxCode) {
        this.boxCode = boxCode;
    }

    public String getDescriptionProducts() {
        return descriptionProducts;
    }

    public void setDescriptionProducts(String descriptionProducts) {
        this.descriptionProducts = descriptionProducts;
    }

}