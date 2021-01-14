package com.gag.enterprisewxmobile.system.sendAppMsg.dao;

import com.gag.enterprisewxmobile.system.sendAppMsg.entity.QywxSendappmsgAndLoginfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (QywxSendappmsgAndLoginfo)表数据库访问层
 *
 * @author makejava
 * @since 2020-06-08 17:13:00
 */
@Mapper
public interface QywxSendappmsgAndLoginfoDao {

    /**
     * 通过ID查询单条数据
     *
     * @param msgAndLoginfoId 主键
     * @return 实例对象
     */
    QywxSendappmsgAndLoginfo queryById(Integer msgAndLoginfoId);



    /**
     * 通过实体作为筛选条件查询
     *
     * @param qywxSendappmsgAndLoginfo 实例对象
     * @return 对象列表
     */
    List<QywxSendappmsgAndLoginfo> queryAll(QywxSendappmsgAndLoginfo qywxSendappmsgAndLoginfo);

    /**
     * 新增数据
     *
     * @param qywxSendappmsgAndLoginfo 实例对象
     * @return 影响行数
     */
    int insert(QywxSendappmsgAndLoginfo qywxSendappmsgAndLoginfo);

    /**
     * 修改数据
     *
     * @param qywxSendappmsgAndLoginfo 实例对象
     * @return 影响行数
     */
    int update(QywxSendappmsgAndLoginfo qywxSendappmsgAndLoginfo);

    /**
     * 通过主键删除数据
     *
     * @param msgAndLoginfoId 主键
     * @return 影响行数
     */
    int deleteById(Integer msgAndLoginfoId);

}