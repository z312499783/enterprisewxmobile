package com.gag.enterprisewxmobile.system.sendAppMsg.dao;

        import com.gag.enterprisewxmobile.system.sendAppMsg.entity.QywxSendappmsg;
        import org.apache.ibatis.annotations.Mapper;
        import org.apache.ibatis.annotations.Param;
        import java.util.List;

/**
 * (QywxSendappmsg)表数据库访问层
 *
 * @author makejava
 * @since 2020-06-01 10:27:28
 */
@Mapper
public interface QywxSendappmsgDao {


    /**
     * 根据QywxSendappmsg的id和企业微信应用的agentiid查询需要发送消息
     * @param send_app_msg_id
     * @return
     */
    QywxSendappmsg queryBymsg_id(Integer send_app_msg_id);

}