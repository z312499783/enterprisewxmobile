package com.gag.enterprisewxmobile.system.kaSystem.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gag.enterprisewxmobile.tool.common.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 商品销售(ShopPos)实体类
 *
 * @author makejava
 * @since 2020-11-02 15:59:49
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShopPos extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -76480872711614304L;
    /**
    * autoID
    */
    private Integer autoid;
    /**
    * 合同编码
    */
    private String contractCode;
    /**
    * 门店编码
    */
    private String shopCode;
    /**
    * 商品条码
    */
    private String articleNumber;
    /**
    * 数量
    */
    private Integer quantity;
    /**
    * 单价
    */
    private Double unitPrice;
    /**
    * 总金额
    */
    private Double aggregateAmount;
    /**
    * 折扣金额
    */
    private Double discountAmount;
    /**
    * 折后金额
    */
    private Double discountedAmount;
    /**
    * 销售日期
    */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date salesDate;



}