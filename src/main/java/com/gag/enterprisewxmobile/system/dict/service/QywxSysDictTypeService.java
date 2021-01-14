package com.gag.enterprisewxmobile.system.dict.service;

import com.gag.enterprisewxmobile.system.dict.entity.QywxSysDictType;
import java.util.List;

/**
 * 字典类型表(QywxSysDictType)表服务接口
 *
 * @author makejava
 * @since 2021-01-11 17:06:10
 */
public interface QywxSysDictTypeService {

    /**
     * 根据条件分页查询字典类型
     *
     * @param dictType 字典类型信息
     * @return 字典类型集合信息
     */
    public List<QywxSysDictType> selectDictTypeList(QywxSysDictType dictType);

    /**
     * 根据所有字典类型
     *
     * @return 字典类型集合信息
     */
    public List<QywxSysDictType> selectDictTypeAll();

    /**
     * 根据字典类型ID查询信息
     *
     * @param dictId 字典类型ID
     * @return 字典类型
     */
    public QywxSysDictType selectDictTypeById(int dictId);

    /**
     * 通过字典ID删除字典信息
     *
     * @param dictId 字典ID
     * @return 结果
     */
    public int deleteDictTypeById(int dictId);

    /**
     * 批量删除字典类型
     *
     * @param ids 需要删除的数据
     * @return 结果
     * @throws Exception 异常
     */
    public int deleteDictTypeByIds(String ids) throws Exception;

    /**
     * 新增保存字典类型信息
     *
     * @param dictType 字典类型信息
     * @return 结果
     */
    public int insertDictType(QywxSysDictType dictType);

    /**
     * 修改保存字典类型信息
     *
     * @param dictType 字典类型信息
     * @return 结果
     */
    public int updateDictType(QywxSysDictType dictType);

    /**
     * 校验字典类型称是否唯一
     *
     * @param dictType 字典类型
     * @return 结果
     */
    public Boolean checkDictTypeUnique(QywxSysDictType dictType);

}