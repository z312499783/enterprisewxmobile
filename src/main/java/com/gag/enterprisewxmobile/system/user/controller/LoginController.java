package com.gag.enterprisewxmobile.system.user.controller;

import com.alibaba.fastjson.JSONObject;
import com.gag.enterprisewxmobile.monitor.job.service.impl.EnterprisewxJobServiceImpl;
import com.gag.enterprisewxmobile.system.approval.service.ApprovalService;
import com.gag.enterprisewxmobile.tool.common.BaseController;
import com.gag.enterprisewxmobile.tool.common.JSONResult;
import com.gag.enterprisewxmobile.utils.HttpInvoker;
import com.gag.enterprisewxmobile.utils.WeChatUtils;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.gag.enterprisewxmobile.tool.common.JSONResult.custom;
import static com.gag.enterprisewxmobile.tool.common.JSONResult.success;

/**
 * 企业微信用户信息表(Usermsg)表控制层
 *
 * @author makejava
 * @since 2020-04-20 10:50:49
 */
@Api(value = "企业微信登陆")
@Controller
@Slf4j
@RequestMapping("login")
public class LoginController extends BaseController {

    /**
     * 服务对象
     */
    @Resource
    private ApprovalService approvalService;

    /**
     * 进行登陆操作
     * @param request
     * @param response
     */
    @RequestMapping("login")
    public void login(HttpServletRequest request, HttpServletResponse response){
        String state = request.getParameter("state");
        //获取路径,然后替换具体的参数
        String GET_WEIXIN_OAUTH_URL = WeChatUtils.QY_WEIXIN_OAUTH_URL;
        String url = GET_WEIXIN_OAUTH_URL.replace("CORPID","ww0ddb06bb7e847826").replace("REDIRECT_URI","www.granlux.net:7671/usermsg/index").replace("STATE",state);
        try {
            response.sendRedirect(url);
        } catch (IOException e) {
            log.error("构造网页授权链接获取code失败:"+request.getRequestURI()+",错误提示:"+e);
            e.printStackTrace();
        }
    }

    /**
     * 执行帐号验证操作，验证通过跳转index。html主页
     * @param request
     * @return
     */
    @RequestMapping("index")
    public JSONResult index(HttpServletRequest request){
        //获取重定向后url的参数code
        String code = request.getParameter("code");
        //设置企业应用id
        String agentida = "1000010";
        String UserId = InsertOnlineuser(agentida,code);

        /**
         * 使用shiro编写认证操作
         */
        //1、获取Subject
        Subject subject = SecurityUtils.getSubject();

        //2、封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(UserId,UserId);

        //3、执行登录方法
        try {
            //登录
            subject.login(token);

            //跳转到index.html
            return success(token);
            //return "redirect:toindex";
            //验证用户名
        }catch (UnknownAccountException e){
            return custom(500,"用户不存在","");
            //验证密码
        }catch (IncorrectCredentialsException e){
            return custom(500,"密码错误","");
        }
    }

    /**
     * 获取用户信息,当获取成功时errmsg等于ok,则往Onlineuser表(存储过程)插入数据,并且判断是否拥有邮箱
     * @param agentid
     * @param code
     * @return
     */
    public String InsertOnlineuser(String agentid,String code){
        //获取用户信息
        JSONObject json = GetUserInfo(agentid,code);

        String UserId = json.getString("UserId");
        String errmsg = json.getString("errmsg");
        String errcode = json.getString("errcode");

        //判断是否请求成功,如何请求不成功不添加数据
        if (!(errcode.equals("0"))){
            log.error("获取访问用户身份失败:请检查approval表相关数据,现在进行更新任务调度");
            //执行定时任务,更新accesstoken
            int run = new EnterprisewxJobServiceImpl().executejob("WeChatAccessTokenUtils", "ExamineAccesstokenEndTime");
            //判断是否更新成功
            if (run==1){
            }
        }
        return UserId;
    }


    /**
     * 根据参数查询企业微信网业授权用户信息
     * @param agentid
     * @param code
     * @return
     */
    public JSONObject GetUserInfo(String agentid,String code){
        //根据agentid查询阿ccesstoken
        String accesstoken = approvalService.queryAccessToken(agentid);
        //获取路径,然后替换具体的参数
        String GET_WEIXIN_USERINFO_URL = WeChatUtils.QY_WEIXIN_USERINFO_URL;
        String requestUrl = GET_WEIXIN_USERINFO_URL.replace("ACCESS_TOKEN",accesstoken).replace("CODE",code);
        //根据url去请求获取json
        JSONObject json = HttpInvoker.exec(requestUrl, "GET", null);

        return json;
    }

}