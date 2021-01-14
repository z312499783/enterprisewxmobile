package com.gag.enterprisewxmobile.system.MBEW.service.impl;

import com.gag.enterprisewxmobile.system.MBEW.entity.Mbew;
import com.gag.enterprisewxmobile.system.MBEW.dao.MbewDao;
import com.gag.enterprisewxmobile.system.MBEW.service.MbewService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Mbew)表服务实现类
 *
 * @author makejava
 * @since 2020-05-09 11:19:06
 */
@Service("mbewService")
public class MbewServiceImpl implements MbewService {
    @Resource
    private MbewDao mbewDao;

    @Override
    public String ekpoAndekko(){
        return this.mbewDao.ekpoAndekko();
    };

    /**
     * 通过实体作为筛选条件查询数据
     * @param mbew
     * @return
     */
    @Override
    public List<Mbew> querymoney(Mbew mbew){
        return this.mbewDao.querymoney(mbew);
    };


    /**
     * 通过实体作为筛选条件查询数据的数量
     * @param mbew
     * @return
     */
    @Override
    public int querysize(Mbew mbew){
        return this.mbewDao.querysize(mbew);
    };

    /**
     * 通过实体作为筛选条件查询
     *
     * @param mbew 实例对象
     * @return 对象列表
     */
    @Override
    public List<Mbew> queryAll(Mbew mbew){
        return this.mbewDao.queryAll(mbew);
    };


    /**
     * 通过ID查询单条数据
     *
     * @param autoid 主键
     * @return 实例对象
     */
    @Override
    public Mbew queryById(int autoid  ) {
        return this.mbewDao.queryById(autoid);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Mbew> queryAllByLimit(int offset, int limit) {
        return this.mbewDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param mbew 实例对象
     * @return 实例对象
     */
    @Override
    public Mbew insert(Mbew mbew) {
        this.mbewDao.insert(mbew);
        return mbew;
    }

    /**
     * 修改数据
     *
     * @param mbew 实例对象
     * @return 实例对象
     */
    @Override
    public Mbew update(Mbew mbew) {
        this.mbewDao.update(mbew);
        return this.queryById(mbew.getAutoid());
    }

    /**
     * 通过主键删除数据
     *
     * @param autoid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(int autoid  ) {
        return this.mbewDao.deleteById(autoid) > 0;
    }
}