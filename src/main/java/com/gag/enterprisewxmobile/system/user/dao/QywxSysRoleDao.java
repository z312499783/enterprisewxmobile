package com.gag.enterprisewxmobile.system.user.dao;

import com.gag.enterprisewxmobile.system.user.entity.QywxSysRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * 企业微信角色信息表(QywxSysRole)表数据库访问层
 *
 * @author makejava
 * @since 2020-12-28 13:41:24
 */
@Mapper
public interface QywxSysRoleDao {

    /**
     * 根据条件分页查询角色数据
     *
     * @param role 角色信息
     * @return 角色数据集合信息
     */
    List<QywxSysRole> selectRoleList(QywxSysRole role);

    /**
     * 根据用户id查询rolekey是否是管理员
     * @param userid
     * @return
     */
    String selectRolekey(String userid);

    /**
     * 根据用户ID查询角色
     *
     * @param userId 用户ID
     * @return 角色列表
     */
    List<QywxSysRole> selectRolesByUserId(String userId);

    /**
     * 通过角色ID查询角色
     *
     * @param roleId 角色ID
     * @return 角色对象信息
     */
    QywxSysRole selectRoleById(Integer roleId);

    /**
     * 通过角色ID删除角色
     *
     * @param roleId 角色ID
     * @return 结果
     */
    int deleteRoleById(Integer roleId);

    /**
     * 批量角色用户信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteRoleByIds(Integer[] ids);

    /**
     * 修改角色信息
     *
     * @param role 角色信息
     * @return 结果
     */
    int updateRole(QywxSysRole role);

    /**
     * 新增角色信息
     *
     * @param role 角色信息
     * @return 结果
     */
    int insertRole(QywxSysRole role);

    /**
     * 校验角色名称是否唯一
     *
     * @param roleName 角色名称
     * @return 角色信息
     */
    QywxSysRole checkRoleNameUnique(String roleName);

    /**
     * 校验角色权限是否唯一
     *
     * @param roleKey 角色权限
     * @return 角色信息
     */
    QywxSysRole checkRoleKeyUnique(String roleKey);

}