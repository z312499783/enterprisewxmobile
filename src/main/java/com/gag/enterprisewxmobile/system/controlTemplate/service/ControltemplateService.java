package com.gag.enterprisewxmobile.system.controlTemplate.service;

import com.gag.enterprisewxmobile.system.controlTemplate.entity.Controltemplate;
import java.util.List;

/**
 * 审批通用模板表(Controltemplate)表服务接口
 *
 * @author makejava
 * @since 2020-05-26 09:25:08
 */
public interface ControltemplateService {

    /**
     * 通过实体作为筛选条件查询
     *
     * @param controltemplate 实例对象
     * @return 对象列表
     */
    List<Controltemplate> queryAll(Controltemplate controltemplate);

    /**
     * 通过ID查询单条数据
     *
     * @param tableId 主键
     * @return 实例对象
     */
    Controltemplate queryById(Integer tableId);


    /**
     * 新增数据
     *
     * @param controltemplate 实例对象
     * @return 实例对象
     */
    Controltemplate insert(Controltemplate controltemplate);

    /**
     * 修改数据
     *
     * @param controltemplate 实例对象
     * @return 实例对象
     */
    Controltemplate update(Controltemplate controltemplate);

    /**
     * 通过主键删除数据
     *
     * @param tableId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer tableId);

}