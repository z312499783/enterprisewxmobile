package com.gag.enterprisewxmobile.tool.common;

import com.gag.enterprisewxmobile.system.shoppos.entity.ShopPosLog;
import com.gag.enterprisewxmobile.system.shoppos.service.ShopPosLogService;

import java.util.TimerTask;

public class AsyncFactory {
    /**
     * 操作日志记录
     *
     * @param shopPosLog 操作日志信息
     * @return 任务task
     */
    public static TimerTask recordOper(final ShopPosLog shopPosLog)
    {
        return new TimerTask()
        {
            @Override
            public void run()
            {
                SpringUtils.getBean(ShopPosLogService.class).insert(shopPosLog);
            }
        };
    }
}
