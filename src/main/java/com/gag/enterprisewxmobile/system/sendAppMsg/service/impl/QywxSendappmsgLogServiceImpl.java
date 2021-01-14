package com.gag.enterprisewxmobile.system.sendAppMsg.service.impl;

import com.gag.enterprisewxmobile.system.sendAppMsg.entity.QywxSendappmsgLog;
import com.gag.enterprisewxmobile.system.sendAppMsg.dao.QywxSendappmsgLogDao;
import com.gag.enterprisewxmobile.system.sendAppMsg.service.QywxSendappmsgLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 定时任务调度日志表(QywxSendappmsgLog)表服务实现类
 *
 * @author makejava
 * @since 2020-06-01 10:27:57
 */
@Service("qywxSendappmsgLogService")
public class QywxSendappmsgLogServiceImpl implements QywxSendappmsgLogService {
    @Resource
    private QywxSendappmsgLogDao qywxSendappmsgLogDao;

    /**
     * 通过ID查询单条数据
     *
     * @param jobLogId 主键
     * @return 实例对象
     */
    @Override
    public QywxSendappmsgLog queryById(Integer jobLogId) {
        return this.qywxSendappmsgLogDao.queryById(jobLogId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<QywxSendappmsgLog> queryAllByLimit(int offset, int limit) {
        return this.qywxSendappmsgLogDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param qywxSendappmsgLog 实例对象
     * @return 实例对象
     */
    @Override
    public QywxSendappmsgLog insert(QywxSendappmsgLog qywxSendappmsgLog) {
        this.qywxSendappmsgLogDao.insert(qywxSendappmsgLog);
        return qywxSendappmsgLog;
    }

    /**
     * 修改数据
     *
     * @param qywxSendappmsgLog 实例对象
     * @return 实例对象
     */
    @Override
    public QywxSendappmsgLog update(QywxSendappmsgLog qywxSendappmsgLog) {
        this.qywxSendappmsgLogDao.update(qywxSendappmsgLog);
        return this.queryById(qywxSendappmsgLog.getJobLogId());
    }

    /**
     * 通过主键删除数据
     *
     * @param jobLogId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer jobLogId) {
        return this.qywxSendappmsgLogDao.deleteById(jobLogId) > 0;
    }
}