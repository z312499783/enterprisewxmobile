package com.gag.enterprisewxmobile.system.controlTemplate.service.impl;

import com.gag.enterprisewxmobile.system.controlTemplate.entity.Controltemplate;
import com.gag.enterprisewxmobile.system.controlTemplate.dao.ControltemplateDao;
import com.gag.enterprisewxmobile.system.controlTemplate.service.ControltemplateService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 审批通用模板表(Controltemplate)表服务实现类
 *
 * @author makejava
 * @since 2020-05-26 09:25:08
 */
@CacheConfig(cacheNames = "controltemplateService")
@Service("controltemplateService")
public class ControltemplateServiceImpl implements ControltemplateService {

    @Resource
    private ControltemplateDao controltemplateDao;

    /**
     * 通过实体作为筛选条件查询
     *
     * @param controltemplate 实例对象
     * @return 对象列表
     */
    @Cacheable(key = "#root.targetClass.typeName")
    @Override
    public List<Controltemplate> queryAll(Controltemplate controltemplate){
        return this.controltemplateDao.queryAll(controltemplate);
    };

    /**
     * 通过ID查询单条数据
     *
     * @param tableId 主键
     * @return 实例对象
     */
    @Cacheable(key = "#tableId")
    @Override
    public Controltemplate queryById(Integer tableId) {
        return this.controltemplateDao.queryById(tableId);
    }


    /**
     * 新增数据
     *
     * @param controltemplate 实例对象
     * @return 实例对象
     */
    @CachePut(key = "#root.targetClass.typeName")
    @Override
    public Controltemplate insert(Controltemplate controltemplate) {
        this.controltemplateDao.insert(controltemplate);
        return controltemplate;
    }

    /**
     * 修改数据
     *
     * @param controltemplate 实例对象
     * @return 实例对象
     */
    @CachePut(key = "#root.targetClass.typeName")
    @Override
    public Controltemplate update(Controltemplate controltemplate) {
        this.controltemplateDao.update(controltemplate);
        return this.queryById(controltemplate.getTableId());
    }

    /**
     * 通过主键删除数据
     *
     * @param tableId 主键
     * @return 是否成功
     */
    @CacheEvict(key = "#tableId")
    @Override
    public boolean deleteById(Integer tableId) {
        return this.controltemplateDao.deleteById(tableId) > 0;
    }
}