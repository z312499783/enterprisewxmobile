package com.gag.enterprisewxmobile.monitor.job.dao;

import com.gag.enterprisewxmobile.monitor.job.entity.EnterprisewxJobLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 定时任务调度日志表(EnterprisewxJobLog)表数据库访问层
 *
 * @author makejava
 * @since 2020-04-22 11:09:31
 */
@Mapper
public interface EnterprisewxJobLogDao {

    /**
     * 通过ID查询单条数据
     *
     * @param jobLogId 主键
     * @return 实例对象
     */
    EnterprisewxJobLog queryById(Integer jobLogId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<EnterprisewxJobLog> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param enterprisewxJobLog 实例对象
     * @return 对象列表
     */
    List<EnterprisewxJobLog> queryAll(EnterprisewxJobLog enterprisewxJobLog);

    /**
     * 新增数据
     *
     * @param enterprisewxJobLog 实例对象
     * @return 影响行数
     */
    int insert(EnterprisewxJobLog enterprisewxJobLog);

    /**
     * 修改数据
     *
     * @param enterprisewxJobLog 实例对象
     * @return 影响行数
     */
    int update(EnterprisewxJobLog enterprisewxJobLog);

    /**
     * 通过主键删除数据
     *
     * @param jobLogId 主键
     * @return 影响行数
     */
    int deleteById(Integer jobLogId);

}