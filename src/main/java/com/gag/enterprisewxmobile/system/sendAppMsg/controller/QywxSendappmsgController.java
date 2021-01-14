package com.gag.enterprisewxmobile.system.sendAppMsg.controller;

import com.gag.enterprisewxmobile.system.sendAppMsg.entity.QywxSendappmsg;
import com.gag.enterprisewxmobile.system.sendAppMsg.service.QywxSendappmsgService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (QywxSendappmsg)表控制层
 *
 * @author makejava
 * @since 2020-06-01 10:27:28
 */
public class QywxSendappmsgController {
    /**
     * 服务对象
     */
    @Resource
    private QywxSendappmsgService qywxSendappmsgService;


}