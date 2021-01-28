package com.gag.enterprisewxmobile.system.kaSystem.service.impl;

import com.gag.enterprisewxmobile.system.kaSystem.entity.KaSystemTime;
import com.gag.enterprisewxmobile.system.kaSystem.entity.ShopPos;
import com.gag.enterprisewxmobile.system.kaSystem.dao.ShopPosDao;
import com.gag.enterprisewxmobile.system.kaSystem.service.ShopPosService;
import com.gag.enterprisewxmobile.tool.gen.entity.ColumnInfo;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * 商品销售(ShopPos)表服务实现类
 *
 * @author makejava
 * @since 2020-11-02 15:59:49
 */
//@CacheConfig(cacheNames = "shopPosService")
@Service("shopPosService")
public class ShopPosServiceImpl implements ShopPosService {
    @Resource
    private ShopPosDao shopPosDao;

    @Override
    public LinkedList<LinkedHashMap> kaSystem(KaSystemTime kaSystemTime){
        return this.shopPosDao.kaSystem(kaSystemTime);
    };

    @Override
    public List<ColumnInfo> selectTableColumnsByName(){
        return this.shopPosDao.selectTableColumnsByName();
    };

    //@Cacheable(key = "#root.targetClass.typeName")
    @Override
    public List<LinkedHashMap> selectmseg(ShopPos shopPos){
        return this.shopPosDao.selectmseg(shopPos);
    };
}