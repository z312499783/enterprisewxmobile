package com.gag.enterprisewxmobile.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gag.enterprisewxmobile.system.approval.entity.Approval;
import com.gag.enterprisewxmobile.system.approval.service.ApprovalService;
import com.gag.enterprisewxmobile.system.user.entity.Usermsg;
import com.gag.enterprisewxmobile.system.user.service.UsermsgService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Component(value = "WeChatUserMsgUtils")
public class WeChatUserMsgUtils {

    /**
     * 服务对象
     */
    @Resource
    private ApprovalService approvalService;

    @Resource
    private UsermsgService usermsgService;


    public void ExamineUserMsg(String approvalId){
        //获取list中的access_token
        Approval approval = approvalService.queryById(Integer.parseInt(approvalId));

        if (approval!=null){
            //获取parties中id为0(无上级部门的部门,就是总公司),fetch_child为1递归获取是否,0为获取本部门,由于总公司下是部门
            //JSONArray jsonArray = getUserMsg(approval,partyService.queryParentid(0),1);
            JSONArray jsonArray = getUserMsg(approval,1,1);
            //判断数据库有没有这条数据,没有就添加
            for (int i=0;i<jsonArray.size();i++){
                JSONObject jsonObject1 = JSONObject.parseObject(jsonArray.getString(i));
                //用户userid
                String userid = jsonObject1.getString("userid");
                //用户姓名
                String name = jsonObject1.getString("name");
                JSONArray jsonArray1 = JSONArray.parseArray(jsonObject1.getString("department"));
                //用户所属部门编号
                int department = Integer.parseInt(jsonArray1.getString(0));

                Usermsg usermsg = new Usermsg();
                usermsg.setUserid(userid);
                usermsg.setName(name);
                usermsg.setDepartment(department);
                //判断数据库数据是否相同，不相同就修改
                List<Usermsg> list2 = usermsgService.queryAll(null);

                for (int j=0;j<list2.size();j++){
                    if (list2.get(j).getUserid().equals(userid)){
                        if (!(list2.get(j).getName().equals(name)&&list2.get(j).getDepartment().equals(department))){
                            usermsgService.update(usermsg);
                        }
                    }
                }
                //查询数据如果没有相同的数据就添加
                List<Usermsg> list3 = usermsgService.queryAll(usermsg);
                if (list3.size()==0){
                    usermsgService.insert(usermsg);
                }
            }
            //判断数据库有没有多余的数据，有就删除
            List<Usermsg> list = usermsgService.queryAll(null);
            for (int i=0;i<list.size();i++){
                String userid = "";
                boolean boo = true;
                for (int j=0;j<jsonArray.size();j++){
                    JSONObject jsonObject1 = JSONObject.parseObject(jsonArray.getString(j));
                    userid = list.get(i).getUserid();
                    if (list.get(i).getUserid().equals(jsonObject1.getString("userid"))){
                        boo = false;
                    }
                }
                if (boo==true){
                    usermsgService.deleteUsermsgByUserid(userid);
                }
            }
        }else {
            log.info("错误:更新企业微信部门员工用户信息失败,approval表中agentid为:"+approval.getAgentid()+"的数据和应用不相等或是Null,请检查数据");
        }
    }

    /**
     * 获取企业微信部门员工用户信息json
     * @param approval
     * @return
     */
    public static JSONArray getUserMsg(Approval approval,int department_id,int fetch_child){
        //获取路径,然后替换具体的参数
        String GET_DEPARTMENTUSER_URL = WeChatUtils.QY_WEIXIN_DEPARTMENTUSER_URL;
        String requestUrl = GET_DEPARTMENTUSER_URL.replace("ACCESS_TOKEN", approval.getAccessToken()).replace("DEPARTMENT_ID", String.valueOf(department_id)).replace("FETCH_CHILD",String.valueOf(fetch_child));
        //根据url去请求获取json
        JSONObject jsonObject = HttpInvoker.exec(requestUrl, "GET", null);
        //企业微信的员工用户信息
        JSONArray jsonArray = JSONArray.parseArray(jsonObject.getString("userlist"));
        if (jsonArray.isEmpty()){
            log.info("error:请求企业微信部门员工用户信息失败：approval中的agentid="+approval.getAgentid()+",请检查相对应的corpId、corpsecret、access_token");
        }
        return jsonArray;
    }
}
