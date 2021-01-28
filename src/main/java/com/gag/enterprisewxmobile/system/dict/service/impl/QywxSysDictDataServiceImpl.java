package com.gag.enterprisewxmobile.system.dict.service.impl;

import com.gag.enterprisewxmobile.system.dict.entity.QywxSysDictData;
import com.gag.enterprisewxmobile.system.dict.dao.QywxSysDictDataDao;
import com.gag.enterprisewxmobile.system.dict.service.QywxSysDictDataService;
import com.gag.enterprisewxmobile.tool.common.ShiroUtils;
import com.gag.enterprisewxmobile.tool.common.utils.Convert;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 字典数据表(QywxSysDictData)表服务实现类
 *
 * @author makejava
 * @since 2021-01-11 17:05:45
 */
//@CacheConfig(cacheNames = "qywxSysDictDataService")
@Service("qywxSysDictDataService")
public class QywxSysDictDataServiceImpl implements QywxSysDictDataService {

    @Resource
    private QywxSysDictDataDao qywxSysDictDataDao;

    /**
     * 根据条件分页查询字典数据
     *
     * @param dictData 字典数据信息
     * @return 字典数据集合信息
     */
    //@Cacheable(key = "#root.targetClass.typeName")
    @Override
    public List<QywxSysDictData> selectDictDataList(QywxSysDictData dictData) {
        return qywxSysDictDataDao.selectDictDataList(dictData);
    }

    /**
     * 根据字典类型查询字典数据
     *
     * @param dictType 字典类型
     * @return 字典数据集合信息
     */
    @Override
    public List<QywxSysDictData> selectDictDataByType(String dictType) {
        return qywxSysDictDataDao.selectDictDataByType(dictType);
    }

    /**
     * 根据字典类型和字典键值查询字典数据信息
     *
     * @param dictType 字典类型
     * @param dictValue 字典键值
     * @return 字典标签
     */

    @Override
    public String selectDictLabel(String dictType, String dictValue) {
        return qywxSysDictDataDao.selectDictLabel(dictType, dictValue);
    }

    @Override
    public QywxSysDictData selectDictDataById(int dictCode) {
        return qywxSysDictDataDao.selectDictDataById(dictCode);
    }

    /**
     * 通过字典ID删除字典数据信息
     *
     * @param dictCode 字典数据ID
     * @return 结果
     */
    //@CacheEvict(key = "#root.targetClass.typeName")
    @Override
    public int deleteDictDataById(int dictCode) {
        return qywxSysDictDataDao.deleteDictDataById(dictCode);
    }

    /**
     * 批量删除字典数据
     *
     * @param ids 需要删除的数据
     * @return 结果
     */
    //@CacheEvict(key = "#root.targetClass.typeName")
    @Override
    public int deleteDictDataByIds(String ids) {
        return qywxSysDictDataDao.deleteDictDataByIds(Convert.toStrArray(ids));
    }

    /**
     * 新增保存字典数据信息
     *
     * @param dictData 字典数据信息
     * @return 结果
     */
    //@CachePut(key = "#root.targetClass.typeName")
    @Override
    public int insertDictData(QywxSysDictData dictData) {
        dictData.setCreateBy(ShiroUtils.getSysUser().getUserid());
        return qywxSysDictDataDao.insertDictData(dictData);
    }

    /**
     * 修改保存字典数据信息
     *
     * @param dictData 字典数据信息
     * @return 结果
     */
    //@CachePut(key = "#root.targetClass.typeName")
    @Override
    public int updateDictData(QywxSysDictData dictData) {
        dictData.setUpdateBy(ShiroUtils.getSysUser().getUserid());
        return qywxSysDictDataDao.updateDictData(dictData);
    }
}