package com.gag.enterprisewxmobile.system.kaSystem.service.impl;

import com.gag.enterprisewxmobile.system.kaSystem.entity.KaSystemCodeshopmsg;
import com.gag.enterprisewxmobile.system.kaSystem.dao.KaSystemCodeshopmsgDao;
import com.gag.enterprisewxmobile.system.kaSystem.service.KaSystemCodeshopmsgService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * ka系统条码商品号信息(KaSystemCodeshopmsg)表服务实现类
 *
 * @author makejava
 * @since 2020-10-30 15:07:11
 */
@Service("kaSystemCodeshopmsgService")
public class KaSystemCodeshopmsgServiceImpl implements KaSystemCodeshopmsgService {
    @Resource
    private KaSystemCodeshopmsgDao kaSystemCodeshopmsgDao;

    /**
     * 通过ID查询单条数据
     *
     * @param autoid 主键
     * @return 实例对象
     */
    @Override
    public KaSystemCodeshopmsg queryById(Integer autoid) {
        return this.kaSystemCodeshopmsgDao.queryById(autoid);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<KaSystemCodeshopmsg> queryAllByLimit(int offset, int limit) {
        return this.kaSystemCodeshopmsgDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param kaSystemCodeshopmsg 实例对象
     * @return 实例对象
     */
    @Override
    public KaSystemCodeshopmsg insert(KaSystemCodeshopmsg kaSystemCodeshopmsg) {
        this.kaSystemCodeshopmsgDao.insert(kaSystemCodeshopmsg);
        return kaSystemCodeshopmsg;
    }

    @Override
    public int insertmsg(Map map){
        return this.kaSystemCodeshopmsgDao.insertmsg(map);
    };

    @Override
    public int selectmsg(String article_code){
        return this.kaSystemCodeshopmsgDao.selectmsg(article_code);
    };

    @Override
    public int updatemsg(Map map){
        return this.kaSystemCodeshopmsgDao.updatemsg(map);
    };


    /**
     * 修改数据
     *
     * @param kaSystemCodeshopmsg 实例对象
     * @return 实例对象
     */
    @Override
    public KaSystemCodeshopmsg update(KaSystemCodeshopmsg kaSystemCodeshopmsg) {
        this.kaSystemCodeshopmsgDao.update(kaSystemCodeshopmsg);
        return this.queryById(kaSystemCodeshopmsg.getAutoid());
    }

    /**
     * 通过主键删除数据
     *
     * @param autoid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer autoid) {
        return this.kaSystemCodeshopmsgDao.deleteById(autoid) > 0;
    }
}