package com.gag.enterprisewxmobile.system.shoppos.service.impl;

import com.gag.enterprisewxmobile.system.shoppos.entity.ShopPosLog;
import com.gag.enterprisewxmobile.system.shoppos.dao.ShopPosLogDao;
import com.gag.enterprisewxmobile.system.shoppos.service.ShopPosLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 商品销售日志(ShopPosLog)表服务实现类
 *
 * @author makejava
 * @since 2020-10-15 10:01:55
 */
@Service("shopPosLogService")
public class ShopPosLogServiceImpl implements ShopPosLogService {
    @Resource
    private ShopPosLogDao shopPosLogDao;

    /**
     * 通过ID查询单条数据
     *
     * @param autoid 主键
     * @return 实例对象
     */
    @Override
    public ShopPosLog queryById(Integer autoid) {
        return this.shopPosLogDao.queryById(autoid);
    }


    /**
     * 通过实体作为筛选条件查询
     *
     * @param shopPosLog 实例对象
     * @return 对象列表
     */
    @Override
    public List<ShopPosLog> queryAll(ShopPosLog shopPosLog){
        return this.shopPosLogDao.queryAll(shopPosLog);
    };


    /**
     * 新增数据
     *
     * @param shopPosLog 实例对象
     * @return 实例对象
     */
    @Override
    public ShopPosLog insert(ShopPosLog shopPosLog) {
        this.shopPosLogDao.insert(shopPosLog);
        return shopPosLog;
    }

    /**
     * 修改数据
     *
     * @param shopPosLog 实例对象
     * @return 实例对象
     */
    @Override
    public ShopPosLog update(ShopPosLog shopPosLog) {
        this.shopPosLogDao.update(shopPosLog);
        return this.queryById(shopPosLog.getAutoid());
    }

    /**
     * 通过主键删除数据
     *
     * @param autoid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer autoid) {
        return this.shopPosLogDao.deleteById(autoid) > 0;
    }
}