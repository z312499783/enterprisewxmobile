package com.gag.enterprisewxmobile.utils;


import com.alibaba.fastjson.JSONObject;
import com.gag.enterprisewxmobile.system.approval.entity.Approval;
import com.gag.enterprisewxmobile.system.approval.service.ApprovalService;
import com.gag.enterprisewxmobile.tool.common.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 获取access_token工具类
 * @author ouyangjun
 *
 */
@Component(value = "WeChatAccessTokenUtils")
@Slf4j
public class WeChatAccessTokenUtils {

    /**
     * 服务对象
     */
    @Resource
    private ApprovalService approvalService;

    /**
     * 定时检查accesstoken时间是否到期(不可检查accesstoken,accesstoken访问过多会受到频率拦截),如果到期则调用ExamineAccessToken()修改
     */
    public void ExamineAccesstokenEndTime(){
        List<Approval> list = approvalService.queryAll(null);
        for (Approval approval:list){
            Date endtime = approval.getEndTime();
            //判断当前时间是否大于accesstoken到期时间，如果大于那么调用ExamineAccessToken()修改accesstoken
            if (new Date().compareTo(endtime)>=0){
                ExamineAccessToken(approval);
            }
        }
    }

    /**
     * 检查accesstoken是否不同,如果不同则修改
     */
    public void ExamineAccessToken(Approval approval){
        //调用getAccessToken()请求获取AccessToken
        String accessToken =  getAccessToken(approval.getCorpid(),approval.getCorpsecret());
        //判断数据库的AccessToken是否和请求获取链接的AccessToken相同,如果不相同就修改数据
        if (!(accessToken.equals(approval.getAccessToken()))){
            log.info("access_token过期,正在更新access_token");
            //approval.setAgentid(approval.getAgentid());
            approval.setAccessToken(accessToken);
            approval.setStartTime(new Date());
            Long time = System.currentTimeMillis();
            time +=120*1000*60;//在当前系统时间的基础上往后加120分钟
            //设置获取凭证结束时间
            approval.setEndTime(new Date(time));
            approvalService.update(approval);
            log.info("access_token更新完成");
        }
    }

    /**
     * @param corpid 企业号的标识
     * @param corpsecret 管理组凭证密钥
     * @return
     * @throws @description: 获取AccessToken
     * @author: ouyangjun
     * @date:
     */
    public static String getAccessToken(String corpid, String corpsecret) {
        //获取路径,然后替换具体的参数
        String GET_ACCESS_TOKEN_URL = WeChatUtils.QY_WEIXIN_ACCESS_TOKEN;
        String requestUrl = GET_ACCESS_TOKEN_URL.replace("ID", StringUtils.trim(corpid)).replace("SECRET", StringUtils.trim(corpsecret));
        System.out.println(requestUrl);
        JSONObject json = HttpInvoker.exec(requestUrl, "GET", null);
        String token = null;
        if (json != null) {
            try {
                token = json.getString("access_token");
                // 打印消息
                String message = String.format("获取access_token成功  access token: %s, expire_in: %s", json.getString("access_token"),json.getInteger("expires_in"));
                //log.info(message);
            } catch (Exception e) {
                String error = String.format("获取access_token失败    errcode: %s ,errmsg: %s", json.getInteger("errcode"),json.getString("errmsg"));
                log.info(error);
            }
        }
        if (token==null){
            log.info("请求accessToken失败："+json);
        }
        return token;
    }

}