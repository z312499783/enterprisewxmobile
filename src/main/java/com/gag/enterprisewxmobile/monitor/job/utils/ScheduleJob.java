package com.gag.enterprisewxmobile.monitor.job.utils;

import com.gag.enterprisewxmobile.monitor.job.entity.EnterprisewxJob;
import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.PersistJobDataAfterExecution;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 定时任务
 */
@Slf4j
@DisallowConcurrentExecution
public class ScheduleJob extends QuartzJobBean
{

    private ExecutorService service = Executors.newSingleThreadExecutor();

    @Override
    protected void executeInternal(JobExecutionContext context) {
        EnterprisewxJob job = new EnterprisewxJob();
        BeanUtils.copyBeanProp(job, context.getMergedJobDataMap().get(ScheduleConstants.TASK_PROPERTIES));

        try
        {
            // 执行任务
            log.info("任务开始执行 - 名称：{} 方法：{}"+job.getJobName()+"方法名字:"+job.getMethodName());
            ScheduleRunnable task = new ScheduleRunnable(job.getJobName(), job.getMethodName(), job.getMethodParams());
            Future<?> future = service.submit(task);
            future.get();
            log.info("任务执行结束 - 名称："+job.getJobName());
        }
        catch (Exception e)
        {
            log.error("任务执行失败 - 名称：{} 方法：{}"+ job.getJobName()+"方法名字:"+ job.getMethodName());
            log.error("任务执行异常  - ："+e);

        }
    }
}
