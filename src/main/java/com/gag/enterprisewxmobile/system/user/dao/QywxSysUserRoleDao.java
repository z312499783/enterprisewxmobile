package com.gag.enterprisewxmobile.system.user.dao;

import com.gag.enterprisewxmobile.system.user.entity.QywxSysUserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 企业微信用户和角色关联表(QywxSysUserRole)表数据库访问层
 *
 * @author makejava
 * @since 2020-12-28 13:41:47
 */
@Mapper
public interface QywxSysUserRoleDao {


    /**
     * 通过用户ID删除用户和角色关联
     *
     * @param userId 用户ID
     * @return 结果
     */
    public int deleteUserRoleByUserId(String userId);

    /**
     * 批量删除用户和角色关联
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteUserRole(String[] ids);

    /**
     * 通过角色ID查询角色使用数量
     *
     * @param roleId 角色ID
     * @return 结果
     */
    public int countUserRoleByRoleId(Integer roleId);

    /**
     * 批量新增用户角色信息
     *
     * @param userRoleList 用户角色列表
     * @return 结果
     */
    public int batchUserRole(List<QywxSysUserRole> userRoleList);


}