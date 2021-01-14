package com.gag.enterprisewxmobile.system.dict.service.impl;

import com.gag.enterprisewxmobile.system.dict.dao.QywxSysDictDataDao;
import com.gag.enterprisewxmobile.system.dict.entity.QywxSysDictType;
import com.gag.enterprisewxmobile.system.dict.dao.QywxSysDictTypeDao;
import com.gag.enterprisewxmobile.system.dict.service.QywxSysDictTypeService;
import com.gag.enterprisewxmobile.tool.common.ShiroUtils;
import com.gag.enterprisewxmobile.tool.common.exception.BusinessException;
import com.gag.enterprisewxmobile.tool.common.utils.Convert;
import com.gag.enterprisewxmobile.tool.common.utils.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 字典类型表(QywxSysDictType)表服务实现类
 *
 * @author makejava
 * @since 2021-01-11 17:06:10
 */
@Service("qywxSysDictTypeService")
public class QywxSysDictTypeServiceImpl implements QywxSysDictTypeService {

    @Resource
    private QywxSysDictTypeDao qywxSysDictTypeDao;

    @Resource
    private QywxSysDictDataDao qywxSysDictDataDao;

    /**
     * 根据条件分页查询字典类型
     *
     * @param dictType 字典类型信息
     * @return 字典类型集合信息
     */
    @Override
    public List<QywxSysDictType> selectDictTypeList(QywxSysDictType dictType)
    {
        return qywxSysDictTypeDao.selectDictTypeList(dictType);
    }

    /**
     * 根据所有字典类型
     *
     * @return 字典类型集合信息
     */
    @Override
    public List<QywxSysDictType> selectDictTypeAll()
    {
        return qywxSysDictTypeDao.selectDictTypeAll();
    }

    /**
     * 根据字典类型ID查询信息
     *
     * @param dictId 字典类型ID
     * @return 字典类型
     */
    @Override
    public QywxSysDictType selectDictTypeById(int dictId)
    {
        return qywxSysDictTypeDao.selectDictTypeById(dictId);
    }

    /**
     * 通过字典ID删除字典信息
     *
     * @param dictId 字典ID
     * @return 结果
     */
    @Override
    public int deleteDictTypeById(int dictId)
    {
        return qywxSysDictTypeDao.deleteDictTypeById(dictId);
    }

    /**
     * 批量删除字典类型
     *
     * @param ids 需要删除的数据
     * @return 结果
     */
    @Override
    public int deleteDictTypeByIds(String ids) throws BusinessException
    {
        Integer[] dictIds = Convert.toIntArray(ids);
        for (Integer dictId : dictIds)
        {
            QywxSysDictType dictType = selectDictTypeById(dictId);
            if (qywxSysDictDataDao.countDictDataByType(dictType.getDictType()) > 0)
            {
                throw new BusinessException(String.format("%1$s已分配,不能删除", dictType.getDictName()));
            }
        }

        return qywxSysDictTypeDao.deleteDictTypeByIds(dictIds);
    }

    /**
     * 新增保存字典类型信息
     *
     * @param dictType 字典类型信息
     * @return 结果
     */
    @Override
    public int insertDictType(QywxSysDictType dictType)
    {
        dictType.setCreateBy(ShiroUtils.getSysUser().getUserid());
        return qywxSysDictTypeDao.insertDictType(dictType);
    }

    /**
     * 修改保存字典类型信息
     *
     * @param dictType 字典类型信息
     * @return 结果
     */
    @Override
    public int updateDictType(QywxSysDictType dictType)
    {
        dictType.setUpdateBy(ShiroUtils.getSysUser().getUserid());
        QywxSysDictType oldDict = qywxSysDictTypeDao.selectDictTypeById(dictType.getDictId());
        qywxSysDictDataDao.updateDictDataType(oldDict.getDictType(), dictType.getDictType());
        return qywxSysDictTypeDao.updateDictType(dictType);
    }

    /**
     * 校验字典类型称是否唯一
     *
     * @param dict 字典类型
     * @return 结果
     */
    @Override
    public Boolean checkDictTypeUnique(QywxSysDictType dict)
    {
        Long dictId = StringUtils.isNull(dict.getDictId()) ? -1L : dict.getDictId();
        QywxSysDictType dictType = qywxSysDictTypeDao.checkDictTypeUnique(dict.getDictType());
        if (StringUtils.isNotNull(dictType) && dictType.getDictId().longValue() != dictId.longValue())
        {
            return true;
        }
        return false;
    }
}