package com.gag.enterprisewxmobile.system.kaSystem.dao;

import com.gag.enterprisewxmobile.system.kaSystem.entity.KaSystemContractmsg;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

/**
 * ka系统合同信息(KaSystemContractmsg)表数据库访问层
 *
 * @author makejava
 * @since 2020-10-30 15:07:35
 */
@Mapper
public interface KaSystemContractmsgDao {

    /**
     * 通过ID查询单条数据
     *
     * @param autoid 主键
     * @return 实例对象
     */
    KaSystemContractmsg queryById(Integer autoid);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<KaSystemContractmsg> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param kaSystemContractmsg 实例对象
     * @return 对象列表
     */
    List<KaSystemContractmsg> queryAll(KaSystemContractmsg kaSystemContractmsg);

    /**
     * 新增数据
     *
     * @param kaSystemContractmsg 实例对象
     * @return 影响行数
     */
    int insert(KaSystemContractmsg kaSystemContractmsg);

    int insertmsg(Map map);

    int selectmsg(String contract_code);

    int updatemsg(Map map);

    /**
     * 修改数据
     *
     * @param kaSystemContractmsg 实例对象
     * @return 影响行数
     */
    int update(KaSystemContractmsg kaSystemContractmsg);

    /**
     * 通过主键删除数据
     *
     * @param autoid 主键
     * @return 影响行数
     */
    int deleteById(Integer autoid);

}