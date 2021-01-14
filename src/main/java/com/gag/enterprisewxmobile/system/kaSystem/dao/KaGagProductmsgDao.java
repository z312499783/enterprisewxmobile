package com.gag.enterprisewxmobile.system.kaSystem.dao;

import com.gag.enterprisewxmobile.system.kaSystem.entity.KaGagProductmsg;
import com.gag.enterprisewxmobile.system.kaSystem.entity.ShopPos;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * ka系统盛宝产品信息(KaGagProductmsg)表数据库访问层
 *
 * @author makejava
 * @since 2020-11-02 13:36:19
 */
@Mapper
public interface KaGagProductmsgDao {

    /**
     * 通过ID查询单条数据
     *
     * @param autoid 主键
     * @return 实例对象
     */
    KaGagProductmsg queryById(Integer autoid);

    int selectmsg(String article_number);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<KaGagProductmsg> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param kaGagProductmsg 实例对象
     * @return 对象列表
     */
    List<KaGagProductmsg> queryAll(KaGagProductmsg kaGagProductmsg);

    /**
     * 新增数据
     *
     * @param kaGagProductmsg 实例对象
     * @return 影响行数
     */
    int insert(KaGagProductmsg kaGagProductmsg);

    int insertmsg(Map map);

    /**
     * 修改数据
     *
     * @param kaGagProductmsg 实例对象
     * @return 影响行数
     */
    int update(KaGagProductmsg kaGagProductmsg);

    int updatemsg(Map map);

}