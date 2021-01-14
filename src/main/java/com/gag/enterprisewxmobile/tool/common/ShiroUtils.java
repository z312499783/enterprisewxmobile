package com.gag.enterprisewxmobile.tool.common;

import com.gag.enterprisewxmobile.framework.config.CustomRealm;
import com.gag.enterprisewxmobile.monitor.job.utils.BeanUtils;
import com.gag.enterprisewxmobile.system.user.entity.QywxSysUser;
import com.gag.enterprisewxmobile.system.user.entity.Usermsg;
import com.gag.enterprisewxmobile.tool.common.utils.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.subject.Subject;

public class ShiroUtils {

    public static Subject getSubject()
    {
        return SecurityUtils.getSubject();
    }

    public static String getLoginName()
    {
        return getSysUser().getUserid();
    }

    public static void clearCachedAuthorizationInfo()
    {
        RealmSecurityManager rsm = (RealmSecurityManager) SecurityUtils.getSecurityManager();
        CustomRealm realm = (CustomRealm) rsm.getRealms().iterator().next();
        realm.clearCachedAuthorizationInfo();
    }

    public static QywxSysUser getSysUser()
    {
        QywxSysUser user = null;
        Object obj = getSubject().getPrincipal();
        if (StringUtils.isNotNull(obj))
        {
            user = new QywxSysUser();
            BeanUtils.copyBeanProp(user, obj);
        }
        return user;
    }
}
