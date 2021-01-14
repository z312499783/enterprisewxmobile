package com.gag.enterprisewxmobile.framework.config;

import com.gag.enterprisewxmobile.system.menu.service.QywxSysMenuService;
import com.gag.enterprisewxmobile.system.user.entity.QywxSysUser;
import com.gag.enterprisewxmobile.system.user.service.QywxSysRoleService;
import com.gag.enterprisewxmobile.system.user.service.QywxSysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

public class CustomRealm extends AuthorizingRealm {

    /**
     * 服务对象
     */
    @Resource
    private QywxSysUserService qywxSysUserService;

    @Resource
    private QywxSysMenuService qywxSysMenuService;

    @Resource
    private QywxSysRoleService qywxSysRoleService;

    /**
     * 执行授权逻辑
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

        Subject subject = SecurityUtils.getSubject();
        //获取当前登录用户
        QywxSysUser user = (QywxSysUser)subject.getPrincipal();
        // 角色列表
        Set<String> roles = new HashSet<String>();
        // 功能列表
        Set<String> menus = new HashSet<String>();

        //当用户角色时admin时添加全部权限
        if (user.isAdmin(qywxSysRoleService.selectRolekey(user.getUserid()))){
            simpleAuthorizationInfo.addRole("admin");
            simpleAuthorizationInfo.addStringPermission("*:*");
        }
        else {
            roles = qywxSysRoleService.selectRoleKeys(user.getUserid());
            menus = qywxSysMenuService.selectPermsByUserId(user.getUserid());
            // 角色加入AuthorizationInfo认证对象
            simpleAuthorizationInfo.setRoles(roles);
            // 权限加入AuthorizationInfo认证对象
            simpleAuthorizationInfo.addStringPermissions(menus);
        }
        return simpleAuthorizationInfo;
    }

    /**
     * 执行认证逻辑
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //编写shiro判断逻辑，判断用户名和密码
        //1、判断用户名
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;

        QywxSysUser user = qywxSysUserService.selectUserById(token.getUsername());

        if(user==null){
            //用户不存在
            return null;//shiro底层会抛出UnkUnknownAccountException
        }
        //2、判断密码
        return new SimpleAuthenticationInfo(user,user.getUserid(),getName());
    }


    /**
     * 清理缓存权限
     */
    public void clearCachedAuthorizationInfo()
    {
        this.clearCachedAuthorizationInfo(SecurityUtils.getSubject().getPrincipals());
    }
}
