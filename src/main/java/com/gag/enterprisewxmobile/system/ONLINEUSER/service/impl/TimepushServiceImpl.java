package com.gag.enterprisewxmobile.system.ONLINEUSER.service.impl;

import com.gag.enterprisewxmobile.system.ONLINEUSER.entity.Timepush;
import com.gag.enterprisewxmobile.system.ONLINEUSER.dao.TimepushDao;
import com.gag.enterprisewxmobile.system.ONLINEUSER.service.TimepushService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Timepush)表服务实现类
 *
 * @author makejava
 * @since 2020-09-25 15:18:37
 */
@Service("timepushService")
public class TimepushServiceImpl implements TimepushService {
    @Resource
    private TimepushDao timepushDao;



    /**
     * 通过实体作为筛选条件查询
     *
     * @param timepush 实例对象
     * @return 对象列表
     */
    @Override
    public List<Timepush> queryAll(Timepush timepush){
        return this.timepushDao.queryAll(timepush);
    };



    /**
     * 修改数据
     *
     * @param timepush 实例对象
     * @return 实例对象
     */
    @Override
    public int update(Timepush timepush) {
        return this.timepushDao.update(timepush);
    }


}