package com.gag.enterprisewxmobile.system.sendAppMsg.service;

import com.gag.enterprisewxmobile.system.sendAppMsg.entity.QywxSendappmsg;
import java.util.List;

/**
 * (QywxSendappmsg)表服务接口
 *
 * @author makejava
 * @since 2020-06-01 10:27:28
 */
public interface QywxSendappmsgService {

    /**
     * 根据QywxSendappmsg的id和企业微信应用的agentiid查询需要发送消息
     * @param send_app_msg_id
     * @return
     */
    QywxSendappmsg queryBymsg_id(Integer send_app_msg_id);

}