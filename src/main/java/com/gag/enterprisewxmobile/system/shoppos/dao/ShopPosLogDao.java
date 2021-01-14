package com.gag.enterprisewxmobile.system.shoppos.dao;

import com.gag.enterprisewxmobile.system.shoppos.entity.ShopPosLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 商品销售日志(ShopPosLog)表数据库访问层
 *
 * @author makejava
 * @since 2020-10-15 10:01:55
 */
@Mapper
public interface ShopPosLogDao {

    /**
     * 通过ID查询单条数据
     *
     * @param autoid 主键
     * @return 实例对象
     */
    ShopPosLog queryById(Integer autoid);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param shopPosLog 实例对象
     * @return 对象列表
     */
    List<ShopPosLog> queryAll(ShopPosLog shopPosLog);

    /**
     * 新增数据
     *
     * @param shopPosLog 实例对象
     * @return 影响行数
     */
    int insert(ShopPosLog shopPosLog);

    /**
     * 修改数据
     *
     * @param shopPosLog 实例对象
     * @return 影响行数
     */
    int update(ShopPosLog shopPosLog);

    /**
     * 通过主键删除数据
     *
     * @param autoid 主键
     * @return 影响行数
     */
    int deleteById(Integer autoid);

}