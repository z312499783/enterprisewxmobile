package com.gag.enterprisewxmobile.system.shoppos.service;

import com.gag.enterprisewxmobile.system.shoppos.entity.ShopPosLog;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 商品销售日志(ShopPosLog)表服务接口
 *
 * @author makejava
 * @since 2020-10-15 10:01:55
 */
public interface ShopPosLogService {

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
     * @return 实例对象
     */
    ShopPosLog insert(ShopPosLog shopPosLog);

    /**
     * 修改数据
     *
     * @param shopPosLog 实例对象
     * @return 实例对象
     */
    ShopPosLog update(ShopPosLog shopPosLog);

    /**
     * 通过主键删除数据
     *
     * @param autoid 主键
     * @return 是否成功
     */
    boolean deleteById(Integer autoid);

}