package com.gag.enterprisewxmobile.system.menu.service;

import com.gag.enterprisewxmobile.system.menu.entity.QywxSysMenu;
import com.gag.enterprisewxmobile.system.user.entity.QywxSysRole;
import com.gag.enterprisewxmobile.system.user.entity.QywxSysUser;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 菜单权限表(QywxSysMenu)表服务接口
 *
 * @author makejava
 * @since 2020-12-28 13:40:15
 */
public interface QywxSysMenuService {

    /**
     * 根据用户ID查询菜单
     *
     * @param user 用户信息
     * @return 菜单列表
     */
    public List<QywxSysMenu> selectMenusByUser(QywxSysUser user);

    /**
     * 查询系统菜单列表
     *
     * @param menu 菜单信息
     * @return 菜单列表
     */
    public List<QywxSysMenu> selectMenuList(QywxSysMenu menu);

    /**
     * 查询菜单集合
     *
     * @return 所有菜单信息
     */
    public List<QywxSysMenu> selectMenuAll();

    /**
     * 根据用户ID查询权限
     *
     * @param userId 用户ID
     * @return 权限列表
     */
    public Set<String> selectPermsByUserId(String userId);

    /**
     * 根据角色ID查询菜单
     *
     * @param role 角色对象
     * @return 菜单列表
     */
    public List<Map<String, Object>> roleMenuTreeData(QywxSysRole role);

    /**
     * 查询所有菜单信息
     *
     * @return 菜单列表
     */
    public List<Map<String, Object>> menuTreeData();

    /**
     * 查询系统所有权限
     *
     * @return 权限列表
     */
    public Map<String, String> selectPermsAll();

    /**
     * 删除菜单管理信息
     *
     * @param menuId 菜单ID
     * @return 结果
     */
    public int deleteMenuById(Integer menuId);

    /**
     * 根据菜单ID查询信息
     *
     * @param menuId 菜单ID
     * @return 菜单信息
     */
    public QywxSysMenu selectMenuById(Integer menuId);

    /**
     * 查询菜单数量
     *
     * @param parentId 菜单父ID
     * @return 结果
     */
    public int selectCountMenuByParentId(Integer parentId);

    /**
     * 查询菜单使用数量
     *
     * @param menuId 菜单ID
     * @return 结果
     */
    public int selectCountRoleMenuByMenuId(Integer menuId);

    /**
     * 新增保存菜单信息
     *
     * @param menu 菜单信息
     * @return 结果
     */
    public int insertMenu(QywxSysMenu menu);

    /**
     * 修改保存菜单信息
     *
     * @param menu 菜单信息
     * @return 结果
     */
    public int updateMenu(QywxSysMenu menu);

    /**
     * 校验菜单名称是否唯一
     *
     * @param menu 菜单信息
     * @return 结果
     */
    public Boolean checkMenuNameUnique(QywxSysMenu menu);

}