package com.gag.enterprisewxmobile.system.kaSystem.service;

import com.gag.enterprisewxmobile.system.kaSystem.entity.KaSystemCodeshopmsg;
import java.util.List;
import java.util.Map;

/**
 * ka系统条码商品号信息(KaSystemCodeshopmsg)表服务接口
 *
 * @author makejava
 * @since 2020-10-30 15:07:11
 */
public interface KaSystemCodeshopmsgService {

    /**
     * 通过ID查询单条数据
     *
     * @param autoid 主键
     * @return 实例对象
     */
    KaSystemCodeshopmsg queryById(Integer autoid);

    /**
     * 新增数据
     *
     * @param kaSystemCodeshopmsg 实例对象
     * @return 实例对象
     */
    KaSystemCodeshopmsg insert(KaSystemCodeshopmsg kaSystemCodeshopmsg);

    int insertmsg(Map map);

    int selectmsg(String article_code);

    int updatemsg(Map map);


    /**
     * 修改数据
     *
     * @param kaSystemCodeshopmsg 实例对象
     * @return 实例对象
     */
    KaSystemCodeshopmsg update(KaSystemCodeshopmsg kaSystemCodeshopmsg);

    /**
     * 通过主键删除数据
     *
     * @param autoid 主键
     * @return 是否成功
     */
    boolean deleteById(Integer autoid);

}