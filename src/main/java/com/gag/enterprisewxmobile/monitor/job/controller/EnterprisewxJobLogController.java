package com.gag.enterprisewxmobile.monitor.job.controller;

import com.gag.enterprisewxmobile.monitor.job.entity.EnterprisewxJobLog;
import com.gag.enterprisewxmobile.monitor.job.service.EnterprisewxJobLogService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 定时任务调度日志表(EnterprisewxJobLog)表控制层
 *
 * @author makejava
 * @since 2020-04-22 11:09:31
 */
@RestController
@RequestMapping("enterprisewxJobLog")
public class EnterprisewxJobLogController {
    /**
     * 服务对象
     */
    @Resource
    private EnterprisewxJobLogService enterprisewxJobLogService;


}