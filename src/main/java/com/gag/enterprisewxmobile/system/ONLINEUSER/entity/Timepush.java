package com.gag.enterprisewxmobile.system.ONLINEUSER.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * (Timepush)实体类
 *
 * @author makejava
 * @since 2020-09-25 15:18:37
 */
@Getter
@Setter
@ApiModel(value = "工厂进出多长时间",description = "工厂进出多长时间(分钟)")
public class Timepush implements Serializable {
    private static final long serialVersionUID = 487123126416884935L;

    @ApiModelProperty(value = "autoid",required = true)
    private Integer autoid;

    @ApiModelProperty(value = "minute",required = true,example = "60")
    private Integer minute;



}