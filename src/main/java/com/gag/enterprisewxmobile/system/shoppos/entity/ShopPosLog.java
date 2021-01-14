package com.gag.enterprisewxmobile.system.shoppos.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gag.enterprisewxmobile.tool.common.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.io.Serializable;

/**
 * 日志(ShopPosLog)实体类
 *
 * @author makejava
 * @since 2020-10-15 10:01:54
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "操作日志",description = "日志参数类")
public class ShopPosLog extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 735726368783949191L;
    /**
    * autoID
    */
    @ApiModelProperty(value = "autoID")
    private Integer autoid;
    /**
    * 用户账号
    */
    @ApiModelProperty(value = "用户账号")
    private String userid;
    /**
    * 姓名
    */
    @ApiModelProperty(value = "姓名")
    private String name;
    /**
    * 所属部门id
    */
    @ApiModelProperty(value = "所属部门id")
    private Integer department;
    /**
    * 操作类型
    */
    @ApiModelProperty(value = "操作类型",example = "select,insert")
    private String operationType;
    /**
    * 操作标题
    */
    @ApiModelProperty(value = "操作标题")
    private String operationTitle;
    /**
    * 操作内容
    */
    @ApiModelProperty(value = "操作内容")
    private String operationContext;
    /**
    * 操作时间
    */
    @ApiModelProperty(value = "操作时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:dd:ss")
    private Date operationTime;


}