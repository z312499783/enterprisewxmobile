package com.gag.enterprisewxmobile.system.sendAppMsg.service;

import com.gag.enterprisewxmobile.system.sendAppMsg.entity.QywxSendappmsg;
import com.gag.enterprisewxmobile.system.sendAppMsg.entity.QywxSendappmsgLog;
import java.util.List;

/**
 * 定时任务调度日志表(QywxSendappmsgLog)表服务接口
 *
 * @author makejava
 * @since 2020-06-01 10:27:57
 */
public interface QywxSendappmsgLogService {


    /**
     * 通过ID查询单条数据
     *
     * @param jobLogId 主键
     * @return 实例对象
     */
    QywxSendappmsgLog queryById(Integer jobLogId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<QywxSendappmsgLog> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param qywxSendappmsgLog 实例对象
     * @return 实例对象
     */
    QywxSendappmsgLog insert(QywxSendappmsgLog qywxSendappmsgLog);

    /**
     * 修改数据
     *
     * @param qywxSendappmsgLog 实例对象
     * @return 实例对象
     */
    QywxSendappmsgLog update(QywxSendappmsgLog qywxSendappmsgLog);

    /**
     * 通过主键删除数据
     *
     * @param jobLogId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer jobLogId);

}