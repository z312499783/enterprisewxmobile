package com.gag.enterprisewxmobile.system.approval.service;

import com.gag.enterprisewxmobile.system.approval.entity.Approval;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 企业微信相关数据(Approval)表服务接口
 *
 * @author makejava
 * @since 2020-04-20 17:20:11
 */
public interface ApprovalService {

    /**
     * 通过agentid查询accesstoken
     * @param agentid
     * @return
     */
    String queryAccessToken(String agentid);

    /**
     * agentid根据查询数据
     * @param agentid
     * @return
     */
    Approval queryAgentid(String agentid);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param approval 实例对象
     * @return 对象列表
     */
    List<Approval> queryAll(Approval approval);

    /**
     * 通过ID查询单条数据
     *
     * @param approvalId 主键
     * @return 实例对象
     */
    Approval queryById(Integer approvalId);

    /**
     * 新增数据
     *
     * @param approval 实例对象
     * @return 实例对象
     */
    Approval insert(Approval approval);

    /**
     * 修改数据
     *
     * @param approval 实例对象
     * @return 实例对象
     */
    Approval update(Approval approval);

    /**
     * 通过主键删除数据
     *
     * @param approvalId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer approvalId);

}