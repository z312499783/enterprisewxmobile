package com.gag.enterprisewxmobile.system.ONLINEUSER.dao;

import com.gag.enterprisewxmobile.system.ONLINEUSER.entity.Timepush;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (Timepush)表数据库访问层
 *
 * @author makejava
 * @since 2020-09-25 15:18:37
 */
@Mapper
public interface TimepushDao {


    /**
     * 通过实体作为筛选条件查询
     *
     * @param timepush 实例对象
     * @return 对象列表
     */
    List<Timepush> queryAll(Timepush timepush);


    /**
     * 修改数据
     *
     * @param timepush 实例对象
     * @return 影响行数
     */
    int update(Timepush timepush);


}