package com.gag.enterprisewxmobile.system.party.controller;

import com.gag.enterprisewxmobile.system.party.entity.QywxSysParty;
import com.gag.enterprisewxmobile.system.party.service.QywxSysPartyService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 企业微信部门信息表(QywxSysParty)表控制层
 *
 * @author makejava
 * @since 2020-12-28 13:40:34
 */
@RestController
@RequestMapping("qywxSysParty")
public class QywxSysPartyController {
    /**
     * 服务对象
     */
    @Resource
    private QywxSysPartyService qywxSysPartyService;


}