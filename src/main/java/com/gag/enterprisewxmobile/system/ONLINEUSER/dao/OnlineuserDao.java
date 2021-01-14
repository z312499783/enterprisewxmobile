package com.gag.enterprisewxmobile.system.ONLINEUSER.dao;

import com.gag.enterprisewxmobile.monitor.job.entity.EnterprisewxJob;
import com.gag.enterprisewxmobile.system.ONLINEUSER.entity.Onlineuser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (Onlineuser)表数据库访问层
 *
 * @author makejava
 * @since 2020-09-25 13:53:57
 */
@Mapper
public interface OnlineuserDao {

    List<Onlineuser> checktimeOnlineuser();




    /**
     * 通过实体作为筛选条件查询
     *
     * @param onlineuser 实例对象
     * @return 对象列表
     */
    List<Onlineuser> queryAll(Onlineuser onlineuser);

}