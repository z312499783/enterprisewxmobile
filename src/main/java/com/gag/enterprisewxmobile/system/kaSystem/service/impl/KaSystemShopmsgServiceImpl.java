package com.gag.enterprisewxmobile.system.kaSystem.service.impl;

import com.gag.enterprisewxmobile.system.kaSystem.dao.KaSystemShopmsgDao;
import com.gag.enterprisewxmobile.system.kaSystem.entity.KaSystemShopmsg;
import com.gag.enterprisewxmobile.system.kaSystem.service.KaSystemShopmsgService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * ka系统门店信息(KaSystemShopmsg)表服务实现类
 *
 * @author makejava
 * @since 2020-10-30 17:07:26
 */
@Service("kaSystemShopmsgService")
public class KaSystemShopmsgServiceImpl implements KaSystemShopmsgService {
    @Resource
    private KaSystemShopmsgDao kaSystemShopmsgDao;

    /**
     * 通过ID查询单条数据
     *
     * @param autoid 主键
     * @return 实例对象
     */
    @Override
    public KaSystemShopmsg queryById(Integer autoid) {
        return this.kaSystemShopmsgDao.queryById(autoid);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<KaSystemShopmsg> queryAllByLimit(int offset, int limit) {
        return this.kaSystemShopmsgDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param kaSystemShopmsg 实例对象
     * @return 实例对象
     */
    @Override
    public KaSystemShopmsg insert(KaSystemShopmsg kaSystemShopmsg) {
        this.kaSystemShopmsgDao.insert(kaSystemShopmsg);
        return kaSystemShopmsg;
    }

    @Override
    public int insertmsg(Map map){
        return this.kaSystemShopmsgDao.insertmsg(map);
    };

    @Override
    public int selectmsg(String shop_code){
        return this.kaSystemShopmsgDao.selectmsg(shop_code);
    };

    @Override
    public int updatemsg(Map map){
        return this.kaSystemShopmsgDao.updatemsg(map);
    };

    /**
     * 修改数据
     *
     * @param kaSystemShopmsg 实例对象
     * @return 实例对象
     */
    @Override
    public KaSystemShopmsg update(KaSystemShopmsg kaSystemShopmsg) {
        this.kaSystemShopmsgDao.update(kaSystemShopmsg);
        return this.queryById(kaSystemShopmsg.getAutoid());
    }

    /**
     * 通过主键删除数据
     *
     * @param autoid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer autoid) {
        return this.kaSystemShopmsgDao.deleteById(autoid) > 0;
    }
}