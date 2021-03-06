package com.gag.enterprisewxmobile.system.party.service.impl;

import com.gag.enterprisewxmobile.system.party.entity.QywxSysParty;
import com.gag.enterprisewxmobile.system.party.dao.QywxSysPartyDao;
import com.gag.enterprisewxmobile.system.party.service.QywxSysPartyService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.security.Key;
import java.util.List;

/**
 * 企业微信部门信息表(QywxSysParty)表服务实现类
 *
 * @author makejava
 * @since 2020-12-28 13:40:34
 */
//@CacheConfig(cacheNames = "qywxSysPartyService")
@Service("qywxSysPartyService")
public class QywxSysPartyServiceImpl implements QywxSysPartyService {

    @Resource
    private QywxSysPartyDao qywxSysPartyDao;

    /**
     * 通过ID查询单条数据
     *
     * @param partyId 主键
     * @return 实例对象
     */
    @Override
    public QywxSysParty queryById(Integer partyId) {
        return this.qywxSysPartyDao.queryById(partyId);
    }



    /**
     * 通过实体作为筛选条件查询
     *
     * @param qywxSysParty 实例对象
     * @return 对象列表
     */
    //@Cacheable(key = "#root.targetClass.typeName")
    @Override
    public List<QywxSysParty> queryAll(QywxSysParty qywxSysParty){
        return this.qywxSysPartyDao.queryAll(qywxSysParty);
    };

    /**
     * 新增数据
     *
     * @param qywxSysParty 实例对象
     * @return 实例对象
     */
    //@CachePut(key = "root.targetClass.typeName")
    @Override
    public QywxSysParty insert(QywxSysParty qywxSysParty) {
        this.qywxSysPartyDao.insert(qywxSysParty);
        return qywxSysParty;
    }

    //@CacheEvict(key = "#root.targetClass.typeName")
    @Override
    public int deleteAll(){
        return this.deleteAll();
    };
}