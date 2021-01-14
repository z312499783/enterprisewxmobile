package com.gag.enterprisewxmobile.system.kaSystem.dao;

import com.gag.enterprisewxmobile.system.kaSystem.entity.KaSystemCodeshopmsg;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

/**
 * ka系统条码商品号信息(KaSystemCodeshopmsg)表数据库访问层
 *
 * @author makejava
 * @since 2020-10-30 15:07:11
 */
@Mapper
public interface KaSystemCodeshopmsgDao {

    /**
     * 通过ID查询单条数据
     *
     * @param autoid 主键
     * @return 实例对象
     */
    KaSystemCodeshopmsg queryById(Integer autoid);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<KaSystemCodeshopmsg> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param kaSystemCodeshopmsg 实例对象
     * @return 对象列表
     */
    List<KaSystemCodeshopmsg> queryAll(KaSystemCodeshopmsg kaSystemCodeshopmsg);

    /**
     * 新增数据
     *
     * @param kaSystemCodeshopmsg 实例对象
     * @return 影响行数
     */
    int insert(KaSystemCodeshopmsg kaSystemCodeshopmsg);

    int insertmsg(Map map);

    int selectmsg(String article_code);

    int updatemsg(Map map);

    /**
     * 修改数据
     *
     * @param kaSystemCodeshopmsg 实例对象
     * @return 影响行数
     */
    int update(KaSystemCodeshopmsg kaSystemCodeshopmsg);

    /**
     * 通过主键删除数据
     *
     * @param autoid 主键
     * @return 影响行数
     */
    int deleteById(Integer autoid);

}