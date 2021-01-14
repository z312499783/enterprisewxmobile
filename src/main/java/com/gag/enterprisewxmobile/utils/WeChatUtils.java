package com.gag.enterprisewxmobile.utils;

/**
 * weixin url工具类
 */
public class WeChatUtils {

    // access_token获取地址
    public final static String QY_WEIXIN_ACCESS_TOKEN = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=ID&corpsecret=SECRET";

    // 根据appid获取code
    public final static String QY_WEIXIN_OAUTH_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=CORPID&redirect_uri=REDIRECT_URI&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";

    //根据access_token和id获取部门列表
    public final static String QY_WEIXIN_DEPARTMENTLIST_URL = "https://qyapi.weixin.qq.com/cgi-bin/department/list?access_token=ACCESS_TOKEN&id=ID";

    //根据access_token和department_id和fetch_child获取部门成员信息
    public final static String  QY_WEIXIN_DEPARTMENTUSER_URL = "https://qyapi.weixin.qq.com/cgi-bin/user/simplelist?access_token=ACCESS_TOKEN&department_id=DEPARTMENT_ID&fetch_child=FETCH_CHILD";

    //根据access_token获取企业的jsapi_ticket用于JS-SDK第三方应用跳转浏览器
    public final static String QY_WEIXIN_JSAPI_TICKET_URL = "https://qyapi.weixin.qq.com/cgi-bin/get_jsapi_ticket?access_token=ACCESS_TOKEN";

    //根据access_token获取企业应用的jsapi_ticket用于JS-SDK第三方应用跳转浏览器
    public final static String QY_WEIXIN_AGENT_JSAPI_TICKET_URL = "https://qyapi.weixin.qq.com/cgi-bin/ticket/get?access_token=ACCESS_TOKEN&type=agent_config";

    // 根据access_token和code获取用户基本信息
    public final static String QY_WEIXIN_USERINFO_URL = "https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo?access_token=ACCESS_TOKEN&code=CODE";

    //根据应用access_token发送应用消息
    public final static String QY_WEIXIN_SENDAPPMSG_RUL="https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=ACCESS_TOKEN";

    //获取通讯录部门成员
    public final static String QY_ADDRESS_BOOK_URL= "https://qyapi.weixin.qq.com/cgi-bin/user/simplelist?access_token=ACCESS_TOKEN&department_id=DEPARTMENT_ID&fetch_child=FETCH_CHILD";
}