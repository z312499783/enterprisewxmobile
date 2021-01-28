package com.gag.enterprisewxmobile.monitor.job.service.impl;

import com.gag.enterprisewxmobile.monitor.job.dao.EnterprisewxJobDao;
import com.gag.enterprisewxmobile.monitor.job.entity.EnterprisewxJob;
import com.gag.enterprisewxmobile.monitor.job.service.EnterprisewxJobService;
import com.gag.enterprisewxmobile.monitor.job.utils.CronUtils;
import com.gag.enterprisewxmobile.monitor.job.utils.ScheduleConstants;
import com.gag.enterprisewxmobile.monitor.job.utils.ScheduleRunnable;
import com.gag.enterprisewxmobile.monitor.job.utils.ScheduleUtils;
import com.gag.enterprisewxmobile.tool.common.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.quartz.CronTrigger;
import org.quartz.Scheduler;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 定时任务调度表(EnterprisewxJob)表服务实现类
 *
 * @author makejava
 * @since 2020-04-22 11:08:58
 */
@Slf4j
@Service("enterprisewxJobService")
public class EnterprisewxJobServiceImpl extends BaseController implements EnterprisewxJobService {

    @Resource
    private Scheduler scheduler;

    @Resource
    private EnterprisewxJobDao enterprisewxJobDao;

    private ExecutorService service = Executors.newSingleThreadExecutor();


    /**
     * 项目启动时，初始化定时器,查询status为0的数据,0为运行,1为暂停
     */
    //@PostConstruct
    public void init()
    {
        List<EnterprisewxJob> jobList = enterprisewxJobDao.queryAll(null);
        for (EnterprisewxJob job : jobList)
        {
            CronTrigger cronTrigger = ScheduleUtils.getCronTrigger(scheduler, job.getJobId());
            // 如果不存在，则创建
            if (cronTrigger == null)
            {
                ScheduleUtils.createScheduleJob(scheduler, job);
            }
            else
            {
                ScheduleUtils.updateScheduleJob(scheduler, job);
            }
        }
    }

    /**
     * 立即运行任务
     * @param job
     * @return
     */
    @Override
    public int run(EnterprisewxJob job){
        return ScheduleUtils.run(scheduler,job);
    }

    /**
     * 执行一次定时任务
     * @param jobName
     * @param methodName
     */
    @Override
    public int executejob(String jobName,String methodName){
        int run = 0;
        try {
            //更新Accesstoken,调用定时任务ExamineAccesstokenEndTime()更新Accesstoken
            EnterprisewxJob queryjob = enterprisewxJobDao.queryjob(jobName, methodName);
            //立即执行任务
            run = ScheduleUtils.run(scheduler, queryjob);
            ScheduleRunnable task = new ScheduleRunnable(queryjob.getJobName(), queryjob.getMethodName(), queryjob.getMethodParams());
            Future<?> future = service.submit(task);
            future.get();
        } catch (Exception e) {
            log.error("执行定时任务失败:"+e);
            e.printStackTrace();
        }
        return run;
    };

    /**
     * 更新任务的时间表达式
     *
     * @param enterprisewxJob 调度信息
     */
    @Override
    public int updateJobCron(EnterprisewxJob enterprisewxJob)
    {
        enterprisewxJob.setUpdateBy(getSysUser().getName());
        int rows = enterprisewxJobDao.update(enterprisewxJob);
        if (rows > 0)
        {
            ScheduleUtils.updateScheduleJob(scheduler, enterprisewxJob);
        }
        return rows;
    }

    /**
     * 任务调度状态修改
     * @param enterprisewxJob
     */
    @Override
    public int changeStatus(EnterprisewxJob enterprisewxJob){
        int rows = 0;
        String status = enterprisewxJob.getStatus();
        if (ScheduleConstants.Status.NORMAL.getValue().equals(status))
        {
            rows = resumeJob(enterprisewxJob);
        }
        else if (ScheduleConstants.Status.PAUSE.getValue().equals(status))
        {
            rows = pauseJob(enterprisewxJob);
        }
        return rows;
    };

    /**
     * 恢复任务
     * @param enterprisewxJob 调度信息
     */
    @Override
    public int resumeJob(EnterprisewxJob enterprisewxJob)
    {
        enterprisewxJob.setStatus(ScheduleConstants.Status.NORMAL.getValue());
        int rows = enterprisewxJobDao.update(enterprisewxJob);
        if (rows > 0)
        {
            ScheduleUtils.resumeJob(scheduler, enterprisewxJob.getJobId());
        }
        return rows;
    }

    /**
     * 暂停任务
     *
     * @param enterprisewxJob 调度信息
     */
    @Override
    public int pauseJob(EnterprisewxJob enterprisewxJob)
    {
        enterprisewxJob.setStatus(ScheduleConstants.Status.PAUSE.getValue());
        int rows = enterprisewxJobDao.update(enterprisewxJob);
        if (rows > 0)
        {
            ScheduleUtils.pauseJob(scheduler, enterprisewxJob.getJobId());
        }
        return rows;
    }


    /**
     * 删除定时任务
     * @param jobId
     * @return
     */
    @Override
    public int deleteScheduleJob(int jobId){
        int i = enterprisewxJobDao.deleteById(jobId);
        if (i>0){
            ScheduleUtils.deleteScheduleJob(scheduler,jobId);
        }
        return i;
    };


    /**
     * 根据任务名称和任务方法查询查询
     * @param jobName
     * @param methodName
     * @return
     */
    @Override
    public EnterprisewxJob queryjob(String jobName,String methodName ){
        return this.enterprisewxJobDao.queryjob(jobName,methodName);
    };

    /**
     * 通过实体作为筛选条件查询
     *
     * @param enterprisewxJob 实例对象
     * @return 对象列表
     */
    @Override
    public List<EnterprisewxJob> queryAll(EnterprisewxJob enterprisewxJob){
        return this.enterprisewxJobDao.queryAll(enterprisewxJob);
    };

    /**
     * 通过定时任务状态status查询数据
     * @param status
     * @return
     */
    @Override
    public List<EnterprisewxJob> querystatus(String status){
        return this.enterprisewxJobDao.querystatus(status);
    };

    /**
     * 通过ID查询单条数据
     *
     * @param jobId 主键
     * @return 实例对象
     */
    @Override
    public EnterprisewxJob queryById(Integer jobId) {
        return this.enterprisewxJobDao.queryById(jobId);
    }


    /**
     * 新增任务
     * @param enterprisewxJob 调度信息 调度信息
     */
    @Override
    public int insert(EnterprisewxJob enterprisewxJob)
    {
        enterprisewxJob.setStatus(ScheduleConstants.Status.PAUSE.getValue());
        int rows = this.enterprisewxJobDao.insert(enterprisewxJob);
        if (rows > 0)
        {
            ScheduleUtils.createScheduleJob(scheduler, enterprisewxJob);
        }
        return rows;
    }

    /**
     * 校验cron表达式是否有效
     *
     * @param cronExpression 表达式
     * @return 结果
     */
    @Override
    public boolean checkCronExpressionIsValid(String cronExpression)
    {
        return CronUtils.isValid(cronExpression);
    }



}