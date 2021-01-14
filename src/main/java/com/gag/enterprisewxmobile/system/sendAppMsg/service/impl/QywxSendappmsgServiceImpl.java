package com.gag.enterprisewxmobile.system.sendAppMsg.service.impl;

import com.gag.enterprisewxmobile.system.sendAppMsg.entity.QywxSendappmsg;
import com.gag.enterprisewxmobile.system.sendAppMsg.dao.QywxSendappmsgDao;
import com.gag.enterprisewxmobile.system.sendAppMsg.service.QywxSendappmsgService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (QywxSendappmsg)表服务实现类
 *
 * @author makejava
 * @since 2020-06-01 10:27:28
 */
@Service("qywxSendappmsgService")
public class QywxSendappmsgServiceImpl implements QywxSendappmsgService {
    @Resource
    private QywxSendappmsgDao qywxSendappmsgDao;

    /**
     * 根据QywxSendappmsg的id和企业微信应用的agentiid查询需要发送消息
     * @param send_app_msg_id
     * @return
     */
    @Override
    public QywxSendappmsg queryBymsg_id(Integer send_app_msg_id){
        return this.qywxSendappmsgDao.queryBymsg_id(send_app_msg_id);
    };

}