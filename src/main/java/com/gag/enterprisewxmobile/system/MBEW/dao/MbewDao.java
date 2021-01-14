package com.gag.enterprisewxmobile.system.MBEW.dao;

import com.gag.enterprisewxmobile.system.MBEW.entity.Mbew;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (Mbew)表数据库访问层
 *
 * @author makejava
 * @since 2020-05-09 11:19:06
 */
@Mapper
public interface MbewDao {

    String ekpoAndekko();

    /**
     * 通过实体作为筛选条件查询数据
     * @param mbew
     * @return
     */
    List<Mbew> querymoney(Mbew mbew);


    /**
     * 通过实体作为筛选条件查询数据的数量
     * @param mbew
     * @return
     */
    int querysize(Mbew mbew);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param mbew 实例对象
     * @return 对象列表
     */
    List<Mbew> queryAll(Mbew mbew);


    /**
     * 通过ID查询单条数据
     *
     * @param autoid 主键
     * @return 实例对象
     */
    Mbew queryById(int autoid );

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Mbew> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);

    /**
     * 新增数据
     *
     * @param mbew 实例对象
     * @return 影响行数
     */
    int insert(Mbew mbew);

    /**
     * 修改数据
     *
     * @param mbew 实例对象
     * @return 影响行数
     */
    int update(Mbew mbew);

    /**
     * 通过主键删除数据
     *
     * @param autoid 主键
     * @return 影响行数
     */
    int deleteById(int autoid );

}