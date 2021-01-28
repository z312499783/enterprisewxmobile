package com.gag.enterprisewxmobile.system.kaSystem.service.impl;

import com.gag.enterprisewxmobile.system.kaSystem.entity.KaSystemContractmsg;
import com.gag.enterprisewxmobile.system.kaSystem.dao.KaSystemContractmsgDao;
import com.gag.enterprisewxmobile.system.kaSystem.service.KaSystemContractmsgService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * ka系统合同信息(KaSystemContractmsg)表服务实现类
 *
 * @author makejava
 * @since 2020-10-30 15:07:35
 */
//@CacheConfig(cacheNames = "kaSystemContractmsgService")
@Service("kaSystemContractmsgService")
public class KaSystemContractmsgServiceImpl implements KaSystemContractmsgService {
    @Resource
    private KaSystemContractmsgDao kaSystemContractmsgDao;

    /**
     * 通过ID查询单条数据
     *
     * @param autoid 主键
     * @return 实例对象
     */
    @Override
    public KaSystemContractmsg queryById(Integer autoid) {
        return this.kaSystemContractmsgDao.queryById(autoid);
    }


    /**
     * 新增数据
     *
     * @param kaSystemContractmsg 实例对象
     * @return 实例对象
     */
    @Override
    public KaSystemContractmsg insert(KaSystemContractmsg kaSystemContractmsg) {
        this.kaSystemContractmsgDao.insert(kaSystemContractmsg);
        return kaSystemContractmsg;
    }

    @Override
    public int insertmsg(Map map){
        return this.kaSystemContractmsgDao.insertmsg(map);
    };

    @Override
    public int selectmsg(String contract_code){
        return this.kaSystemContractmsgDao.selectmsg(contract_code);
    };

    @Override
    public int updatemsg(Map map){
        return this.kaSystemContractmsgDao.updatemsg(map);
    };

    /**
     * 修改数据
     *
     * @param kaSystemContractmsg 实例对象
     * @return 实例对象
     */
    @Override
    public KaSystemContractmsg update(KaSystemContractmsg kaSystemContractmsg) {
        this.kaSystemContractmsgDao.update(kaSystemContractmsg);
        return this.queryById(kaSystemContractmsg.getAutoid());
    }

    /**
     * 通过主键删除数据
     *
     * @param autoid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer autoid) {
        return this.kaSystemContractmsgDao.deleteById(autoid) > 0;
    }
}