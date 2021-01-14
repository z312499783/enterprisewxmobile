package com.gag.enterprisewxmobile.system.ONLINEUSER.service;

import com.gag.enterprisewxmobile.system.ONLINEUSER.entity.Timepush;
import java.util.List;

/**
 * (Timepush)表服务接口
 *
 * @author makejava
 * @since 2020-09-25 15:18:37
 */
public interface TimepushService {


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
     * @return 实例对象
     */
    int update(Timepush timepush);



}