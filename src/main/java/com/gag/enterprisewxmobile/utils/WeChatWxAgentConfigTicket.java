package com.gag.enterprisewxmobile.utils;

import com.alibaba.fastjson.JSONObject;
import com.gag.enterprisewxmobile.system.approval.entity.Approval;
import com.gag.enterprisewxmobile.system.approval.service.ApprovalService;
import com.gag.enterprisewxmobile.system.ticket.entity.Ticket;
import com.gag.enterprisewxmobile.system.ticket.service.TicketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Component(value = "WeChatWxAgentConfigTicket")
@Slf4j
/**
 * 企业的Ticket
 */
public class WeChatWxAgentConfigTicket {
    /**
     * 服务对象
     */
    @Resource
    private ApprovalService approvalService;

    @Resource
    private TicketService ticketService ;

    /**
     * 定时检查应用的jsapi_ticket是否到期,以jsapi_ticket的到期时间做检查(不可检查jsapi_ticket,jsapi_ticket访问过多会受到频率拦截)如果到期则调用ExamineTicket()修改
     */
    public void ExamineTicketEndTime(String str){
        String regex=",";
        String[] strs  =str.split(regex);
        for (int i=0;i<strs.length;i++){
            Ticket ticket = new Ticket();
            ticket.setAgentidNum(strs[i]);
            //获取ticket表中符合条件的企业微信自建应用的信息
            List<Ticket> ti = ticketService.queryAll(ticket);
            for (Ticket t:ti){
                //根据ticket的agentidNum查询approval的信息用来获取应用的jsapi_ticket,approval表满足条件的仅有一条数据
                Approval approval = approvalService.queryAgentid(t.getAgentidNum());
                Date endtime = t.getEndTime();
                //判断当前时间是否大于ticket到期时间，如果大于那么调用ExamineAccessToken()修改应用jsapi_ticket
                if (new Date().compareTo(endtime)>=0){
                    ExamineTicket(approval.getAgentid(),approval.getAccessToken());
                }
            }
        }
    }

    /**
     * 检查应用jsapi_ticket是否相等,如果不相等那么则修改
     * @param agentidNum
     * @param accesstoken
     */
    public void ExamineTicket(String agentidNum,String accesstoken){
        //调用获取应用的jsapi_ticket方法
        String jsapi_ticket = getJsapiTicket(agentidNum,accesstoken);
        //根据agentidNum查询ticket
        String ti = ticketService.queryTicket(agentidNum);
        //判断ticket是否存在agentId_num等于参数agentidNum
        if (ti!=null){
            //判断应用jsapi_ticket是否更新,如果更新则修改数据
            if (!(jsapi_ticket.equals(ti))){
                log.info("应用jsapi_ticket过期,正在更新jsapi_ticket");
                Ticket ticket = new Ticket();
                ticket.setAgentidNum(agentidNum);
                ticket.setTicket(jsapi_ticket);
                ticket.setStartTime(new Date());
                Long time = System.currentTimeMillis();
                time +=120*1000*60;//在当前系统时间的基础上往后加120分钟
                //设置获取凭证结束时间
                ticket.setEndTime(new Date(time));
                ticketService.update(ticket);
                log.info("应用jsapi_ticket更新完成");
            }
        }
    }

    //获取应用的jsapi_ticket
    public static String getJsapiTicket(String agentidNum,String accesstoken){
        //获取路径,然后替换具体的参数
        String GET_AGENT_JSAPI_TICKET_URL = WeChatUtils.QY_WEIXIN_AGENT_JSAPI_TICKET_URL;
        String requestUrl = GET_AGENT_JSAPI_TICKET_URL.replace("ACCESS_TOKEN",accesstoken);
        //根据url去请求获取json
        JSONObject json = HttpInvoker.exec(requestUrl, "GET", null);
        //企业微信中员工用户信息
        String ticket = null;
        if (json != null) {
            try {
                ticket = json.getString("ticket");
            } catch (Exception e) {
                log.info("获取自建应用AgentId为:"+agentidNum+"的jsapi_ticket失败,请检查ticket表以及approval的agentid为"+agentidNum+"数据");
            }
        }
        if (ticket==null){
            log.info("应用jsapi_ticket："+json);
        }
        return ticket;
    }
}
