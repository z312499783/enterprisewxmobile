package com.gag.enterprisewxmobile.system.user.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.io.Serializable;

/**
 * 企业微信角色信息表(QywxSysRole)实体类
 *
 * @author makejava
 * @since 2020-12-28 13:41:24
 */
@Getter
@Setter
public class QywxSysRole implements Serializable {
    private static final long serialVersionUID = 990247229242204884L;
    /**
    * 角色ID
    */
    private Integer roleId;
    /**
    * 角色名称
    */
    private String roleName;
    /**
    * 角色权限字符串
    */
    private String roleKey;
    /**
    * 显示顺序
    */
    private String roleSort;
    /**
    * 数据范围（1：全部数据权限 2：自定义数据权限）
    */
    private String dataScope;
    /**
    * 角色状态（0正常 1停用）
    */
    private String status;
    /**
    * 删除标志（0代表存在 2代表删除）
    */
    private String delFlag;
    /**
    * 创建者
    */
    private String createBy;
    /**
    * 创建时间
    */
    private Date createTime;
    /**
    * 更新者
    */
    private String updateBy;
    /**
    * 更新时间
    */
    private Date updateTime;
    /**
    * 备注
    */
    private String remark;

    /** 用户是否存在此角色标识 默认不存在 */
    private boolean flag = false;

    /** 菜单组 */
    private Integer[] menuIds;

    /** 部门组（数据权限） */
    private Integer[] deptIds;



}