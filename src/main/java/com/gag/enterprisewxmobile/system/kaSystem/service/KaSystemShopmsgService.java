package com.gag.enterprisewxmobile.system.kaSystem.service;

import com.gag.enterprisewxmobile.system.kaSystem.entity.KaSystemShopmsg;

import java.util.List;
import java.util.Map;

/**
 * ka系统门店信息(KaSystemShopmsg)表服务接口
 *
 * @author makejava
 * @since 2020-10-30 17:07:26
 */
public interface KaSystemShopmsgService {

    /**
     * 通过ID查询单条数据
     *
     * @param autoid 主键
     * @return 实例对象
     */
    KaSystemShopmsg queryById(Integer autoid);


    /**
     * 新增数据
     *
     * @param kaSystemShopmsg 实例对象
     * @return 实例对象
     */
    KaSystemShopmsg insert(KaSystemShopmsg kaSystemShopmsg);

    int insertmsg(Map map);

    int selectmsg(String shop_code);

    int updatemsg(Map map);

    /**
     * 修改数据
     *
     * @param kaSystemShopmsg 实例对象
     * @return 实例对象
     */
    KaSystemShopmsg update(KaSystemShopmsg kaSystemShopmsg);

    /**
     * 通过主键删除数据
     *
     * @param autoid 主键
     * @return 是否成功
     */
    boolean deleteById(Integer autoid);

}