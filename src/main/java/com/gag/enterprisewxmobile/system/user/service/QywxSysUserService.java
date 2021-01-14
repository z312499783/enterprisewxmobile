package com.gag.enterprisewxmobile.system.user.service;

import com.gag.enterprisewxmobile.system.user.entity.QywxSysUser;
import java.util.List;

/**
 * 企业微信用户信息表(QywxSysUser)表服务接口
 *
 * @author makejava
 * @since 2020-12-28 13:42:07
 */
public interface QywxSysUserService {

    QywxSysUser selectUserById(String userid);


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
     * 删除企业微信用户信息
     * @param userid
     * @return
     */
    int deleteUsermsgByUserid(String userid);

}