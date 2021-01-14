package com.gag.enterprisewxmobile.system.sendAppMsg.dao;

import com.gag.enterprisewxmobile.system.sendAppMsg.entity.QywxSendappmsgLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 定时任务调度日志表(QywxSendappmsgLog)表数据库访问层
 *
 * @author makejava
 * @since 2020-06-01 10:27:57
 */
@Mapper
public interface QywxSendappmsgLogDao {

    /**
     * 通过ID查询单条数据
     *
     * @param jobLogId 主键
     * @return 实例对象
     */
    QywxSendappmsgLog queryById(Integer jobLogId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<QywxSendappmsgLog> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param qywxSendappmsgLog 实例对象
     * @return 对象列表
     */
    List<QywxSendappmsgLog> queryAll(QywxSendappmsgLog qywxSendappmsgLog);

    /**
     * 新增数据
     *
     * @param qywxSendappmsgLog 实例对象
     * @return 影响行数
     */
    int insert(QywxSendappmsgLog qywxSendappmsgLog);

    /**
     * 修改数据
     *
     * @param qywxSendappmsgLog 实例对象
     * @return 影响行数
     */
    int update(QywxSendappmsgLog qywxSendappmsgLog);

    /**
     * 通过主键删除数据
     *
     * @param jobLogId 主键
     * @return 影响行数
     */
    int deleteById(Integer jobLogId);

}