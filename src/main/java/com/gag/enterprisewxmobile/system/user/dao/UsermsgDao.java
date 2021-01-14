package com.gag.enterprisewxmobile.system.user.dao;

import com.gag.enterprisewxmobile.system.user.entity.Usermsg;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 企业微信用户信息表(Usermsg)表数据库访问层
 *
 * @author makejava
 * @since 2020-04-20 10:50:48
 */
@Mapper
public interface UsermsgDao {


    /**
     * 通过企业微信账号查询数据
     * @param userid
     * @return
     */
    Usermsg findByName(String userid);

    /**
     * 通过ID查询单条数据
     *
     * @param usermsgId 主键
     * @return 实例对象
     */
    Usermsg queryById(Integer usermsgId);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param usermsg 实例对象
     * @return 对象列表
     */
    List<Usermsg> queryAll(Usermsg usermsg);

    /**
     * 新增数据
     *
     * @param usermsg 实例对象
     * @return 影响行数
     */
    int insert(Usermsg usermsg);

    /**
     * 修改数据
     *
     * @param usermsg 实例对象
     * @return 影响行数
     */
    int update(Usermsg usermsg);

    /**
     * 通过主键删除数据
     *
     * @param usermsgId 主键
     * @return 影响行数
     */
    int deleteById(Integer usermsgId);

    /**
     * 删除企业微信用户信息
     * @param userid
     * @return
     */
    int deleteUsermsgByUserid(String userid);

}