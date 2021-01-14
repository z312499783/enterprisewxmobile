package com.gag.enterprisewxmobile.system.menu.entity;

import java.util.ArrayList;
import java.util.Date;
import java.io.Serializable;
import java.util.List;

/**
 * 菜单权限表(QywxSysMenu)实体类
 *
 * @author makejava
 * @since 2020-12-28 13:40:14
 */
public class QywxSysMenu implements Serializable {
    private static final long serialVersionUID = 875973495235515117L;
    /**
    * 菜单id
    */
    private Integer menuId;
    /**
    * 菜单名称
    */
    private String menuName;
    /**
    * 父菜单id
    */
    private Integer parentId;
    /**
    * 显示顺序
    */
    private Integer orderNum;
    /**
    * 请求地址
    */
    private String url;
    /**
    * 菜单类型(M目录 C菜单 F按钮)
    */
    private String menuType;
    /**
    * 菜单状态(0显示 1隐藏)
    */
    private String visible;
    /**
    * 权限标识
    */
    private String perms;
    /**
    * 创建者
    */
    private String createBy;
    /**
    * 创建时间
    */
    private Date createTime;
    /**
    * 修改者
    */
    private String updateBy;
    /**
    * 修改时间
    */
    private Date updateTime;
    /**
    * 备注
    */
    private String remark;


    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    /** 子菜单 */
    private List<QywxSysMenu> children = new ArrayList<QywxSysMenu>();

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

    public String getVisible() {
        return visible;
    }

    public void setVisible(String visible) {
        this.visible = visible;
    }

    public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms;
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

    public List<QywxSysMenu> getChildren() {
        return children;
    }

    public void setChildren(List<QywxSysMenu> children) {
        this.children = children;
    }
}