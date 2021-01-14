package com.gag.enterprisewxmobile.monitor.job.dao;

import com.gag.enterprisewxmobile.monitor.job.entity.EnterprisewxJob;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 定时任务调度表(EnterprisewxJob)表数据库访问层
 *
 * @author makejava
 * @since 2020-04-22 11:08:57
 */
@Mapper
public interface EnterprisewxJobDao {

    /**
     * 根据任务名称和任务方法查询查询
     * @param jobName
     * @param methodName
     * @return
     */
    EnterprisewxJob queryjob(@Param("jobName") String jobName,@Param("methodName") String methodName );


    /**
     * 通过定时任务状态status查询数据
     * @param status
     * @return
     */
    List<EnterprisewxJob> querystatus(String status);

    /**
     * 通过ID查询单条数据
     *
     * @param jobId 主键
     * @return 实例对象
     */
    EnterprisewxJob queryById(Integer jobId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<EnterprisewxJob> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param enterprisewxJob 实例对象
     * @return 对象列表
     */
    List<EnterprisewxJob> queryAll(EnterprisewxJob enterprisewxJob);

    /**
     * 新增数据
     *
     * @param enterprisewxJob 实例对象
     * @return 影响行数
     */
    int insert(EnterprisewxJob enterprisewxJob);

    /**
     * 修改数据
     *
     * @param enterprisewxJob 实例对象
     * @return 影响行数
     */
    int update(EnterprisewxJob enterprisewxJob);

    /**
     * 通过主键删除数据
     *
     * @param jobId 主键
     * @return 影响行数
     */
    int deleteById(Integer jobId);

    /**
     * 通过主键修改数据的状态
     * @param jobId
     * @param status
     * @return
     */
    int updatejobstatus(Integer jobId,String status);

}