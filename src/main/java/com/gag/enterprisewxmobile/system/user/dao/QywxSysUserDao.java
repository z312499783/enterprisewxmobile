package com.gag.enterprisewxmobile.system.user.dao;

import com.gag.enterprisewxmobile.system.user.entity.QywxSysUser;
import com.gag.enterprisewxmobile.system.user.entity.Usermsg;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 企业微信用户信息表(QywxSysUser)表数据库访问层
 *
 * @author makejava
 * @since 2020-12-28 13:42:07
 */
@Mapper
public interface QywxSysUserDao {



    /**
     * 通过企业微信账号查询数据
     * @param userid
     * @return
     */
    QywxSysUser findByName(String userid);

    /**
     * 通过ID查询单条数据
     *
     * @param usermsgId 主键
     * @return 实例对象
     */
    QywxSysUser queryById(Integer usermsgId);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param qywxSysUser 实例对象
     * @return 对象列表
     */
    List<QywxSysUser> queryAll(QywxSysUser qywxSysUser);

    /**
     * 新增数据
     *
     * @param qywxSysUser 实例对象
     * @return 影响行数
     */
    int insert(QywxSysUser qywxSysUser);

    /**
     * 修改数据
     *
     * @param qywxSysUser 实例对象
     * @return 影响行数
     */
    int update(QywxSysUser qywxSysUser);

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