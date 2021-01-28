package com.gag.enterprisewxmobile.system.user.service.impl;

import com.gag.enterprisewxmobile.system.user.entity.QywxSysUser;
import com.gag.enterprisewxmobile.system.user.dao.QywxSysUserDao;
import com.gag.enterprisewxmobile.system.user.entity.Usermsg;
import com.gag.enterprisewxmobile.system.user.service.QywxSysUserService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;
import java.util.List;

/**
 * 企业微信用户信息表(QywxSysUser)表服务实现类
 *
 * @author makejava
 * @since 2020-12-28 13:42:07
 */
@Service("qywxSysUserService")
public class QywxSysUserServiceImpl implements QywxSysUserService {

    @Resource
    private QywxSysUserDao qywxSysUserDao;

    /**
     * 通过企业微信账号查询数据
     * @param userid
     * @return
     */
    @Override
    public QywxSysUser findByName(String userid){
        return this.qywxSysUserDao.findByName(userid);
    };


    /**
     * 通过实体作为筛选条件查询
     *
     * @param qywxSysUser 实例对象
     * @return 对象列表
     */
    @Override
    public List<QywxSysUser> queryAll(QywxSysUser qywxSysUser){
        return this.qywxSysUserDao.queryAll(qywxSysUser);
    };

    /**
     * 通过ID查询单条数据
     *
     * @param usermsgId 主键
     * @return 实例对象
     */
    @Override
    public QywxSysUser queryById(Integer usermsgId) {
        return this.qywxSysUserDao.queryById(usermsgId);
    }


    /**
     * 新增数据
     *
     * @param qywxSysUser 实例对象
     * @return 实例对象
     */
    @Override
    public QywxSysUser insert(QywxSysUser qywxSysUser) {
        this.qywxSysUserDao.insert(qywxSysUser);
        return qywxSysUser;
    }

    /**
     * 修改数据
     *
     * @param qywxSysUser 实例对象
     * @return 实例对象
     */
    @Override
    public QywxSysUser update(QywxSysUser qywxSysUser) {
        this.qywxSysUserDao.update(qywxSysUser);
        return this.queryById(qywxSysUser.getUsermsgId());
    }

    /**
     * 通过主键删除数据
     *
     * @param usermsgId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer usermsgId) {
        return this.qywxSysUserDao.deleteById(usermsgId) > 0;
    }


    /**
     * 删除企业微信用户信息
     * @param userid
     * @return
     */
    @Override
    public int deleteQywxSysUserByUserid(String userid){
        return this.qywxSysUserDao.deleteUsermsgByUserid(userid);
    };
}