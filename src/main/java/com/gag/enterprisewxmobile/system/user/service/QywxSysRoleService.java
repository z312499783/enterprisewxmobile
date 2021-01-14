package com.gag.enterprisewxmobile.system.user.service;

import com.gag.enterprisewxmobile.system.user.entity.QywxSysRole;
import java.util.List;
import java.util.Set;

/**
 * 企业微信角色信息表(QywxSysRole)表服务接口
 *
 * @author makejava
 * @since 2020-12-28 13:41:24
 */
public interface QywxSysRoleService {

    /**
     * 根据条件分页查询角色数据
     *
     * @param role 角色信息
     * @return 角色数据集合信息
     */
    public List<QywxSysRole> selectRoleList(QywxSysRole role);

    /**
     * 根据用户ID查询角色
     *
     * @param userId 用户ID
     * @return 权限列表
     */
    public Set<String> selectRoleKeys(String userId);

    public String selectRolekey(String userid);

    /**
     * 根据用户ID查询角色
     *
     * @param userId 用户ID
     * @return 角色列表
     */
    public List<QywxSysRole> selectRolesByUserId(String userId);

    /**
     * 查询所有角色
     *
     * @return 角色列表
     */
    public List<QywxSysRole> selectRoleAll();

    /**
     * 通过角色ID查询角色
     *
     * @param roleId 角色ID
     * @return 角色对象信息
     */
    public QywxSysRole selectRoleById(Integer roleId);

    /**
     * 通过角色ID删除角色
     *
     * @param roleId 角色ID
     * @return 结果
     */
    public boolean deleteRoleById(Integer roleId);

    /**
     * 批量删除角色用户信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     * @throws Exception 异常
     */
    public int deleteRoleByIds(String ids) throws Exception;

    /**
     * 新增保存角色信息
     *
     * @param role 角色信息
     * @return 结果
     */
    public int insertRole(QywxSysRole role);

    /**
     * 修改保存角色信息
     *
     * @param role 角色信息
     * @return 结果
     */
    public int updateRole(QywxSysRole role);

    /**
     * 修改数据权限信息
     *
     * @param role 角色信息
     * @return 结果
     */
    public int updateRule(QywxSysRole role);

    /**
     * 校验角色名称是否唯一
     *
     * @param role 角色信息
     * @return 结果
     */
    public Boolean checkRoleNameUnique(QywxSysRole role);

    /**
     * 校验角色权限是否唯一
     *
     * @param role 角色信息
     * @return 结果
     */
    public Boolean checkRoleKeyUnique(QywxSysRole role);

    /**
     * 通过角色ID查询角色使用数量
     *
     * @param roleId 角色ID
     * @return 结果
     */
    public int countUserRoleByRoleId(Integer roleId);

}