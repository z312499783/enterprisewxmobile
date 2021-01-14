package com.gag.enterprisewxmobile.system.sendAppMsg.controller;

import com.gag.enterprisewxmobile.system.sendAppMsg.entity.QywxSendappmsgLog;
import com.gag.enterprisewxmobile.system.sendAppMsg.service.QywxSendappmsgLogService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 定时任务调度日志表(QywxSendappmsgLog)表控制层
 *
 * @author makejava
 * @since 2020-06-01 10:27:57
 */

public class QywxSendappmsgLogController {
    /**
     * 服务对象
     */
    @Resource
    private QywxSendappmsgLogService qywxSendappmsgLogService;



}