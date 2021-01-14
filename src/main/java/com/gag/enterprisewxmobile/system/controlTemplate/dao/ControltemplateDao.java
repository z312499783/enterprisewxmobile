package com.gag.enterprisewxmobile.system.controlTemplate.dao;

import com.gag.enterprisewxmobile.system.controlTemplate.entity.Controltemplate;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 审批通用模板表(Controltemplate)表数据库访问层
 *
 * @author makejava
 * @since 2020-05-26 09:25:07
 */
@Mapper
public interface ControltemplateDao {

    /**
     * 通过ID查询单条数据
     *
     * @param tableId 主键
     * @return 实例对象
     */
    Controltemplate queryById(Integer tableId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Controltemplate> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param controltemplate 实例对象
     * @return 对象列表
     */
    List<Controltemplate> queryAll(Controltemplate controltemplate);

    /**
     * 新增数据
     *
     * @param controltemplate 实例对象
     * @return 影响行数
     */
    int insert(Controltemplate controltemplate);

    /**
     * 修改数据
     *
     * @param controltemplate 实例对象
     * @return 影响行数
     */
    int update(Controltemplate controltemplate);

    /**
     * 通过主键删除数据
     *
     * @param tableId 主键
     * @return 影响行数
     */
    int deleteById(Integer tableId);

}