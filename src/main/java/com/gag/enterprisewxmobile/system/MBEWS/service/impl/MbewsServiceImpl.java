package com.gag.enterprisewxmobile.system.MBEWS.service.impl;

import com.gag.enterprisewxmobile.system.MBEWS.entity.Mbews;
import com.gag.enterprisewxmobile.system.MBEWS.dao.MbewsDao;
import com.gag.enterprisewxmobile.system.MBEWS.service.MbewsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Mbews)表服务实现类
 *
 * @author makejava
 * @since 2020-05-14 10:31:56
 */
@Service("mbewsService")
public class MbewsServiceImpl implements MbewsService {
    @Resource
    private MbewsDao mbewsDao;

    /**
     * 通过实体作为筛选条件查询MBEWS
     * @param mbews
     * @return
     */
    @Override
    public List<Mbews> queryMbews(Mbews mbews){
        return this.mbewsDao.queryMbews(mbews);
    };

    /**
     * 通过实体作为筛选条件查询数据数量
     * @param mbews
     * @return
     */
    @Override
    public int querysize(Mbews mbews){
        return this.mbewsDao.querysize(mbews);
    };

    /**
     * 通过ID查询单条数据
     *
     * @param autoid 主键
     * @return 实例对象
     */
    @Override
    public Mbews queryById(Integer autoid ) {
        return this.mbewsDao.queryById(autoid);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Mbews> queryAllByLimit(int offset, int limit) {
        return this.mbewsDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param mbews 实例对象
     * @return 实例对象
     */
    @Override
    public Mbews insert(Mbews mbews) {
        this.mbewsDao.insert(mbews);
        return mbews;
    }

    /**
     * 修改数据
     *
     * @param mbews 实例对象
     * @return 实例对象
     */
    @Override
    public Mbews update(Mbews mbews) {
        this.mbewsDao.update(mbews);
        return this.queryById(mbews.getAutoid());
    }

    /**
     * 通过主键删除数据
     *
     * @param autoid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer autoid ) {
        return this.mbewsDao.deleteById(autoid) > 0;
    }
}