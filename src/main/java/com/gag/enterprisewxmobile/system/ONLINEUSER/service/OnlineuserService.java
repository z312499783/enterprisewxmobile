package com.gag.enterprisewxmobile.system.ONLINEUSER.service;

import com.gag.enterprisewxmobile.system.ONLINEUSER.entity.Onlineuser;
import java.util.List;

/**
 * (Onlineuser)表服务接口
 *
 * @author makejava
 * @since 2020-09-25 13:53:58
 */
public interface OnlineuserService {

    List<Onlineuser> checktimeOnlineuser();



    /**
     * 通过实体作为筛选条件查询
     *
     * @param onlineuser 实例对象
     * @return 对象列表
     */
    List<Onlineuser> queryAll(Onlineuser onlineuser);



}