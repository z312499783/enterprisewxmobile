package com.gag.enterprisewxmobile.system.user.service.impl;

import com.gag.enterprisewxmobile.system.user.entity.QywxSysUser;
import com.gag.enterprisewxmobile.system.user.dao.QywxSysUserDao;
import com.gag.enterprisewxmobile.system.user.service.QywxSysUserService;
import org.springframework.stereotype.Service;

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

    @Override
    public QywxSysUser selectUserById(String userid){
        return qywxSysUserDao.selectUserById(userid);
    };

    @Override
    public List<QywxSysUser> queryAll(QywxSysUser qywxSysUser) {
        return qywxSysUserDao.queryAll(qywxSysUser);
    }

    @Override
    public int insert(QywxSysUser qywxSysUser) {
        return qywxSysUserDao.insert(qywxSysUser);
    }

    @Override
    public int update(QywxSysUser qywxSysUser) {
        return qywxSysUserDao.update(qywxSysUser);
    }

    @Override
    public int deleteUsermsgByUserid(String userid) {
        return qywxSysUserDao.deleteUsermsgByUserid(userid);
    }
}