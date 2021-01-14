package com.gag.enterprisewxmobile.system.kaSystem.service.impl;

import com.gag.enterprisewxmobile.system.kaSystem.entity.KaGagProductmsg;
import com.gag.enterprisewxmobile.system.kaSystem.dao.KaGagProductmsgDao;
import com.gag.enterprisewxmobile.system.kaSystem.service.KaGagProductmsgService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * ka系统盛宝产品信息(KaGagProductmsg)表服务实现类
 *
 * @author makejava
 * @since 2020-11-02 13:36:19
 */
@Service("kaGagProductmsgService")
public class KaGagProductmsgServiceImpl implements KaGagProductmsgService {
    @Resource
    private KaGagProductmsgDao kaGagProductmsgDao;


    @Override
    public int selectmsg(String article_number){
        return this.kaGagProductmsgDao.selectmsg(article_number);
    };

    @Override
    public int updatemsg(Map map){
        return this.kaGagProductmsgDao.updatemsg(map);
    };

    /**
     * 通过ID查询单条数据
     *
     * @param autoid 主键
     * @return 实例对象
     */
    @Override
    public KaGagProductmsg queryById(Integer autoid) {
        return this.kaGagProductmsgDao.queryById(autoid);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<KaGagProductmsg> queryAllByLimit(int offset, int limit) {
        return this.kaGagProductmsgDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param kaGagProductmsg 实例对象
     * @return 实例对象
     */
    @Override
    public KaGagProductmsg insert(KaGagProductmsg kaGagProductmsg) {
        this.kaGagProductmsgDao.insert(kaGagProductmsg);
        return kaGagProductmsg;
    }

    @Override
    public int insertmsg(Map map){
        return this.kaGagProductmsgDao.insertmsg(map);
    };

    /**
     * 修改数据
     *
     * @param kaGagProductmsg 实例对象
     * @return 实例对象
     */
    @Override
    public KaGagProductmsg update(KaGagProductmsg kaGagProductmsg) {
        this.kaGagProductmsgDao.update(kaGagProductmsg);
        return this.queryById(kaGagProductmsg.getAutoid());
    }

}