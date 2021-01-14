package com.gag.enterprisewxmobile.system.sendAppMsg.dao;

import com.gag.enterprisewxmobile.system.sendAppMsg.entity.QywxSendappmsgLogInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (QywxSendappmsgLogInfo)表数据库访问层
 *
 * @author makejava
 * @since 2020-06-08 11:14:55
 */
@Mapper
public interface QywxSendappmsgLogInfoDao {

    /**
     * 通过ID查询单条数据
     *
     * @param jobLogInfoId 主键
     * @return 实例对象
     */
    QywxSendappmsgLogInfo queryById(Integer jobLogInfoId);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param qywxSendappmsgLogInfo 实例对象
     * @return 对象列表
     */
    List<QywxSendappmsgLogInfo> queryAll(QywxSendappmsgLogInfo qywxSendappmsgLogInfo);

    /**
     * 新增数据
     *
     * @param qywxSendappmsgLogInfo 实例对象
     * @return 影响行数
     */
    int insert(QywxSendappmsgLogInfo qywxSendappmsgLogInfo);

    /**
     * 修改数据
     *
     * @param qywxSendappmsgLogInfo 实例对象
     * @return 影响行数
     */
    int update(QywxSendappmsgLogInfo qywxSendappmsgLogInfo);

    /**
     * 通过主键删除数据
     *
     * @param jobLogInfoId 主键
     * @return 影响行数
     */
    int deleteById(Integer jobLogInfoId);

}