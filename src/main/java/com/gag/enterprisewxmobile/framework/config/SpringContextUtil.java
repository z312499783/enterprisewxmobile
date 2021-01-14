package com.gag.enterprisewxmobile.framework.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import static java.util.Objects.isNull;

/**
 * spring工具类 方便在非spring管理环境中获取bean
 */
@Component
public class SpringContextUtil  implements ApplicationContextAware {

    /**
     * 应用环境
     */
    private static ApplicationContext applicationContext;

    /** 空字符串 */
    private static final String NULLSTR = "";

    /**
     *  设置环境
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        SpringContextUtil.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 获取对象
     */
    public static Object getBean(String beanId) throws BeansException {
        return applicationContext.getBean(beanId);
    }

    /**
     * * 判断一个字符串是否为空串
     *
     * @param str String
     * @return true：为空 false：非空
     */
    public static boolean isEmpty(String str)
    {
        return isNull(str) || NULLSTR.equals(str.trim());
    }

    /**
     * * 判断一个字符串是否为非空串
     *
     * @param str String
     * @return true：非空串 false：空串
     */
    public static boolean isNotEmpty(String str)
    {
        return !isEmpty(str);
    }

    /**
     * 根据参数判断是否有,如果有那么以,截取参数到数组中，如果没有则返回null
     * @param method_params
     * @return
     */
    public static String[] getParams(String method_params){
        String regex=",";
        String[] strs = null;
        boolean boo = method_params.contains(regex);
        if (boo==true){
            strs  =method_params.split(regex);
        }
        return strs;
    }
}

