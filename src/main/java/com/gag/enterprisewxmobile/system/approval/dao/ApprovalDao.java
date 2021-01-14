package com.gag.enterprisewxmobile.system.approval.dao;

import com.gag.enterprisewxmobile.system.approval.entity.Approval;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 企业微信相关数据(Approval)表数据库访问层
 *
 * @author makejava
 * @since 2020-04-20 17:20:11
 */
@Mapper
public interface ApprovalDao {

    /**
     * 通过agentid查询accesstoken
     * @param agentid
     * @return
     */
    String queryAccessToken(@Param("agentid") String agentid);

    /**
     * agentid根据查询数据
     * @param agentid
     * @return
     */
    Approval queryAgentid(@Param("agentid") String agentid);


    /**
     * 通过ID查询单条数据
     *
     * @param approvalId 主键
     * @return 实例对象
     */
    Approval queryById(Integer approvalId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Approval> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param approval 实例对象
     * @return 对象列表
     */
    List<Approval> queryAll(Approval approval);

    /**
     * 新增数据
     *
     * @param approval 实例对象
     * @return 影响行数
     */
    int insert(Approval approval);

    /**
     * 修改数据
     *
     * @param approval 实例对象
     * @return 影响行数
     */
    int update(Approval approval);

    /**
     * 通过主键删除数据
     *
     * @param approvalId 主键
     * @return 影响行数
     */
    int deleteById(Integer approvalId);

}