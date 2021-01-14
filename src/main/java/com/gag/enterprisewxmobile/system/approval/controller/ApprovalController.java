package com.gag.enterprisewxmobile.system.approval.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gag.enterprisewxmobile.system.approval.entity.Approval;
import com.gag.enterprisewxmobile.system.approval.service.ApprovalService;
import com.gag.enterprisewxmobile.tool.common.JSONResult;
import com.gag.enterprisewxmobile.utils.HttpInvoker;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * 企业微信相关数据(Approval)表控制层
 *
 * @author makejava
 * @since 2020-04-20 17:20:11
 */
@Api(value = "企业微信相关数据")
@Controller
@RequestMapping("approval")
public class ApprovalController {

    /**
     * 服务对象
     */
    @Resource
    private ApprovalService approvalService;

    /**
     * 通过企业应用agentid获取AccessToken
     * @param agentid
     * @return
     */
    @ApiOperation(value = "企业微信相关数据查询",notes = "根据agentid查询企业微信相关数据",response = JSONResult.class,httpMethod = "GET")
    @GetMapping("select/{agentid}")
    @ResponseBody
    public JSONResult selectAccessToken(@ApiParam(name = "agentid",value = "企业微信数据agentid",required = true) @PathVariable("agentid") String agentid){
        String accessToken = this.approvalService.queryAccessToken(agentid);
        return JSONResult.custom(accessToken);
    }


    /**
     * 获取有多少个审批单号
     * @param accessToken
     * @param starttime
     * @param endtime
     * @param cursor
     * @param size
     * @return
     */
    public int getSp_nolength(String starttime,String endtime,int cursor,int size,String accessToken){
        String sp_no_list = getSp_no_list(starttime,endtime,cursor,size,accessToken);
        JSONObject jsonObject = JSONObject.parseObject(sp_no_list);
        String s = jsonObject.getString("sp_no_list");
        return JSONArray.parseArray(s).size();
    }

    /**
     * 获取审批单号
     * @param index
     * @param accessToken
     * @param starttime
     * @param endtime
     * @param cursor
     * @param size
     * @return
     */
    public String getSp_no(int index,String accessToken,String starttime,String endtime,int cursor,int size){
        String sp_no_list = getSp_no_list(starttime,endtime,cursor,size,accessToken);
        JSONObject jsonObject = JSONObject.parseObject(sp_no_list);
        String s = jsonObject.getString("sp_no_list");
        return JSONArray.parseArray(s).get(index).toString();
    }

    /**
     * 获取批量审批单号
     * @param starttime
     * @param endtime
     * @param cursor
     * @param size
     */
    public String getSp_no_list(String starttime, String endtime, int cursor, int size,String accessToken){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("starttime",starttime);
        jsonObject.put("endtime",endtime);
        jsonObject.put("cursor",cursor);
        jsonObject.put("size",size);
        String path = "https://qyapi.weixin.qq.com/cgi-bin/oa/getapprovalinfo?access_token="+accessToken;

        jsonObject = HttpInvoker.postResponse(path,jsonObject);

        return jsonObject.toString();
    }

    /**
     * 根据审批单号获取审批详情
     * @param sp_no
     * @return
     */
    public String getInfo(String sp_no,String accessToken){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sp_no",sp_no);
        String path = "https://qyapi.weixin.qq.com/cgi-bin/oa/getapprovaldetail?access_token="+accessToken;

        jsonObject = HttpInvoker.postResponse(path,jsonObject);

        return jsonObject.toString();
    }
}