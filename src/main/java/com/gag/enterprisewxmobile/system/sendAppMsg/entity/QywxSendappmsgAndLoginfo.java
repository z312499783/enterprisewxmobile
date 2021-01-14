package com.gag.enterprisewxmobile.system.sendAppMsg.entity;

import java.io.Serializable;

/**
 * (QywxSendappmsgAndLoginfo)实体类
 *
 * @author makejava
 * @since 2020-06-08 17:13:00
 */
public class QywxSendappmsgAndLoginfo implements Serializable {
    private static final long serialVersionUID = -22867901320290825L;
    /**
    * 企业微信推送消息和日志id
    */
    private Integer msgAndLoginfoId;
    /**
    * SendAppMsg表的id，将消息推送给谁
    */
    private Integer sendappmsgId;
    /**
    * SendAppMsg_log_info表的id，推送消息后添加的日志信息
    */
    private Integer loginfoId;


    public Integer getMsgAndLoginfoId() {
        return msgAndLoginfoId;
    }

    public void setMsgAndLoginfoId(Integer msgAndLoginfoId) {
        this.msgAndLoginfoId = msgAndLoginfoId;
    }

    public Integer getSendappmsgId() {
        return sendappmsgId;
    }

    public void setSendappmsgId(Integer sendappmsgId) {
        this.sendappmsgId = sendappmsgId;
    }

    public Integer getLoginfoId() {
        return loginfoId;
    }

    public void setLoginfoId(Integer loginfoId) {
        this.loginfoId = loginfoId;
    }

}