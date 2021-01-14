package com.gag.enterprisewxmobile.system.party.dao;

import com.gag.enterprisewxmobile.system.party.entity.QywxSysParty;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 企业微信部门信息表(QywxSysParty)表数据库访问层
 *
 * @author makejava
 * @since 2020-12-28 13:40:33
 */
@Mapper
public interface QywxSysPartyDao {

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
     * @return 影响行数
     */
    int insert(QywxSysParty qywxSysParty);

    int deleteAll();


}