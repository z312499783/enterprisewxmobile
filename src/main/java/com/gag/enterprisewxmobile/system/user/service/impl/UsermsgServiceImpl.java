package com.gag.enterprisewxmobile.system.user.service.impl;

import com.gag.enterprisewxmobile.system.user.entity.Usermsg;
import com.gag.enterprisewxmobile.system.user.dao.UsermsgDao;
import com.gag.enterprisewxmobile.system.user.service.UsermsgService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 企业微信用户信息表(Usermsg)表服务实现类
 *
 * @author makejava
 * @since 2020-04-20 10:50:49
 */
@Service("usermsgService")
public class UsermsgServiceImpl implements UsermsgService {

    @Resource
    private UsermsgDao usermsgDao;
    /**
     * 通过企业微信账号查询数据
     * @param userid
     * @return
     */
    @Override
    public Usermsg findByName(String userid){
        return this.usermsgDao.findByName(userid);
    };


    /**
     * 通过实体作为筛选条件查询
     *
     * @param usermsg 实例对象
     * @return 对象列表
     */
    @Override
    public List<Usermsg> queryAll(Usermsg usermsg){
        return this.usermsgDao.queryAll(usermsg);
    };

    /**
     * 通过ID查询单条数据
     *
     * @param usermsgId 主键
     * @return 实例对象
     */
    @Override
    public Usermsg queryById(Integer usermsgId) {
        return this.usermsgDao.queryById(usermsgId);
    }


    /**
     * 新增数据
     *
     * @param usermsg 实例对象
     * @return 实例对象
     */
    @Override
    public Usermsg insert(Usermsg usermsg) {
        this.usermsgDao.insert(usermsg);
        return usermsg;
    }

    /**
     * 修改数据
     *
     * @param usermsg 实例对象
     * @return 实例对象
     */
    @Override
    public Usermsg update(Usermsg usermsg) {
        this.usermsgDao.update(usermsg);
        return this.queryById(usermsg.getUsermsgId());
    }

    /**
     * 通过主键删除数据
     *
     * @param usermsgId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer usermsgId) {
        return this.usermsgDao.deleteById(usermsgId) > 0;
    }


    /**
     * 删除企业微信用户信息
     * @param userid
     * @return
     */
    @Override
    public int deleteUsermsgByUserid(String userid){
        return this.usermsgDao.deleteUsermsgByUserid(userid);
    };
}