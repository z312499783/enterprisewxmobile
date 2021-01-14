package com.gag.enterprisewxmobile.system.party.service;

import com.gag.enterprisewxmobile.system.party.entity.QywxSysParty;
import java.util.List;

/**
 * 企业微信部门信息表(QywxSysParty)表服务接口
 *
 * @author makejava
 * @since 2020-12-28 13:40:33
 */
public interface QywxSysPartyService {

    /**
     * 通过ID查询单条数据
     *
     * @param partyId 主键
     * @return 实例对象
     */
    QywxSysParty queryById(Integer partyId);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param qywxSysParty 实例对象
     * @return 对象列表
     */
    List<QywxSysParty> queryAll(QywxSysParty qywxSysParty);

    /**
     * 新增数据
     *
     * @param qywxSysParty 实例对象
     * @return 实例对象
     */
    QywxSysParty insert(QywxSysParty qywxSysParty);

    int deleteAll();



}