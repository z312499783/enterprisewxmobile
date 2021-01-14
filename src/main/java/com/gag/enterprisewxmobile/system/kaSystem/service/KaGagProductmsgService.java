package com.gag.enterprisewxmobile.system.kaSystem.service;

import com.gag.enterprisewxmobile.system.kaSystem.entity.KaGagProductmsg;
import java.util.List;
import java.util.Map;

/**
 * ka系统盛宝产品信息(KaGagProductmsg)表服务接口
 *
 * @author makejava
 * @since 2020-11-02 13:36:19
 */
public interface KaGagProductmsgService {

    /**
     * 通过ID查询单条数据
     *
     * @param autoid 主键
     * @return 实例对象
     */
    KaGagProductmsg queryById(Integer autoid);

    int selectmsg(String article_number);

    int updatemsg(Map map);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<KaGagProductmsg> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param kaGagProductmsg 实例对象
     * @return 实例对象
     */
    KaGagProductmsg insert(KaGagProductmsg kaGagProductmsg);

    int insertmsg(Map map);

    /**
     * 修改数据
     *
     * @param kaGagProductmsg 实例对象
     * @return 实例对象
     */
    KaGagProductmsg update(KaGagProductmsg kaGagProductmsg);


}