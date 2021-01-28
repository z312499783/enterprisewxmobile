package com.gag.enterprisewxmobile.system.controlTemplate.service.impl;

import com.gag.enterprisewxmobile.framework.cache.RedisUtil;
import com.gag.enterprisewxmobile.system.controlTemplate.entity.Controltemplate;
import com.gag.enterprisewxmobile.system.controlTemplate.dao.ControltemplateDao;
import com.gag.enterprisewxmobile.system.controlTemplate.service.ControltemplateService;
import com.gag.enterprisewxmobile.tool.common.utils.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 审批通用模板表(Controltemplate)表服务实现类
 *
 * @author makejava
 * @since 2020-05-26 09:25:08
 */
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
    @Override
    public List<Controltemplate> queryAll(Controltemplate controltemplate){
        List<Controltemplate> list = this.controltemplateDao.queryAll(controltemplate);
        return list;
    };

    /**
     * 通过ID查询单条数据
     *
     * @param tableId 主键
     * @return 实例对象
     */
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
    @Override
    public boolean deleteById(Integer tableId) {
        return this.controltemplateDao.deleteById(tableId) > 0;
    }
}