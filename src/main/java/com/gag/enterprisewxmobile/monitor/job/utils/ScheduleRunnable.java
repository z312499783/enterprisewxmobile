package com.gag.enterprisewxmobile.monitor.job.utils;

import java.lang.reflect.Method;
import com.gag.enterprisewxmobile.framework.config.SpringContextUtil;
import org.springframework.util.ReflectionUtils;

/**
 * 执行定时任务
 */
public class ScheduleRunnable implements Runnable
{
    private Object target;
    private Method method;
    private String params;

    public ScheduleRunnable(String beanName, String methodName, String params)
            throws NoSuchMethodException, SecurityException
    {
        this.target = SpringContextUtil.getBean(beanName);
        this.params = params;

        if (SpringContextUtil.isNotEmpty(params))
        {
            this.method = target.getClass().getDeclaredMethod(methodName, String.class);
        }
        else
        {
            this.method = target.getClass().getDeclaredMethod(methodName);
        }
    }

    @Override
    public void run()
    {
        try
        {
            ReflectionUtils.makeAccessible(method);
            if (SpringContextUtil.isNotEmpty(params))
            {
                method.invoke(target, params);
            }
            else
            {
                method.invoke(target);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
