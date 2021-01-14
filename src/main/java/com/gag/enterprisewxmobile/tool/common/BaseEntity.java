package com.gag.enterprisewxmobile.tool.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.collect.Maps;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

@ApiModel(value = "基础实体",description = "基础实体类")
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 735726368781111191L;

    //搜索值
    @ApiModelProperty(value = "搜索值")
    private String searchValue;

    //创建者
    @ApiModelProperty(value = "创建者")
    private String createBy;

    //创建时间
    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    //更新者
    @ApiModelProperty(value = "更新者")
    private String updateBy;

    //修改时间
    @ApiModelProperty(value = "修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    //备注
    @ApiModelProperty(value = "备注")
    private String remark;

    //请求参数
    @ApiModelProperty(value = "请求参数")
    private Map<String,Object> params;


    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
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

    public Map<String, Object> getParams() {
        if (params==null){
            params = Maps.newHashMap();
        }
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }
}
