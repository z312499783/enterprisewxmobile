package com.gag.enterprisewxmobile.system.approval.service.impl;

import com.gag.enterprisewxmobile.system.approval.entity.Approval;
import com.gag.enterprisewxmobile.system.approval.dao.ApprovalDao;
import com.gag.enterprisewxmobile.system.approval.service.ApprovalService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 企业微信相关数据(Approval)表服务实现类
 *
 * @author makejava
 * @since 2020-04-20 17:20:11
 */
@CacheConfig(cacheNames = "approvalService")
@Service("approvalService")
public class ApprovalServiceImpl implements ApprovalService {
    @Resource
    private ApprovalDao approvalDao;


    /**
     * 通过agentid查询accesstoken
     * @param agentid
     * @return
     */
    @Override
    public String queryAccessToken(String agentid){
        return this.approvalDao.queryAccessToken(agentid);
    };

    /**
     * agentid根据查询数据
     * @param agentid
     * @return
     */
    @Override
    public Approval queryAgentid(String agentid){
        return this.approvalDao.queryAgentid(agentid);
    };


    /**
     * 通过实体作为筛选条件查询
     *
     * @param approval 实例对象
     * @return 对象列表
     */
    @Cacheable(key = "#root.targetClass.typeName")
    @Override
    public List<Approval> queryAll(Approval approval){
        return this.approvalDao.queryAll(approval);
    };

    /**
     * 通过ID查询单条数据
     *
     * @param approvalId 主键
     * @return 实例对象
     */
    @Override
    public Approval queryById(Integer approvalId) {
        return this.approvalDao.queryById(approvalId);
    }


    /**
     * 新增数据
     *
     * @param approval 实例对象
     * @return 实例对象
     */
    @CachePut(key = "#root.targetClass.typeName")
    @Override
    public Approval insert(Approval approval) {
        this.approvalDao.insert(approval);
        return approval;
    }

    /**
     * 修改数据
     *
     * @param approval 实例对象
     * @return 实例对象
     */
    @CachePut(key = "#root.targetClass.typeName")
    @Override
    public Approval update(Approval approval) {
        this.approvalDao.update(approval);
        return this.queryById(approval.getApprovalId());
    }

    /**
     * 通过主键删除数据
     *
     * @param approvalId 主键
     * @return 是否成功
     */
    @CacheEvict(key = "#root.targetClass.typeName")
    @Override
    public boolean deleteById(Integer approvalId) {
        return this.approvalDao.deleteById(approvalId) > 0;
    }
}