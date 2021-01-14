package com.gag.enterprisewxmobile.system.sendAppMsg.service.impl;

import com.gag.enterprisewxmobile.system.sendAppMsg.entity.QywxSendappmsgLogInfo;
import com.gag.enterprisewxmobile.system.sendAppMsg.dao.QywxSendappmsgLogInfoDao;
import com.gag.enterprisewxmobile.system.sendAppMsg.service.QywxSendappmsgLogInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (QywxSendappmsgLogInfo)表服务实现类
 *
 * @author makejava
 * @since 2020-06-08 11:14:55
 */
@Service("qywxSendappmsgLogInfoService")
public class QywxSendappmsgLogInfoServiceImpl implements QywxSendappmsgLogInfoService {
    @Resource
    private QywxSendappmsgLogInfoDao qywxSendappmsgLogInfoDao;

    /**
     * 通过ID查询单条数据
     *
     * @param jobLogInfoId 主键
     * @return 实例对象
     */
    @Override
    public QywxSendappmsgLogInfo queryById(Integer jobLogInfoId) {
        return this.qywxSendappmsgLogInfoDao.queryById(jobLogInfoId);
    }


    /**
     * 新增数据
     *
     * @param qywxSendappmsgLogInfo 实例对象
     * @return 实例对象
     */
    @Override
    public QywxSendappmsgLogInfo insert(QywxSendappmsgLogInfo qywxSendappmsgLogInfo) {
        this.qywxSendappmsgLogInfoDao.insert(qywxSendappmsgLogInfo);
        return qywxSendappmsgLogInfo;
    }

    /**
     * 修改数据
     *
     * @param qywxSendappmsgLogInfo 实例对象
     * @return 实例对象
     */
    @Override
    public QywxSendappmsgLogInfo update(QywxSendappmsgLogInfo qywxSendappmsgLogInfo) {
        this.qywxSendappmsgLogInfoDao.update(qywxSendappmsgLogInfo);
        return this.queryById(qywxSendappmsgLogInfo.getJobLogInfoId());
    }

    /**
     * 通过主键删除数据
     *
     * @param jobLogInfoId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer jobLogInfoId) {
        return this.qywxSendappmsgLogInfoDao.deleteById(jobLogInfoId) > 0;
    }
}