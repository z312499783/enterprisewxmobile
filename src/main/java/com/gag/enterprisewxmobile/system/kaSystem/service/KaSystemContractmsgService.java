package com.gag.enterprisewxmobile.system.kaSystem.service;

import com.gag.enterprisewxmobile.system.kaSystem.entity.KaSystemContractmsg;
import java.util.List;
import java.util.Map;

/**
 * ka系统合同信息(KaSystemContractmsg)表服务接口
 *
 * @author makejava
 * @since 2020-10-30 15:07:35
 */
public interface KaSystemContractmsgService {

    /**
     * 通过ID查询单条数据
     *
     * @param autoid 主键
     * @return 实例对象
     */
    KaSystemContractmsg queryById(Integer autoid);

    /**
     * 新增数据
     *
     * @param kaSystemContractmsg 实例对象
     * @return 实例对象
     */
    KaSystemContractmsg insert(KaSystemContractmsg kaSystemContractmsg);

    int insertmsg(Map map);

    int selectmsg(String contract_code);

    int updatemsg(Map map);

    /**
     * 修改数据
     *
     * @param kaSystemContractmsg 实例对象
     * @return 实例对象
     */
    KaSystemContractmsg update(KaSystemContractmsg kaSystemContractmsg);

    /**
     * 通过主键删除数据
     *
     * @param autoid 主键
     * @return 是否成功
     */
    boolean deleteById(Integer autoid);

}