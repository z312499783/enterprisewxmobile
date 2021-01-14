package com.gag.enterprisewxmobile.utils;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gag.enterprisewxmobile.framework.config.SpringContextUtil;
import com.gag.enterprisewxmobile.framework.stringSubUtil.StringSubUtil;
import com.gag.enterprisewxmobile.monitor.job.service.EnterprisewxJobService;
import com.gag.enterprisewxmobile.monitor.job.service.impl.EnterprisewxJobServiceImpl;
import com.gag.enterprisewxmobile.system.MBEW.entity.Mbew;
import com.gag.enterprisewxmobile.system.MBEW.service.MbewService;
import com.gag.enterprisewxmobile.system.ONLINEUSER.entity.Onlineuser;
import com.gag.enterprisewxmobile.system.ONLINEUSER.entity.Timepush;
import com.gag.enterprisewxmobile.system.ONLINEUSER.service.OnlineuserService;
import com.gag.enterprisewxmobile.system.ONLINEUSER.service.TimepushService;
import com.gag.enterprisewxmobile.system.approval.service.ApprovalService;
import com.gag.enterprisewxmobile.system.sendAppMsg.entity.*;
import com.gag.enterprisewxmobile.system.sendAppMsg.service.QywxSendappmsgAndLoginfoService;
import com.gag.enterprisewxmobile.system.sendAppMsg.service.QywxSendappmsgLogInfoService;
import com.gag.enterprisewxmobile.system.sendAppMsg.service.QywxSendappmsgLogService;
import com.gag.enterprisewxmobile.system.sendAppMsg.service.QywxSendappmsgService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Component(value = "WeChatSendAppMsgUtils")
@Slf4j
public class WeChatSendAppMsgUtils {
    /**
     * 服务对象
     */
    @Resource
    private ApprovalService approvalService;

    @Resource
    private EnterprisewxJobService enterprisewxJobService;

    @Resource
    private QywxSendappmsgService qywxSendappmsgService;

    @Resource
    private MbewService mbewService;

    @Resource
    private OnlineuserService onlineuserService;

    @Resource
    private TimepushService timepushService;

    @Resource
    private QywxSendappmsgLogService qywxSendappmsgLogService;

    @Resource
    private QywxSendappmsgLogInfoService qywxSendappmsgLogInfoService;

    @Resource
    private QywxSendappmsgAndLoginfoService qywxSendappmsgAndLoginfoService;

    public void ExaminKQJB(String method_params){
        QywxSendappmsgAndLoginfo qywxSendappmsgAndLoginfo = qywxSendappmsgAndLoginfoService.queryById(Integer.parseInt(method_params));
        String queryBymsg_id = qywxSendappmsgAndLoginfo.getSendappmsgId().toString();
        String jobInfo_id = qywxSendappmsgAndLoginfo.getLoginfoId().toString();
        QywxSendappmsgLogInfo qywxSendappmsgLogInfo = qywxSendappmsgLogInfoService.queryById(Integer.parseInt(jobInfo_id));
        QywxSendappmsgLog qywxSendappmsgLog = getqywxSendappmsgLog(qywxSendappmsgLogInfo);
        QywxSendappmsg querymsg = qywxSendappmsgService.queryBymsg_id(Integer.parseInt(queryBymsg_id));
        List<Onlineuser> onlineusers = onlineuserService.checktimeOnlineuser();
        for (int i=0;i<onlineusers.size();i++){
            String msg = "姓名:"+onlineusers.get(i).getName()+",已进入仓库"+onlineusers.get(i).getAddtime()+onlineusers.get(i).getUnit()+"未出";
            judgeSendNum(msg,querymsg,qywxSendappmsgLog,qywxSendappmsgLogInfo);
        }
        if (onlineusers.size()==0){
            QywxSendappmsgAndLoginfo qywxSendappmsgAndLoginfo2 = qywxSendappmsgAndLoginfoService.queryById(4);
            String queryBymsg_id2 = qywxSendappmsgAndLoginfo2.getSendappmsgId().toString();
            String jobInfo_id2 = qywxSendappmsgAndLoginfo2.getLoginfoId().toString();
            QywxSendappmsgLogInfo qywxSendappmsgLogInfo2 = qywxSendappmsgLogInfoService.queryById(Integer.parseInt(jobInfo_id2));
            QywxSendappmsgLog qywxSendappmsgLog2 = getqywxSendappmsgLog(qywxSendappmsgLogInfo2);
            QywxSendappmsg querymsg2 = qywxSendappmsgService.queryBymsg_id(Integer.parseInt(queryBymsg_id2));
            //judgeSendNum("无异常",querymsg2,qywxSendappmsgLog2,qywxSendappmsgLogInfo2);
            qywxSendappmsgLogService.insert(qywxSendappmsgLog2);
        }

    }


    public void ExamineMsgPrice(String method_params){
        QywxSendappmsgAndLoginfo qywxSendappmsgAndLoginfo = qywxSendappmsgAndLoginfoService.queryById(Integer.parseInt(method_params));
        String queryBymsg_id = qywxSendappmsgAndLoginfo.getSendappmsgId().toString();
        String jobInfo_id = qywxSendappmsgAndLoginfo.getLoginfoId().toString();
        QywxSendappmsgLogInfo qywxSendappmsgLogInfo = qywxSendappmsgLogInfoService.queryById(Integer.parseInt(jobInfo_id));
        QywxSendappmsgLog qywxSendappmsgLog = getqywxSendappmsgLog(qywxSendappmsgLogInfo);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dt = simpleDateFormat.format(new Date());
        Mbew mbew = new Mbew();
        mbew.setStartdt(dt);
        mbew.setDt(dt);
        List<Mbew> list = this.mbewService.querymoney(mbew);
        BigDecimal bigDecimal = BigDecimal.valueOf(500);
        boolean boo = true;
        for (Mbew mbew1: list) {
            if (mbew1.getMoney1().compareTo(bigDecimal) >-1){
                String content = "价格异常:工厂werks="+mbew1.getWerks()+"/物料matnr="+mbew1.getMatnr()+"/单价money1="+mbew1.getMoney1()+"/时间dt="+mbew1.getDt();
                QywxSendappmsg querymsg = qywxSendappmsgService.queryBymsg_id(Integer.parseInt(queryBymsg_id));
                querymsg.setText(new QywxSendappmsgContent(content));
                qywxSendappmsgLog.setJobMessage(content);
                qywxSendappmsgLog.setMethodParams(querymsg.getAgentid().toString());
                if (sendAppmsg(querymsg,qywxSendappmsgLog.getJobGroup())==true){
                    qywxSendappmsgLogService.insert(qywxSendappmsgLog);
                }else {
                    qywxSendappmsgLog.setStatus("1");
                    qywxSendappmsgLog.setExceptionInfo(qywxSendappmsgLogInfo.getInfoExceptionInfo());
                    qywxSendappmsgLogService.insert(qywxSendappmsgLog);
                }
                boo = false;
            }
        }
        if (boo==true){
            qywxSendappmsgLogService.insert(qywxSendappmsgLog);
        }

    }


    public void ExamineSendAppmsg(String method_params){
        QywxSendappmsgAndLoginfo qywxSendappmsgAndLoginfo = qywxSendappmsgAndLoginfoService.queryById(Integer.parseInt(method_params));
        String queryBymsg_id = qywxSendappmsgAndLoginfo.getSendappmsgId().toString();
        String jobInfo_id = qywxSendappmsgAndLoginfo.getLoginfoId().toString();
        QywxSendappmsgLogInfo qywxSendappmsgLogInfo = qywxSendappmsgLogInfoService.queryById(Integer.parseInt(jobInfo_id));
        QywxSendappmsgLog qywxSendappmsgLog = getqywxSendappmsgLog(qywxSendappmsgLogInfo);
        QywxSendappmsg querymsg = qywxSendappmsgService.queryBymsg_id(Integer.parseInt(queryBymsg_id));
        String msg = this.mbewService.ekpoAndekko();
        if (!(msg.equals(null)||msg.equals(""))){
            judgeSendNum(msg,querymsg,qywxSendappmsgLog,qywxSendappmsgLogInfo);
            } else {
            QywxSendappmsgAndLoginfo qywxSendappmsgAndLoginfo2 = qywxSendappmsgAndLoginfoService.queryById(3);
            String queryBymsg_id2 = qywxSendappmsgAndLoginfo2.getSendappmsgId().toString();
            String jobInfo_id2 = qywxSendappmsgAndLoginfo2.getLoginfoId().toString();
            QywxSendappmsgLogInfo qywxSendappmsgLogInfo2 = qywxSendappmsgLogInfoService.queryById(Integer.parseInt(jobInfo_id2));
            QywxSendappmsgLog qywxSendappmsgLog2 = getqywxSendappmsgLog(qywxSendappmsgLogInfo2);
            QywxSendappmsg querymsg2 = qywxSendappmsgService.queryBymsg_id(Integer.parseInt(queryBymsg_id2));
            judgeSendNum("无异常",querymsg2,qywxSendappmsgLog2,qywxSendappmsgLogInfo2);
            qywxSendappmsgLogService.insert(qywxSendappmsgLog2);
        }
    }


    //qywx应用发送消息
    public void judgeSendNum(String msg,QywxSendappmsg querymsg,QywxSendappmsgLog qywxSendappmsgLog,QywxSendappmsgLogInfo qywxSendappmsgLogInfo){
        int msglength = 0;
        try {
            //获取往企业微信推送发送消息的字节长度（字节编码为UTF-8）
            msglength = StringSubUtil.getStringByteLenths(msg);
            //初始化企业微信推送最长字节数
            int msgSenglength = 2048;
            //当需要发送的消息字节大于2048时,进行多次发送
            if (msglength>msgSenglength){
                //获取发送的次数
                int sendnum = msglength/msgSenglength;
                //当这值大于0时发送次数加1
                if (msglength%msgSenglength>0){ sendnum = sendnum+1; }
                //当sendnum>0时循环发送消息
                for (int i =0;i<sendnum;i++){
                    //初始化需要发送的消息
                    String sendmsg = "";
                    //当第一次发送的时候
                    if (i==0){
                        sendmsg = StringSubUtil.substringByte(msg, i, msgSenglength);//0,2048
                    }else {
                        sendmsg = StringSubUtil.substringByte(msg, (i * msgSenglength)+1, msgSenglength-1);//2049,2047
                    }
                    //将需要发送的消息set到qywxSendappmsgContent实体类中
                    querymsg.setText(new QywxSendappmsgContent(sendmsg));
                    //当企业微信发送应用消息成功或者失败都添加日志
                    qywxSendappmsgLog.setJobMessage(sendmsg);
                    qywxSendappmsgLog.setMethodParams(querymsg.getAgentid().toString());
                    //判断企业微信应用推送消息是否成功
                    sendAppmsgAndAddlog(querymsg,qywxSendappmsgLog,qywxSendappmsgLogInfo);
                }
            } else {
                //将需要发送的消息set到qywxSendappmsgContent实体类中
                querymsg.setText(new QywxSendappmsgContent(msg));
                //当企业微信发送应用消息成功或者失败都添加日志
                qywxSendappmsgLog.setJobMessage(msg);
                qywxSendappmsgLog.setMethodParams(querymsg.getAgentid().toString());
                //判断企业微信应用推送消息是否成功
                sendAppmsgAndAddlog(querymsg,qywxSendappmsgLog,qywxSendappmsgLogInfo);
            }
        } catch (UnsupportedEncodingException e) {
            log.error("推送任务ExamineSendAppmsg不支持的编码异常:"+msg);
            e.printStackTrace();
        }
    }


    /**
     * 调用发送企业微信应用消息方法以及相关日志
     * @param querymsg
     * @param qywxSendappmsgLog
     * @param qywxSendappmsgLogInfo
     */
    public void sendAppmsgAndAddlog(QywxSendappmsg querymsg,QywxSendappmsgLog qywxSendappmsgLog,QywxSendappmsgLogInfo qywxSendappmsgLogInfo){
        //判断企业微信应用推送消息是否成功
        if (sendAppmsg(querymsg,qywxSendappmsgLog.getJobGroup())==true){
            qywxSendappmsgLogService.insert(qywxSendappmsgLog);
        }else {
            qywxSendappmsgLog.setStatus("1");
            //当推送消息发送失败后添加失败消息
            qywxSendappmsgLog.setExceptionInfo(qywxSendappmsgLogInfo.getInfoExceptionInfo());
            qywxSendappmsgLogService.insert(qywxSendappmsgLog);
        }
    }

    /**
     * 根据agentid和querymsg发送企业微信应用消息,jobGroup任务组名
     * @param querymsg
     * @param jobGroup
     */
    public Boolean sendAppmsg(QywxSendappmsg querymsg,String jobGroup){
        //根据agentid获取应用accesstoken
        String accessToken = approvalService.queryAccessToken(querymsg.getAgentid().toString());
        //将企业微信应用消息需要的实体类转换为String
        ObjectMapper mapper = new ObjectMapper();
        String value = null;
        try {
            value = mapper.writeValueAsString(querymsg);
        } catch (JsonProcessingException e) {
            log.error("企业微信应用消息推送失败:"+jobGroup+"价格异常：转换json错误"+e);
            e.printStackTrace();
        }
        //构建发送企业微信应用消息的json
        JSONObject jsonObject = JSONObject.parseObject(value);
        //构建url
        String GET_SENDAPPMSG_RUL = WeChatUtils.QY_WEIXIN_SENDAPPMSG_RUL;
        String requestUrl = GET_SENDAPPMSG_RUL.replace("ACCESS_TOKEN", accessToken);
        //发送企业微信应用消息
        JSONObject jsonObject1 = HttpInvoker.postResponse(requestUrl, jsonObject);
        //判断是否发送成功
        String errcode = jsonObject1.get("errcode").toString();
        if (errcode.equals("0")){
            return true;
        }else {
            log.error("企业微信推送消息失败，正在执行定时任务更新accesstoken，调用者是sendAppmsg()："+querymsg+"----"+jobGroup);
            //执行定时任务,更新accesstoken
            int run = enterprisewxJobService.executejob("WeChatAccessTokenUtils", "ExamineAccesstokenEndTime");
            //判断是否更新成功
            if (run==1){
            }
            return false;
        }
    }

    /**
     * 根据定时任务日志信息返回一个定时任务日志,用于定时发送企业微信推送消息日志
     * @param qywxSendappmsgLogInfo
     * @return
     */
    public static QywxSendappmsgLog getqywxSendappmsgLog(QywxSendappmsgLogInfo qywxSendappmsgLogInfo){
        QywxSendappmsgLog qywxSendappmsgLog = new QywxSendappmsgLog();
        qywxSendappmsgLog.setJobName(qywxSendappmsgLogInfo.getJobInfoName());
        qywxSendappmsgLog.setJobGroup(qywxSendappmsgLogInfo.getJobInfoGroup());
        qywxSendappmsgLog.setMethodName(qywxSendappmsgLogInfo.getMethodInfoName());
        qywxSendappmsgLog.setMethodParams(qywxSendappmsgLogInfo.getMethodInfoParams());
        qywxSendappmsgLog.setJobMessage(qywxSendappmsgLogInfo.getJobInfoMessage());
        qywxSendappmsgLog.setStatus(qywxSendappmsgLogInfo.getInfoStatus());
        qywxSendappmsgLog.setCreateTime(new Date());
        //qywxSendappmsgLog.setExceptionInfo(qywxSendappmsgLogInfo.getInfoExceptionInfo());
        return qywxSendappmsgLog;
    }
}
