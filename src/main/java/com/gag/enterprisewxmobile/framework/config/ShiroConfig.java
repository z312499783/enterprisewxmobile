package com.gag.enterprisewxmobile.framework.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    /**
     * 创建ShiroFilterFactoryBean
     * shiro过滤器
     * @param securityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        //添加shiro内置过滤器
        /**
         * Shiro内置过滤器，可以实现权限相关的拦截器
         *      常用的过滤器
         *      anon：无需认证（登录）可以访问
         *      authc：必须认证才可以访问
         *      user：如果使用rememberMe的功能可以直接访问
         *      perms：该资源必须得到资源权限才可以访问
         *      role：该资源必须得到角色权限才可以访问
         */

        Map<String,String> filterMap = new LinkedHashMap<String,String>();
        filterMap.put("/usermsg/login","anon");
        filterMap.put("/usermsg/index","anon");
        filterMap.put("/usermsg/error","anon");
        filterMap.put("/usermsg/tojob","anon");

        //开放对外获取企业应用accesstoken
        filterMap.put("/approval/queryAccessToken/**","anon");

        //授权过滤器
        //注意，当前授权拦截后，shiro会自动跳转到未授权页面
//        filterMap.put("/mbew/add","perms[mbew:add]");

        //全部请求都拦截
        filterMap.put("/**","authc");

        //修改调整的登录页面
        shiroFilterFactoryBean.setLoginUrl("/login/login");
        //设置未授权提示页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/login/login");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);

        return shiroFilterFactoryBean;
    }

    /**
     * 创建DefaultWebSecurityManager
     */
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(CustomRealm customRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(customRealm);
        return securityManager;
    }


    /**
     * 创建Realm
     */
    @Bean
    public CustomRealm getRealm(){

        return new CustomRealm();
    }

    /**
     * thymeleaf模板引擎和shiro的整合
     * @return
     */
    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }
}
