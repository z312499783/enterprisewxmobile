package com.gag.enterprisewxmobile.system.sendAppMsg.service.impl;

import com.gag.enterprisewxmobile.system.sendAppMsg.entity.QywxSendappmsgAndLoginfo;
import com.gag.enterprisewxmobile.system.sendAppMsg.dao.QywxSendappmsgAndLoginfoDao;
import com.gag.enterprisewxmobile.system.sendAppMsg.service.QywxSendappmsgAndLoginfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (QywxSendappmsgAndLoginfo)表服务实现类
 *
 * @author makejava
 * @since 2020-06-08 17:13:00
 */
@Service("qywxSendappmsgAndLoginfoService")
public class QywxSendappmsgAndLoginfoServiceImpl implements QywxSendappmsgAndLoginfoService {
    @Resource
    private QywxSendappmsgAndLoginfoDao qywxSendappmsgAndLoginfoDao;

    /**
     * 通过ID查询单条数据
     *
     * @param msgAndLoginfoId 主键
     * @return 实例对象
     */
    @Override
    public QywxSendappmsgAndLoginfo queryById(Integer msgAndLoginfoId) {
        return this.qywxSendappmsgAndLoginfoDao.queryById(msgAndLoginfoId);
    }

    /**
     * 新增数据
     *
     * @param qywxSendappmsgAndLoginfo 实例对象
     * @return 实例对象
     */
    @Override
    public QywxSendappmsgAndLoginfo insert(QywxSendappmsgAndLoginfo qywxSendappmsgAndLoginfo) {
        this.qywxSendappmsgAndLoginfoDao.insert(qywxSendappmsgAndLoginfo);
        return qywxSendappmsgAndLoginfo;
    }

    /**
     * 修改数据
     *
     * @param qywxSendappmsgAndLoginfo 实例对象
     * @return 实例对象
     */
    @Override
    public QywxSendappmsgAndLoginfo update(QywxSendappmsgAndLoginfo qywxSendappmsgAndLoginfo) {
        this.qywxSendappmsgAndLoginfoDao.update(qywxSendappmsgAndLoginfo);
        return this.queryById(qywxSendappmsgAndLoginfo.getMsgAndLoginfoId());
    }

    /**
     * 通过主键删除数据
     *
     * @param msgAndLoginfoId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer msgAndLoginfoId) {
        return this.qywxSendappmsgAndLoginfoDao.deleteById(msgAndLoginfoId) > 0;
    }
}