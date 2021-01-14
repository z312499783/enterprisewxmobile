package com.gag.enterprisewxmobile.monitor.job.service;

import com.gag.enterprisewxmobile.monitor.job.entity.EnterprisewxJobLog;
import java.util.List;

/**
 * 定时任务调度日志表(EnterprisewxJobLog)表服务接口
 *
 * @author makejava
 * @since 2020-04-22 11:09:31
 */
public interface EnterprisewxJobLogService {

    /**
     * 通过ID查询单条数据
     *
     * @param jobLogId 主键
     * @return 实例对象
     */
    EnterprisewxJobLog queryById(Integer jobLogId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<EnterprisewxJobLog> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param enterprisewxJobLog 实例对象
     * @return 实例对象
     */
    EnterprisewxJobLog insert(EnterprisewxJobLog enterprisewxJobLog);

    /**
     * 修改数据
     *
     * @param enterprisewxJobLog 实例对象
     * @return 实例对象
     */
    EnterprisewxJobLog update(EnterprisewxJobLog enterprisewxJobLog);

    /**
     * 通过主键删除数据
     *
     * @param jobLogId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer jobLogId);

}