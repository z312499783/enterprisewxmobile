package com.gag.enterprisewxmobile.system.MBEWS.service;

import com.gag.enterprisewxmobile.system.MBEWS.entity.Mbews;
import java.util.List;

/**
 * (Mbews)表服务接口
 *
 * @author makejava
 * @since 2020-05-14 10:31:56
 */
public interface MbewsService {

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
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Mbews> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param mbews 实例对象
     * @return 实例对象
     */
    Mbews insert(Mbews mbews);

    /**
     * 修改数据
     *
     * @param mbews 实例对象
     * @return 实例对象
     */
    Mbews update(Mbews mbews);

    /**
     * 通过主键删除数据
     *
     * @param autoid 主键
     * @return 是否成功
     */
    boolean deleteById(Integer autoid );

}