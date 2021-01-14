package com.gag.enterprisewxmobile.monitor.job.service.impl;

import com.gag.enterprisewxmobile.monitor.job.entity.EnterprisewxJobLog;
import com.gag.enterprisewxmobile.monitor.job.dao.EnterprisewxJobLogDao;
import com.gag.enterprisewxmobile.monitor.job.service.EnterprisewxJobLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 定时任务调度日志表(EnterprisewxJobLog)表服务实现类
 *
 * @author makejava
 * @since 2020-04-22 11:09:31
 */
@Service("enterprisewxJobLogService")
public class EnterprisewxJobLogServiceImpl implements EnterprisewxJobLogService {

    @Resource
    private EnterprisewxJobLogDao enterprisewxJobLogDao;

    /**
     * 通过ID查询单条数据
     *
     * @param jobLogId 主键
     * @return 实例对象
     */
    @Override
    public EnterprisewxJobLog queryById(Integer jobLogId) {
        return this.enterprisewxJobLogDao.queryById(jobLogId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<EnterprisewxJobLog> queryAllByLimit(int offset, int limit) {
        return this.enterprisewxJobLogDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param enterprisewxJobLog 实例对象
     * @return 实例对象
     */
    @Override
    public EnterprisewxJobLog insert(EnterprisewxJobLog enterprisewxJobLog) {
        this.enterprisewxJobLogDao.insert(enterprisewxJobLog);
        return enterprisewxJobLog;
    }

    /**
     * 修改数据
     *
     * @param enterprisewxJobLog 实例对象
     * @return 实例对象
     */
    @Override
    public EnterprisewxJobLog update(EnterprisewxJobLog enterprisewxJobLog) {
        this.enterprisewxJobLogDao.update(enterprisewxJobLog);
        return this.queryById(enterprisewxJobLog.getJobLogId());
    }

    /**
     * 通过主键删除数据
     *
     * @param jobLogId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer jobLogId) {
        return this.enterprisewxJobLogDao.deleteById(jobLogId) > 0;
    }
}