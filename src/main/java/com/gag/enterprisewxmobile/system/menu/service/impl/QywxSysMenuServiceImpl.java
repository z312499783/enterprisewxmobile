package com.gag.enterprisewxmobile.system.menu.service.impl;

import com.gag.enterprisewxmobile.system.menu.entity.QywxSysMenu;
import com.gag.enterprisewxmobile.system.menu.dao.QywxSysMenuDao;
import com.gag.enterprisewxmobile.system.menu.service.QywxSysMenuService;
import com.gag.enterprisewxmobile.system.user.dao.QywxSysRoleDao;
import com.gag.enterprisewxmobile.system.user.dao.QywxSysRoleMenuDao;
import com.gag.enterprisewxmobile.system.user.entity.QywxSysRole;
import com.gag.enterprisewxmobile.system.user.entity.QywxSysUser;
import com.gag.enterprisewxmobile.tool.common.ShiroUtils;
import com.gag.enterprisewxmobile.tool.common.utils.StringUtils;
import com.gag.enterprisewxmobile.tool.common.utils.TreeUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.MessageFormat;
import java.util.*;

/**
 * 菜单权限表(QywxSysMenu)表服务实现类
 *
 * @author makejava
 * @since 2020-12-28 13:40:15
 */
//@CacheConfig(cacheNames = "qywxSysMenuService")
@Service("qywxSysMenuService")
public class QywxSysMenuServiceImpl implements QywxSysMenuService {


    public static final String PREMISSION_STRING = "perms[\"{0}\"]";

    @Resource
    private QywxSysMenuDao qywxSysMenuDao;

    @Resource
    private QywxSysRoleDao qywxSysRoleDao;

    @Resource
    private QywxSysRoleMenuDao qywxSysRoleMenuDao;

    /**
     * 根据用户查询菜单
     *
     * @param user 用户信息
     * @return 菜单列表
     */
    @Override
    public List<QywxSysMenu> selectMenusByUser(QywxSysUser user)
    {
        List<QywxSysMenu> menus = new LinkedList<QywxSysMenu>();
        // 管理员显示所有菜单信息
        if (user.isAdmin(qywxSysRoleDao.selectRolekey(user.getUserid())))
        {
            menus = qywxSysMenuDao.selectMenuNormalAll();
        }
        else
        {
            menus = qywxSysMenuDao.selectMenusByUserId(user.getUserid());
        }
        return TreeUtils.getChildPerms(menus, 0);
    }

    /**
     * 查询菜单集合
     *
     * @return 所有菜单信息
     */
    @Override
    public List<QywxSysMenu> selectMenuList(QywxSysMenu menu)
    {
        return qywxSysMenuDao.selectMenuList(menu);
    }

    /**
     * 查询菜单集合
     *
     * @return 所有菜单信息
     */
    @Override
    public List<QywxSysMenu> selectMenuAll()
    {
        return qywxSysMenuDao.selectMenuAll();
    }

    /**
     * 根据用户ID查询权限
     *
     * @param userId 用户ID
     * @return 权限列表
     */
    @Override
    public Set<String> selectPermsByUserId(String userId)
    {
        List<String> perms = qywxSysMenuDao.selectPermsByUserId(userId);
        Set<String> permsSet = new HashSet<>();
        for (String perm : perms)
        {
            if (StringUtils.isNotEmpty(perm))
            {
                permsSet.addAll(Arrays.asList(perm.trim().split(",")));
            }
        }
        return permsSet;
    }

    /**
     * 根据角色ID查询菜单
     *
     * @param role 角色对象
     * @return 菜单列表
     */
    @Override
    public List<Map<String, Object>> roleMenuTreeData(QywxSysRole role)
    {
        Integer roleId = role.getRoleId();
        List<Map<String, Object>> trees = new ArrayList<Map<String, Object>>();
        List<QywxSysMenu> menuList = qywxSysMenuDao.selectMenuAll();
        if (StringUtils.isNotNull(roleId))
        {
            List<String> roleMenuList = qywxSysMenuDao.selectMenuTree(roleId);
            trees = getTrees(menuList, true, roleMenuList, true);
        }
        else
        {
            trees = getTrees(menuList, false, null, true);
        }
        return trees;
    }

    /**
     * 查询所有菜单
     *
     * @return 菜单列表
     */
    @Override
    public List<Map<String, Object>> menuTreeData()
    {
        List<Map<String, Object>> trees = new ArrayList<Map<String, Object>>();
        List<QywxSysMenu> menuList = qywxSysMenuDao.selectMenuAll();
        trees = getTrees(menuList, false, null, false);
        return trees;
    }

    /**
     * 查询系统所有权限
     *
     * @return 权限列表
     */
    @Override
    public LinkedHashMap<String, String> selectPermsAll()
    {
        LinkedHashMap<String, String> section = new LinkedHashMap<>();
        List<QywxSysMenu> permissions = qywxSysMenuDao.selectMenuAll();
        if (StringUtils.isNotEmpty(permissions))
        {
            for (QywxSysMenu menu : permissions)
            {
                section.put(menu.getUrl(), MessageFormat.format(PREMISSION_STRING, menu.getPerms()));
            }
        }
        return section;
    }

    /**
     * 对象转菜单树
     *
     * @param menuList 菜单列表
     * @param isCheck 是否需要选中
     * @param roleMenuList 角色已存在菜单列表
     * @param permsFlag 是否需要显示权限标识
     * @return
     */
    public List<Map<String, Object>> getTrees(List<QywxSysMenu> menuList, boolean isCheck, List<String> roleMenuList,
                                              boolean permsFlag)
    {
        List<Map<String, Object>> trees = new ArrayList<Map<String, Object>>();
        for (QywxSysMenu menu : menuList)
        {
            Map<String, Object> deptMap = new HashMap<String, Object>();
            deptMap.put("id", menu.getMenuId());
            deptMap.put("pId", menu.getParentId());
            deptMap.put("name", transMenuName(menu, roleMenuList, permsFlag));
            deptMap.put("title", menu.getMenuName());
            if (isCheck)
            {
                deptMap.put("checked", roleMenuList.contains(menu.getMenuId() + menu.getPerms()));
            }
            else
            {
                deptMap.put("checked", false);
            }
            trees.add(deptMap);
        }
        return trees;
    }

    public String transMenuName(QywxSysMenu menu, List<String> roleMenuList, boolean permsFlag)
    {
        StringBuffer sb = new StringBuffer();
        sb.append(menu.getMenuName());
        if (permsFlag)
        {
            sb.append("<font color=\"#888\">&nbsp;&nbsp;&nbsp;" + menu.getPerms() + "</font>");
        }
        return sb.toString();
    }

    /**
     * 删除菜单管理信息
     *
     * @param menuId 菜单ID
     * @return 结果
     */
    @Override
    public int deleteMenuById(Integer menuId)
    {
        ShiroUtils.clearCachedAuthorizationInfo();
        return qywxSysMenuDao.deleteMenuById(menuId);
    }

    /**
     * 根据菜单ID查询信息
     *
     * @param menuId 菜单ID
     * @return 菜单信息
     */
    @Override
    public QywxSysMenu selectMenuById(Integer menuId)
    {
        return qywxSysMenuDao.selectMenuById(menuId);
    }

    /**
     * 查询子菜单数量
     *
     * @param parentId 菜单ID
     * @return 结果
     */
    @Override
    public int selectCountMenuByParentId(Integer parentId)
    {
        return qywxSysMenuDao.selectCountMenuByParentId(parentId);
    }

    /**
     * 查询菜单使用数量
     *
     * @param menuId 菜单ID
     * @return 结果
     */
    @Override
    public int selectCountRoleMenuByMenuId(Integer menuId)
    {
        return qywxSysRoleMenuDao.selectCountRoleMenuByMenuId(menuId);
    }

    /**
     * 新增保存菜单信息
     *
     * @param menu 菜单信息
     * @return 结果
     */
    @Override
    public int insertMenu(QywxSysMenu menu)
    {
        menu.setCreateBy(ShiroUtils.getLoginName());
        ShiroUtils.clearCachedAuthorizationInfo();
        return qywxSysMenuDao.insertMenu(menu);
    }

    /**
     * 修改保存菜单信息
     *
     * @param menu 菜单信息
     * @return 结果
     */
    @Override
    public int updateMenu(QywxSysMenu menu)
    {
        menu.setUpdateBy(ShiroUtils.getLoginName());
        ShiroUtils.clearCachedAuthorizationInfo();
        return qywxSysMenuDao.updateMenu(menu);
    }

    /**
     * 校验菜单名称是否唯一
     *
     * @param menu 菜单信息
     * @return 结果
     */
    @Override
    public Boolean checkMenuNameUnique(QywxSysMenu menu)
    {
        Long menuId = StringUtils.isNull(menu.getMenuId()) ? -1L : menu.getMenuId();
        QywxSysMenu info = qywxSysMenuDao.checkMenuNameUnique(menu.getMenuName(), menu.getParentId());
        if (StringUtils.isNotNull(info) && info.getMenuId().longValue() != menuId.longValue())
        {
            return true;
        }
        return false;
    }
}