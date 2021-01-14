package com.gag.enterprisewxmobile.tool.common;


import com.alibaba.fastjson.JSONObject;
import com.gag.enterprisewxmobile.system.shoppos.entity.ShopPosLog;
import com.gag.enterprisewxmobile.system.user.entity.QywxSysUser;
import com.gag.enterprisewxmobile.tool.common.utils.ServletUtils;
import com.gag.enterprisewxmobile.tool.common.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * 操作日志记录处理
 *
 * @author zjc
 */
@Slf4j
@Aspect
@Component
public class LogAspect {


    // 配置织入点
    @Pointcut("@annotation(com.gag.enterprisewxmobile.tool.common.Log)")
    public void logPointCut()
    {
    }

    /**
     * 前置通知 用于拦截操作
     *
     * @param joinPoint 切点
     */
    @AfterReturning(pointcut = "logPointCut()")
    public void doBefore(JoinPoint joinPoint)
    {
        handleLog(joinPoint, null);
    }

    protected void handleLog(final JoinPoint joinPoint, final Exception e)
    {

        try
        {
            // 获得注解
            Log controllerLog = getAnnotationLog(joinPoint);
            if (controllerLog == null)
            {
                return;
            }

            // 获取当前的用户
            QywxSysUser currentUser = ShiroUtils.getSysUser();

            // *========数据库日志=========*//
            ShopPosLog shopPosLog= new ShopPosLog();
            if (currentUser != null)
            {
                shopPosLog.setUserid(currentUser.getUserid());
                shopPosLog.setName(currentUser.getName());
                if (StringUtils.isNotEmpty(currentUser.getDepartment().toString())){
                    shopPosLog.setDepartment(currentUser.getDepartment());
                }
            }

            // 设置方法名称
            String className = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();
            shopPosLog.setOperationContext(className + "." + methodName + "()");
            // 处理设置注解上的参数
            getControllerMethodDescription(controllerLog, shopPosLog);
            // 保存数据库
            AsyncManager.me().execute(AsyncFactory.recordOper(shopPosLog));
        }
        catch (Exception exp)
        {
            // 记录本地异常日志
            log.error("==前置通知异常==");
            log.error("异常信息:{}", exp.getMessage());
            exp.printStackTrace();
        }
    }

    /**
     * 是否存在注解，如果存在就获取
     */
    private Log getAnnotationLog(JoinPoint joinPoint) throws Exception
    {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();

        if (method != null)
        {
            return method.getAnnotation(Log.class);
        }
        return null;
    }

    /**
     * 获取注解中对方法的描述信息 用于Controller层注解
     *
     * @return 方法描述
     * @throws Exception
     */
    public void getControllerMethodDescription(Log log, ShopPosLog shopPosLog)
    {
        // 设置action动作
        shopPosLog.setOperationType(log.businessType().name());
        // 设置标题
        shopPosLog.setOperationTitle(log.title());
        // 是否需要保存request，参数和值
        if (log.isSaveRequestData())
        {
            // 获取参数的信息，传入到数据库中。
            setRequestValue(shopPosLog);
        }
    }

    /**
     * 获取请求的参数，放到log中
     *
     * @param shopPosLog
     */
    private void setRequestValue(ShopPosLog shopPosLog)
    {
        Map<String, String[]> map = ServletUtils.getRequest().getParameterMap();
        String params = JSONObject.toJSONString(map);
        shopPosLog.setOperationContext(StringUtils.substring(params, 0, 255));
    }
}
