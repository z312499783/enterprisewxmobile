package com.gag.enterprisewxmobile.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gag.enterprisewxmobile.system.approval.entity.Approval;
import com.gag.enterprisewxmobile.system.approval.service.ApprovalService;
import com.gag.enterprisewxmobile.system.party.entity.QywxSysParty;
import com.gag.enterprisewxmobile.system.party.service.QywxSysPartyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Component(value = "WeChatPartyUtils")
@Slf4j
public class WeChatPartyUtils {

    /**
     * 服务对象
     */
    @Resource
    private ApprovalService approvalService;

    @Resource
    private QywxSysPartyService qywxSysPartyService ;

    /**
     * 检查企业微信部门列表是否有此数据
     */
    @Transactional(rollbackFor = Exception.class)
    public void ExaminePartyList(String approvalId){
        Approval approval = approvalService.queryById(Integer.parseInt(approvalId));
        if (approval!=null){
            qywxSysPartyService.deleteAll();
            //调用方法获取去企业微信部门列表json
            JSONArray jsonArray = getPartyList(approval);
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject = JSONObject.parseObject(jsonArray.getString(i));
                //id
                String id = jsonObject.getString("id");
                //name
                String name = jsonObject.getString("name");
                //parentid
                String parentid = jsonObject.getString("parentid");
                //order
                String order = jsonObject.getString("order");

                QywxSysParty party = new QywxSysParty();
                party.setId(Integer.parseInt(id));
                party.setPartyname(name);
                party.setParentid(Integer.parseInt(parentid));
                party.setOrdernum(order);

                qywxSysPartyService.insert(party);
            }
        }else {
            log.info("错误:更新企业微信部门列表失败,approval表中approvalId为:"+approvalId+"的数据和应用不相等,请检查数据");
        }
    }

    /**
     * 获取企业微信部门列表json
     * @param approval
     * @return
     */
    public static JSONArray getPartyList(Approval approval){
        //获取路径,然后替换具体的参数
        String GET_DEPARTMENTLIST_URL = WeChatUtils.QY_WEIXIN_DEPARTMENTLIST_URL;
        String requestUrl = GET_DEPARTMENTLIST_URL.replace("ACCESS_TOKEN", approval.getAccessToken()).replace("id", "ID");
        //根据url去请求获取json
        JSONObject json = HttpInvoker.exec(requestUrl, "GET", null);
        //企业微信中员工用户信息
        JSONArray jsonArray = JSONArray.parseArray(json.getString("department"));
        //当jsonArray为空是记录日志,提示哪条数据可能出现问题
        if (jsonArray.isEmpty()){
            log.info("error:请求部门列表失败：approval中的approvalId="+approval.getApprovalId()+",请检查相对应的corpId、corpsecret、access_token");
        }
        return jsonArray;
    }
}
