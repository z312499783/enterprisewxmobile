package com.gag.enterprisewxmobile.system.kaSystem.dao;

import com.gag.enterprisewxmobile.system.kaSystem.entity.KaSystemShopmsg;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

/**
 * ka系统门店信息(KaSystemShopmsg)表数据库访问层
 *
 * @author makejava
 * @since 2020-10-30 17:07:26
 */
@Mapper
public interface KaSystemShopmsgDao {

    /**
     * 通过ID查询单条数据
     *
     * @param autoid 主键
     * @return 实例对象
     */
    KaSystemShopmsg queryById(Integer autoid);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<KaSystemShopmsg> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param kaSystemShopmsg 实例对象
     * @return 对象列表
     */
    List<KaSystemShopmsg> queryAll(KaSystemShopmsg kaSystemShopmsg);

    /**
     * 新增数据
     *
     * @param kaSystemShopmsg 实例对象
     * @return 影响行数
     */
    int insert(KaSystemShopmsg kaSystemShopmsg);

    int insertmsg(Map map);

    int selectmsg(String shop_code);

    int updatemsg(Map map);

    /**
     * 修改数据
     *
     * @param kaSystemShopmsg 实例对象
     * @return 影响行数
     */
    int update(KaSystemShopmsg kaSystemShopmsg);

    /**
     * 通过主键删除数据
     *
     * @param autoid 主键
     * @return 影响行数
     */
    int deleteById(Integer autoid);

}