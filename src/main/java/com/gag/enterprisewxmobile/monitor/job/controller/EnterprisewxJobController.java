package com.gag.enterprisewxmobile.monitor.job.controller;

import com.gag.enterprisewxmobile.framework.custom.RequestLimit;
import com.gag.enterprisewxmobile.monitor.job.entity.EnterprisewxJob;
import com.gag.enterprisewxmobile.monitor.job.service.EnterprisewxJobService;
import com.gag.enterprisewxmobile.tool.common.BaseController;
import com.gag.enterprisewxmobile.tool.common.JSONResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.gag.enterprisewxmobile.tool.common.JSONResult.*;

/**
 * 定时任务调度表(EnterprisewxJob)表控制层
 *
 * @author makejava
 * @since 2020-04-22 11:08:58
 */
@Controller
@RequestMapping("enterprisewxJob")
public class EnterprisewxJobController extends BaseController {

    /**
     * 服务对象
     */
    @Resource
    private EnterprisewxJobService enterprisewxJobService;


    /**
     * 添加定时任务数据
     * @param enterprisewxJob
     * @return
     */
    @RequiresPermissions("job:insert")
    @RequestMapping("insert")
    @ResponseBody
    public JSONResult insertjob(EnterprisewxJob enterprisewxJob){
        int i = this.enterprisewxJobService.insert(enterprisewxJob);
        return custom(i,enterprisewxJob);
    }



    /**
     * 任务调度状态修改
     * @param enterprisewxJob
     */
    @RequiresPermissions("job:changeStatus")
    @RequestMapping("changeStatus")
    @ResponseBody
    public JSONResult changeStatus(EnterprisewxJob enterprisewxJob){
        int i = this.enterprisewxJobService.updateJobCron(enterprisewxJob);
        return custom(i,enterprisewxJob);
    };


    /**
     * 删除定时任务
     * @param jobId
     * @return
     */
    public JSONResult deleteScheduleJob(int jobId){
        int i = this.enterprisewxJobService.deleteScheduleJob(jobId);
        EnterprisewxJob enterprisewxJob = enterprisewxJobService.queryById(jobId);
        return custom(i,enterprisewxJob);
    };

    /**
     * 定时任务立即执行一次
     * @param enterprisewx
     * @return
     */
    @RequiresPermissions("job:run")
    @RequestMapping("run")
    @ResponseBody
    public JSONResult run(EnterprisewxJob enterprisewx){
        EnterprisewxJob enterprisewxJob = this.enterprisewxJobService.queryById(enterprisewx.getJobId());
        int run = this.enterprisewxJobService.run(enterprisewxJob);
        return custom(run,enterprisewx);
    }


    /**
     * 执行一次定时任务
     */
    @RequestLimit(count=3,time=3600000)
    @RequestMapping("executejob")
    @ResponseBody
    public String executejob(HttpServletRequest request){
        String jobName = request.getParameter("jobName");
        String methodName = request.getParameter("methodName");
        int run = 0;
        if (!(jobName.equals(null)||methodName.equals(null))){
            run = enterprisewxJobService.executejob(jobName, methodName);
        }
        if (run==1){
            return String.valueOf(run);
        }else {
            return "执行定时任务失败,更新次数过多";
        }
    };

    /**
     * 根据enterprisewxJob条件查询数据
     * @param enterprisewxJob
     * @return
     */
    @RequiresPermissions("job:select")
    @PostMapping("queryall")
    @ResponseBody
    public JSONResult queryall(EnterprisewxJob enterprisewxJob){
        //分页查询
        startPage();
        List<EnterprisewxJob> list = this.enterprisewxJobService.queryAll(enterprisewxJob);
       return success(list);
    }


    /**
     * 修改
     * @param enterprisewxJob
     * @return
     */
    @RequiresPermissions("job:edit")
    @PostMapping("edit")
    @ResponseBody
    public JSONResult edit(EnterprisewxJob enterprisewxJob){
        int i = this.enterprisewxJobService.updateJobCron(enterprisewxJob);
        return custom(i,enterprisewxJob);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @RequiresPermissions("job:selectOne")
    @PostMapping("selectOne")
    @ResponseBody
    public JSONResult selectOne(Integer id) {
        EnterprisewxJob enterprisewxJob = this.enterprisewxJobService.queryById(id);
        return success(enterprisewxJob);
    }



}