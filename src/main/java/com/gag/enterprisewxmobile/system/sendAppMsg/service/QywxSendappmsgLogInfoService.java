package com.gag.enterprisewxmobile.system.sendAppMsg.service;

import com.gag.enterprisewxmobile.system.sendAppMsg.entity.QywxSendappmsgLogInfo;
import java.util.List;

/**
 * (QywxSendappmsgLogInfo)表服务接口
 *
 * @author makejava
 * @since 2020-06-08 11:14:55
 */
public interface QywxSendappmsgLogInfoService {

    /**
     * 通过ID查询单条数据
     *
     * @param jobLogInfoId 主键
     * @return 实例对象
     */
    QywxSendappmsgLogInfo queryById(Integer jobLogInfoId);

    /**
     * 新增数据
     *
     * @param qywxSendappmsgLogInfo 实例对象
     * @return 实例对象
     */
    QywxSendappmsgLogInfo insert(QywxSendappmsgLogInfo qywxSendappmsgLogInfo);

    /**
     * 修改数据
     *
     * @param qywxSendappmsgLogInfo 实例对象
     * @return 实例对象
     */
    QywxSendappmsgLogInfo update(QywxSendappmsgLogInfo qywxSendappmsgLogInfo);

    /**
     * 通过主键删除数据
     *
     * @param jobLogInfoId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer jobLogInfoId);

}