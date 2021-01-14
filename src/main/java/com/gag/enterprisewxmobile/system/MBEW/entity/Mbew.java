package com.gag.enterprisewxmobile.system.MBEW.entity;

import java.math.BigDecimal;
import java.io.Serializable;

/**
 * (Mbew)实体类
 *
 * @author makejava
 * @since 2020-05-09 11:19:04
 */
public class Mbew implements Serializable {
    private static final long serialVersionUID = 762976551272404018L;
    
    private Integer autoid;
    
    private String werks;
    
    private String lgort;
    
    private Integer matnr;
    /**
    * 数量
    */
    private Double menge1;
    /**
    * V 价
    */
    private BigDecimal money1;
    /**
    * S价
    */
    private BigDecimal money2;

    private String dt;

    private String startdt;

    public String getStartdt() {
        return startdt;
    }

    public void setStartdt(String startdt) {
        this.startdt = startdt;
    }

    public Integer getAutoid() {
        return autoid;
    }

    public void setAutoid(Integer autoid) {
        this.autoid = autoid;
    }

    public String getWerks() {
        return werks;
    }

    public void setWerks(String werks) {
        this.werks = werks;
    }

    public String getLgort() {
        return lgort;
    }

    public void setLgort(String lgort) {
        this.lgort = lgort;
    }

    public Integer getMatnr() {
        return matnr;
    }

    public void setMatnr(Integer matnr) {
        this.matnr = matnr;
    }

    public Double getMenge1() {
        return menge1;
    }

    public void setMenge1(Double menge1) {
        this.menge1 = menge1;
    }

    public BigDecimal getMoney1() {
        return money1;
    }

    public void setMoney1(BigDecimal money1) {
        this.money1 = money1;
    }

    public BigDecimal getMoney2() {
        return money2;
    }

    public void setMoney2(BigDecimal money2) {
        this.money2 = money2;
    }

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

}