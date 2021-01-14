package com.gag.enterprisewxmobile.monitor.job.service;

import com.gag.enterprisewxmobile.framework.custom.RequestLimit;
import com.gag.enterprisewxmobile.monitor.job.entity.EnterprisewxJob;
import org.quartz.Scheduler;

import java.util.List;

/**
 * 定时任务调度表(EnterprisewxJob)表服务接口
 *
 * @author makejava
 * @since 2020-04-22 11:08:57
 */
public interface EnterprisewxJobService {

    /**
     * 根据任务名称和任务方法查询查询
     * @param jobName
     * @param methodName
     * @return
     */
    EnterprisewxJob queryjob(String jobName,String methodName );

    /**
     * 立即运行任务
     * @param job
     * @return
     */
    int run(EnterprisewxJob job);

    /**
     * 执行一次定时任务
     * @param jobName
     * @param methodName
     * @return
     */
    int executejob(String jobName,String methodName);

    /**
     * 更新任务的时间表达式
     *
     * @param enterprisewxJob 调度信息
     */
    int updateJobCron(EnterprisewxJob enterprisewxJob);

    /**
     * 任务调度状态修改
     * @param enterprisewxJob
     */
    int changeStatus(EnterprisewxJob enterprisewxJob);

    /**
     * 恢复任务
     * @param enterprisewxJob
     * @return
     */
    int resumeJob(EnterprisewxJob enterprisewxJob);

    /**
     * 暂停任务
     * @param enterprisewxJob 调度信息
     */
    int pauseJob(EnterprisewxJob enterprisewxJob);


    /**
     * 删除定时任务
     * @param jobId
     * @return
     */
    int deleteScheduleJob(int jobId);


    /**
     * 通过定时任务状态status查询数据
     * @param status
     * @return
     */
    List<EnterprisewxJob> querystatus(String status);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param enterprisewxJob 实例对象
     * @return 对象列表
     */
    List<EnterprisewxJob> queryAll(EnterprisewxJob enterprisewxJob);

    /**
     * 通过ID查询单条数据
     *
     * @param jobId 主键
     * @return 实例对象
     */
    EnterprisewxJob queryById(Integer jobId);



    /**
     * 新增数据
     *
     * @param enterprisewxJob 实例对象
     * @return 实例对象
     */
    int insert(EnterprisewxJob enterprisewxJob);



    /**
     * 校验cron表达式是否有效
     *
     * @param cronExpression 表达式
     * @return 结果
     */
    boolean checkCronExpressionIsValid(String cronExpression);

}