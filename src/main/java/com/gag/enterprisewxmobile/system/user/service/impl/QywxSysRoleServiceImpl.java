package com.gag.enterprisewxmobile.system.user.service.impl;

import com.gag.enterprisewxmobile.system.user.dao.QywxSysRoleMenuDao;
import com.gag.enterprisewxmobile.system.user.dao.QywxSysUserRoleDao;
import com.gag.enterprisewxmobile.system.user.entity.QywxSysRole;
import com.gag.enterprisewxmobile.system.user.dao.QywxSysRoleDao;
import com.gag.enterprisewxmobile.system.user.entity.QywxSysRoleMenu;
import com.gag.enterprisewxmobile.system.user.service.QywxSysRoleService;
import com.gag.enterprisewxmobile.tool.common.utils.Convert;
import com.gag.enterprisewxmobile.tool.common.ShiroUtils;
import com.gag.enterprisewxmobile.tool.common.utils.StringUtils;
import com.gag.enterprisewxmobile.tool.common.exception.BusinessException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * 企业微信角色信息表(QywxSysRole)表服务实现类
 *
 * @author makejava
 * @since 2020-12-28 13:41:24
 */
@Service("qywxSysRoleService")
public class QywxSysRoleServiceImpl implements QywxSysRoleService {

    @Resource
    private QywxSysRoleDao qywxSysRoleDao;

    @Resource
    private QywxSysRoleMenuDao qywxSysRoleMenuDao;

    @Resource
    private QywxSysUserRoleDao qywxSysUserRoleDao;

    /**
     * 根据条件分页查询角色数据
     *
     * @param role 角色信息
     * @return 角色数据集合信息
     */
    @Override
    public List<QywxSysRole> selectRoleList(QywxSysRole role)
    {
        return qywxSysRoleDao.selectRoleList(role);
    }

    /**
     * 根据用户ID查询权限
     *
     * @param userId 用户ID
     * @return 权限列表
     */
    @Override
    public Set<String> selectRoleKeys(String userId)
    {
        List<QywxSysRole> perms = qywxSysRoleDao.selectRolesByUserId(userId);
        Set<String> permsSet = new HashSet<>();
        for (QywxSysRole perm : perms)
        {
            if (StringUtils.isNotNull(perms))
            {
                permsSet.addAll(Arrays.asList(perm.getRoleKey().trim().split(",")));
            }
        }
        return permsSet;
    }

    @Override
    public String selectRolekey(String userid){
        return qywxSysRoleDao.selectRolekey(userid);
    };

    /**
     * 根据用户ID查询角色
     *
     * @param userId 用户ID
     * @return 角色列表
     */
    @Override
    public List<QywxSysRole> selectRolesByUserId(String userId)
    {
        List<QywxSysRole> userRoles = qywxSysRoleDao.selectRolesByUserId(userId);
        List<QywxSysRole> roles = selectRoleAll();
        for (QywxSysRole role : roles)
        {
            for (QywxSysRole userRole : userRoles)
            {
                if (role.getRoleId().longValue() == userRole.getRoleId().longValue())
                {
                    role.setFlag(true);
                    break;
                }
            }
        }
        return roles;
    }

    /**
     * 查询所有角色
     *
     * @return 角色列表
     */
    @Override
    public List<QywxSysRole> selectRoleAll()
    {
        return selectRoleList(new QywxSysRole());
    }

    /**
     * 通过角色ID查询角色
     *
     * @param roleId 角色ID
     * @return 角色对象信息
     */
    @Override
    public QywxSysRole selectRoleById(Integer roleId)
    {
        return qywxSysRoleDao.selectRoleById(roleId);
    }

    /**
     * 通过角色ID删除角色
     *
     * @param roleId 角色ID
     * @return 结果
     */
    @Override
    public boolean deleteRoleById(Integer roleId)
    {
        return qywxSysRoleDao.deleteRoleById(roleId) > 0 ? true : false;
    }

    /**
     * 批量删除角色信息
     *
     * @param ids 需要删除的数据ID
     * @throws Exception
     */
    @Override
    public int deleteRoleByIds(String ids) throws BusinessException
    {
        Integer[] roleIds = Convert.toIntArray(ids);
        for (Integer roleId : roleIds)
        {
            QywxSysRole role = selectRoleById(roleId);
            if (countUserRoleByRoleId(roleId) > 0)
            {
                throw new BusinessException(String.format("%1$s已分配,不能删除", role.getRoleName()));
            }
        }
        return qywxSysRoleDao.deleteRoleByIds(roleIds);
    }

    /**
     * 新增保存角色信息
     *
     * @param role 角色信息
     * @return 结果
     */
    @Override
    public int insertRole(QywxSysRole role)
    {
        role.setCreateBy(ShiroUtils.getLoginName());
        // 新增角色信息
        qywxSysRoleDao.insertRole(role);
        ShiroUtils.clearCachedAuthorizationInfo();
        return insertRoleMenu(role);
    }

    /**
     * 修改保存角色信息
     *
     * @param role 角色信息
     * @return 结果
     */
    @Override
    public int updateRole(QywxSysRole role)
    {
        role.setUpdateBy(ShiroUtils.getLoginName());
        // 修改角色信息
        qywxSysRoleDao.updateRole(role);
        ShiroUtils.clearCachedAuthorizationInfo();
        // 删除角色与菜单关联
        qywxSysRoleMenuDao.deleteRoleMenuByRoleId(role.getRoleId());
        return insertRoleMenu(role);
    }

    /**
     * 修改数据权限信息
     *
     * @param role 角色信息
     * @return 结果
     */
    @Override
    public int updateRule(QywxSysRole role)
    {
        role.setUpdateBy(ShiroUtils.getLoginName());
        // 修改角色信息
        return qywxSysRoleDao.updateRole(role);
    }

    /**
     * 新增角色菜单信息
     *
     * @param role 角色对象
     */
    public int insertRoleMenu(QywxSysRole role)
    {
        int rows = 1;
        // 新增用户与角色管理
        List<QywxSysRoleMenu> list = new ArrayList<QywxSysRoleMenu>();
        for (int menuId : role.getMenuIds())
        {
            QywxSysRoleMenu rm = new QywxSysRoleMenu();
            rm.setRoleId(role.getRoleId());
            rm.setMenuId(menuId);
            list.add(rm);
        }
        if (list.size() > 0)
        {
            rows = qywxSysRoleMenuDao.batchRoleMenu(list);
        }
        return rows;
    }


    /**
     * 校验角色名称是否唯一
     *
     * @param role 角色信息
     * @return 结果
     */
    @Override
    public Boolean checkRoleNameUnique(QywxSysRole role)
    {
        Long roleId = StringUtils.isNull(role.getRoleId()) ? -1L : role.getRoleId();
        QywxSysRole info = qywxSysRoleDao.checkRoleNameUnique(role.getRoleName());
        if (StringUtils.isNotNull(info) && info.getRoleId().longValue() != roleId.longValue())
        {
            return true;
        }
        return false;
    }

    /**
     * 校验角色权限是否唯一
     *
     * @param role 角色信息
     * @return 结果
     */
    @Override
    public Boolean checkRoleKeyUnique(QywxSysRole role)
    {
        Long roleId = StringUtils.isNull(role.getRoleId()) ? -1L : role.getRoleId();
        QywxSysRole info = qywxSysRoleDao.checkRoleKeyUnique(role.getRoleKey());
        if (StringUtils.isNotNull(info) && info.getRoleId().longValue() != roleId.longValue())
        {
            return true;
        }
        return false;
    }

    /**
     * 通过角色ID查询角色使用数量
     *
     * @param roleId 角色ID
     * @return 结果
     */
    @Override
    public int countUserRoleByRoleId(Integer roleId)
    {
        return qywxSysUserRoleDao.countUserRoleByRoleId(roleId);
    }

}