package com.gag.enterprisewxmobile.system.sendAppMsg.service;

import com.gag.enterprisewxmobile.system.sendAppMsg.entity.QywxSendappmsgAndLoginfo;
import java.util.List;

/**
 * (QywxSendappmsgAndLoginfo)表服务接口
 *
 * @author makejava
 * @since 2020-06-08 17:13:00
 */
public interface QywxSendappmsgAndLoginfoService {

    /**
     * 通过ID查询单条数据
     *
     * @param msgAndLoginfoId 主键
     * @return 实例对象
     */
    QywxSendappmsgAndLoginfo queryById(Integer msgAndLoginfoId);


    /**
     * 新增数据
     *
     * @param qywxSendappmsgAndLoginfo 实例对象
     * @return 实例对象
     */
    QywxSendappmsgAndLoginfo insert(QywxSendappmsgAndLoginfo qywxSendappmsgAndLoginfo);

    /**
     * 修改数据
     *
     * @param qywxSendappmsgAndLoginfo 实例对象
     * @return 实例对象
     */
    QywxSendappmsgAndLoginfo update(QywxSendappmsgAndLoginfo qywxSendappmsgAndLoginfo);

    /**
     * 通过主键删除数据
     *
     * @param msgAndLoginfoId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer msgAndLoginfoId);

}