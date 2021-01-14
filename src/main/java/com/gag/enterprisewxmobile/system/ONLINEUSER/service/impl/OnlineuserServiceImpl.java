package com.gag.enterprisewxmobile.system.ONLINEUSER.service.impl;

import com.gag.enterprisewxmobile.system.ONLINEUSER.entity.Onlineuser;
import com.gag.enterprisewxmobile.system.ONLINEUSER.dao.OnlineuserDao;
import com.gag.enterprisewxmobile.system.ONLINEUSER.service.OnlineuserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Onlineuser)表服务实现类
 *
 * @author makejava
 * @since 2020-09-25 13:53:58
 */
@Service("onlineuserService")
public class OnlineuserServiceImpl implements OnlineuserService {

    @Resource
    private OnlineuserDao onlineuserDao;

    @Override
    public List<Onlineuser> checktimeOnlineuser(){
        return this.onlineuserDao.checktimeOnlineuser();
    };


    /**
     * 通过实体作为筛选条件查询
     *
     * @param onlineuser 实例对象
     * @return 对象列表
     */
    @Override
    public List<Onlineuser> queryAll(Onlineuser onlineuser){
        return this.onlineuserDao.queryAll(onlineuser);
    };



}