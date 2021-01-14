package com.gag.enterprisewxmobile.system.MBEWS.dao;

import com.gag.enterprisewxmobile.system.MBEWS.entity.Mbews;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (Mbews)表数据库访问层
 *
 * @author makejava
 * @since 2020-05-14 10:31:56
 */
@Mapper
public interface MbewsDao {

    /**
     * 通过实体作为筛选条件查询MBEWS
     * @param mbews
     * @return
     */
    List<Mbews> queryMbews(Mbews mbews);

    /**
     * 通过实体作为筛选条件查询数据数量
     * @param mbews
     * @return
     */
    int querysize(Mbews mbews);

    /**
     * 通过ID查询单条数据
     *
     * @param autoid 主键
     * @return 实例对象
     */
    Mbews queryById(Integer autoid );

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Mbews> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param mbews 实例对象
     * @return 对象列表
     */
    List<Mbews> queryAll(Mbews mbews);

    /**
     * 新增数据
     *
     * @param mbews 实例对象
     * @return 影响行数
     */
    int insert(Mbews mbews);

    /**
     * 修改数据
     *
     * @param mbews 实例对象
     * @return 影响行数
     */
    int update(Mbews mbews);

    /**
     * 通过主键删除数据
     *
     * @param autoid 主键
     * @return 影响行数
     */
    int deleteById(Integer autoid );

}