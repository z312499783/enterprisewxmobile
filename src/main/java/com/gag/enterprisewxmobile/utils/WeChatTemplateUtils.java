package com.gag.enterprisewxmobile.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gag.enterprisewxmobile.system.approval.controller.ApprovalController;
import com.gag.enterprisewxmobile.system.approval.service.ApprovalService;
import com.gag.enterprisewxmobile.system.controlTemplate.entity.Controltemplate;
import com.gag.enterprisewxmobile.system.controlTemplate.service.ControltemplateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Slf4j
@Component(value = "WeChatTemplateUtils")
public class WeChatTemplateUtils {
    /**
     * 服务对象
     */
    @Resource
    private ControltemplateService controltemplateService;

    @Resource
    private ApprovalService approvalService;


    //日期格式转换
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    //初始化转换类型
    String dareString="";
    long longTimeStamp;
    Date date = new Date();

    /**
     * 添加及查询数据
     * @throws Exception
     */
    public void AddandUpdate() throws Exception {
        String accessToken = approvalService.queryAccessToken("3010040");
        //把当前时间转换为时间戳
        String endtime = null;
        String starttime = null;
        Long longTime = simpleDateFormat.parse(simpleDateFormat.format(date)).getTime()/1000;
        endtime = Long.toString(longTime);

        //获取当前时间-一个月并且转换为时间戳
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH,-30);
        date = calendar.getTime();
        Long longTime1 = simpleDateFormat.parse(simpleDateFormat.format(date)).getTime()/1000;
        starttime = Long.toString(longTime1);
        for (int cursor=0;getSp_no_index(starttime,endtime,cursor,100,accessToken)==false;cursor+=100){
            getSp_no_index(starttime,endtime,cursor,100,accessToken);
        }
        System.getenv();
    }

    /**
     * 根据审批单号的下标获取审批单号
     * @param starttime
     * @param endtime
     * @param cursor
     * @param size
     * @param accessToken
     * @throws ParseException
     */
    public boolean getSp_no_index(String starttime,String endtime,int cursor,int size,String accessToken) throws Exception {
        boolean boo=true;
        //获取审批数据数量
        int index = new ApprovalController().getSp_nolength(starttime,endtime,cursor,size,accessToken);
        if (index==100){
            boo =false;
        }
        for (int i=0;i<index;i++){
            //获取审批等具体详情
            getDetails(i,starttime,endtime,cursor,size,accessToken);
        }
        return boo;
    }

    /**
     * 根据审批编号获取审批详情json
     * @param index
     * @param starttime
     * @param endtime
     * @param cursor
     * @param size
     * @return
     */
    public String getMessage(int index,String starttime,String endtime,int cursor,int size){
        ApprovalController approvalController =new ApprovalController();
        String accessToken = approvalService.queryAccessToken("3010040");
        return approvalController.getInfo(approvalController.getSp_no(index,accessToken,starttime,endtime,cursor,size),accessToken);
    }

    /**
     * 公共审批编号等
     * @param index
     * @param starttime
     * @param endtime
     * @param cursor
     * @param size
     * @param accessToken
     * @throws Exception
     */
    public void getDetails(int index, String starttime, String endtime, int cursor, int size, String accessToken) throws Exception {
        //根据审批编号获取审批详情json
        JSONObject jsonObject = JSONObject.parseObject(getMessage(index, starttime, endtime, cursor, size));
        //主要详情
        JSONObject jsonObject1 = JSONObject.parseObject(jsonObject.getString("info"));
        JSONObject jsonObject2 = JSONObject.parseObject(jsonObject1.getString("applyer"));
        //审批模板名字
        String spName=jsonObject1.getString("sp_name");
        //模板id
        String templateId=jsonObject1.getString("template_id");
        //审批编号
        String approvalNum=jsonObject1.getString("sp_no");
        //提交时间
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //一般网上的转换是没有中间new Long（timeStamp）,因为他们都是精确到毫秒的时间戳，不用再乘以1000进行转换
        long longTimeStamp = new Long(new Long(jsonObject1.getString("apply_time")) * 1000);
        Date submitTime=simpleDateFormat.parse(simpleDateFormat.format(new Date(longTimeStamp)));

        //申请人id
        String userid=jsonObject2.getString("userid");
        //申请人部门id
        int partyId=Integer.parseInt(jsonObject2.getString("partyid"));
        //审批状态(1审批中，2已通过，3已驳回，4已撤销，6通过后撤销，7已删除，10已支付,)
        int spStatus=Integer.parseInt(jsonObject1.getString("sp_status"));
        //审批人+审批状态的拼接
        String approver = "";
        //审批人+审批状态json
        JSONArray jsonArray2 = jsonObject1.getJSONArray("sp_record");
        System.out.println("json_sp_record:"+jsonArray2);
        System.out.println("-----------");
        String[] sp_records = new String[jsonArray2.size()];
        for (int i=0;i<jsonArray2.size();i++){
            sp_records[i] = jsonArray2.getString(i);
            //审批人加个人审批状态json
            String approvers = JSONObject.parseObject(sp_records[i]).getString("details");
            String sp_record = approvers.substring(1,approvers.length()-1);
            System.out.println("sp_record:"+sp_record);
            JSONObject jsonObject15 = JSONObject.parseObject(JSONObject.parseObject(sp_record).getString("approver"));
            //审批人,如果只有一个审批人那么就是userid+审批状态
            if (jsonArray2.size()==1){
                approver += jsonObject15.getString("userid")+","+JSONObject.parseObject(sp_record).getString("sp_status");
                //审批人如果不止一个审批人那么就是userid+审批状态+%
            }if (jsonArray2.size()>1){
                if (i==0){
                    approver +=jsonObject15.getString("userid")+","+JSONObject.parseObject(sp_record).getString("sp_status");
                }else if (i>0){
                    approver += "%"+ jsonObject15.getString("userid")+","+JSONObject.parseObject(sp_record).getString("sp_status");
                }
            }
        }
        /**
         * 抄送人
         */
        //抄送人
        String copyPerson="";
        //抄送人json
        JSONArray jsonArray4 = jsonObject1.getJSONArray("notifyer");
        String[] notifyers = new String[jsonArray4.size()];
        for (int i=0;i<jsonArray4.size();i++){
            notifyers[i] = jsonArray4.getString(i);
            //抄送人,如果只有一个抄送人那么就是userid
            if (jsonArray4.size()==1){
                copyPerson += JSONObject.parseObject(notifyers[i]).getString("userid");
                //抄送人,如果不只一个抄送人那么就是userid+^^
            }if (jsonArray4.size()>1){
                if (i==0){
                    copyPerson += JSONObject.parseObject(notifyers[i]).getString("userid");
                }else if (i>0){
                    copyPerson += "%"+ JSONObject.parseObject(notifyers[i]).getString("userid");
                }
            }
        }

        /**
         * 备注
         */
        //备注
        String remarks="";
        JSONArray jsonArray3 = JSONArray.parseArray(jsonObject1.getString("comments"));
        if (!jsonArray3.isEmpty()){
            for (int i=0;i<jsonArray3.size();i++){
                JSONObject jsonObject15 = JSONObject.parseObject(jsonArray3.getString(i));
                //说话时间
                //System.out.println("说话时间:"+jsonObject15.getString("commenttime"));
                //备注内容
                //System.out.println("备注内容:"+jsonObject15.getString("commentcontent"));
                //备注人userid
                //JSONObject jsonObject16 = JSONObject.parseObject(jsonObject15.getString("commentUserInfo"));
                //.out.println("备注人userid:"+jsonObject16.getString("userid"));
                //备注内容id
                //System.out.println("备注内容id:"+jsonObject15.getString("commentid"));
                //备注图片
                JSONArray jsonArray5= JSONArray.parseArray(jsonObject15.getString("media_id"));
                for (int j=0;j<jsonArray5.size();j++){
                    if (jsonArray5.size()==1&&i==0){
                        remarks += "https://qyapi.weixin.qq.com/cgi-bin/media/get?access_token="+accessToken+"&media_id="+jsonArray5.getString(j);
                    }else if (remarks==null||remarks==""){
                        remarks += "https://qyapi.weixin.qq.com/cgi-bin/media/get?access_token="+accessToken+"&media_id="+jsonArray5.getString(j);
                    } else{
                        remarks += "%"+ "https://qyapi.weixin.qq.com/cgi-bin/media/get?access_token="+accessToken+"&media_id="+jsonArray5.getString(j);
                    }
                }
            }
        }
        //主要数据json
        JSONObject jsonObject3 = JSONObject.parseObject(jsonObject1.getString("apply_data"));
        JSONArray jsonArray5 = jsonObject3.getJSONArray("contents");
        Controltemplate controlTemplate = new Controltemplate();
        //初始化控件数量
        int Texta=0;
        int Textareaa=0;
        int Numbera=0;
        int Moneay=0;
        int Datea=0;
        int Datetimea=0;
        int Attendancea=0;
        int Singlea=0;
        int Multia=0;
        int Membersa=0;
        int Departmentsa=0;
        int Filea=0;
        int Vacationa=0;
        //初始化控件文本
        String Titletexta="";
        String Titletextareaa="";
        String Titlenumbera="";
        String Titlemoneaya="";
        String Titledatea="";
        String Titledatetimea="";
        String Titlesinglea="";
        String Titlemultia="";
        String Titlemembera="";
        String Titledepartmenta="";
        String Titlefilea="";
        //初始化文本内容
        String texta="";
        String textareaa="";
        String numbera="";
        String moneaya="";
        String datea="";
        String datatimea="";
        String singlea="";
        String multia="";
        String membera="";
        String departmenta="";
        String filea="";
        //获取自定义模板控件数量
        for (int i=0;i<jsonArray5.size();i++){
            JSONObject jsonObject4 = JSONObject.parseObject(jsonArray5.getString(i));
            //判断控件是哪类型的控件,然后调用文本说明的setter方法以及相对的setter方法
            if (jsonObject4.getString("control").equals("Text")){
                Texta++;
                Titletexta+=getTitle(jsonObject4,Texta);
                controlTemplate.setTitleText(Titletexta);
                texta+=getText(jsonObject4,Texta);
                controlTemplate.setTemplateText(texta);
            }
            if (jsonObject4.getString("control").equals("Textarea")){
                Textareaa++;
                Titletextareaa+=getTitle(jsonObject4,Textareaa);
                controlTemplate.setTitleTextarea(Titletextareaa);
                textareaa+=getTextarea(jsonObject4,Textareaa);
                controlTemplate.setTemplateTextarea(textareaa);
            }
            if (jsonObject4.getString("control").equals("Number")){
                Numbera++;
                Titlenumbera+=getTitle(jsonObject4,Numbera);
                controlTemplate.setTitleNumber(Titlenumbera);
                numbera+=getNumber(jsonObject4,Numbera);
                controlTemplate.setTemplateNumber(numbera);
            }
            if (jsonObject4.getString("control").equals("Money")){
                Moneay++;
                Titlemoneaya+=getTitle(jsonObject4,Moneay);
                controlTemplate.setTitleMoney(Titlemoneaya);
                moneaya+=getMoney(jsonObject4,Moneay);
                controlTemplate.setTemplateMoney(moneaya);
            }
            if (jsonObject4.getString("control").equals("Date")) {
                JSONObject jsonObject15 = JSONObject.parseObject(jsonObject4.getString("value"));
                JSONObject jsonObject16 = JSONObject.parseObject(jsonObject15.getString("date"));
                if (jsonObject16.getString("type").equals("day")) {
                    Datea++;
                    Titledatea+=getTitle(jsonObject4,Datea);
                    controlTemplate.setTitleDate(Titledatea);
                    datea+=getDate(jsonObject4,Datea);
                    controlTemplate.setTemplateDate(datea);
                }else if (jsonObject16.getString("type").equals("hour")) {
                    Datetimea++;
                    Titledatetimea+=getTitle(jsonObject4,Datetimea);
                    controlTemplate.setTitleDatetime(Titledatetimea);
                    datatimea+=getDatatime(jsonObject4,Datetimea);
                    controlTemplate.setTemplateDatetime(datatimea);
                }
            }
            if (jsonObject4.getString("control").equals("Attendance")) {
                Attendancea++;
                controlTemplate.setTitleNewBegin(getTitle(jsonObject4,Attendancea));
                controlTemplate.setTemplateNewBegin(getNewBegin(jsonObject4,Attendancea));
                controlTemplate.setTemplateNewEnd(getNewEnd(jsonObject4,Attendancea));
                controlTemplate.setTemplateNewDuration(getNewDuration(jsonObject4,Attendancea));
            }
            if (jsonObject4.getString("control").equals("Selector")) {
                JSONObject jsonObject19 = JSONObject.parseObject(jsonObject4.getString("value"));
                JSONObject jsonObject20 = JSONObject.parseObject(jsonObject19.getString("selector"));
                if (jsonObject20.getString("type").equals("single")) {
                    Singlea++;
                    Titlesinglea+=getTitle(jsonObject4,Singlea);
                    controlTemplate.setTitleSelectorSingle(Titlesinglea);
                    singlea+=getSelectorSingle(jsonObject4,Singlea);
                    controlTemplate.setTemplateSelectorSingle(singlea);
                }
                if (jsonObject20.getString("type").equals("multi")) {
                    Multia++;
                    Titlemultia+=getTitle(jsonObject4,Multia);
                    controlTemplate.setTitleSelectorMulti(Titlemultia);
                    multia+=getSelectorMulti(jsonObject4,Multia);
                    controlTemplate.setTemplateSelectorMulti(multia);
                }
            }
            if (jsonObject4.getString("control").equals("Contact")) {
                JSONObject jsonObject25 = JSONObject.parseObject(jsonObject4.getString("value"));
                JSONArray jsonArray26 = JSONArray.parseArray(jsonObject25.getString("members"));
                if (jsonArray26.size() >= 1) {
                    Membersa++;
                    Titlemembera+=getTitle(jsonObject4,Membersa);
                    controlTemplate.setTitleContactMember(Titlemembera);
                    membera+=getContactmember(jsonObject4,Membersa);
                    controlTemplate.setTemplateContactMember(membera);
                }
                JSONArray jsonArray27 = JSONArray.parseArray(jsonObject25.getString("departments"));
                if (jsonArray27.size() >= 1) {
                    Departmentsa++;
                    Titledepartmenta+=getTitle(jsonObject4,Departmentsa);
                    controlTemplate.setTitleContactDepartment(Titledepartmenta);
                    departmenta+=getContactDepartment(jsonObject4,Departmentsa);
                    controlTemplate.setTemplateContactDepartment(departmenta);
                }
            }
            if (jsonObject4.getString("control").equals("File")) {
                Filea++;
                Titlefilea+=getTitle(jsonObject4,Filea);
                controlTemplate.setTitleFile(Titlefilea);
                filea+=getFile(jsonObject4,accessToken,Filea);
                if (filea.equals(null)||filea.equals("")){
                    controlTemplate.setTemplateFile(null);
                }else {
                    controlTemplate.setTemplateFile(filea);
                }
            }
            if (jsonObject4.getString("control").equals("Vacation")){
                Vacationa++;
                String[] contro;
                contro = getVacation(jsonObject4,Vacationa);
                controlTemplate.setTitleSelectorSingle(getTitle(jsonObject4,Vacationa));
                controlTemplate.setTemplateSelectorSingle(contro[0]);
                controlTemplate.setTemplateNewBegin(changeDatetime(contro[1],1000));
                controlTemplate.setTemplateNewEnd(changeDatetime(contro[2],1000));
                controlTemplate.setTemplateNewDuration(changeHour(contro[3],3600));
            }
            if (jsonObject4.getString("control").equals("Table")){
                controlTemplate.setTitleTable(getTitle(jsonObject4,1));
                controlTemplate.setTemplateTable(getTable(jsonObject4,accessToken,approvalNum));
            }
        }
        controlTemplate.setSpName(spName);
        controlTemplate.setTemplateId(templateId);
        controlTemplate.setApprovalNum(approvalNum);
        controlTemplate.setSubmitTime(submitTime);
        controlTemplate.setUserid(userid);
        controlTemplate.setPartyId(partyId);
        controlTemplate.setSpStatus(spStatus);
        controlTemplate.setApprover(approver);
        controlTemplate.setCopyPerson(copyPerson);
        controlTemplate.setRemarks(remarks);

        //判断数据是否相同，相同则不作为，不相同则更新数据
        List<Controltemplate> list = controltemplateService.queryAll(null);
        boolean boo = true;
        for (int i=0;i<list.size();i++){
            //判断数据的附件是否为空
            if (!(list.get(i).getTemplateFile()==null|| list.get(i).getTemplateFile()=="")) {
                //判断审批编号是否空是否相同，如果相同那么从附件中将accessToken截取出来
                if (!(list.get(i).getApprovalNum()==null||list.get(i).getApprovalNum()=="")) {
                    int indexOf = list.get(i).getTemplateFile().toString().indexOf("=");
                    int indexof1 = list.get(i).getTemplateFile().toString().indexOf("&");
                    String file_access = list.get(i).getTemplateFile().toString().substring(indexOf+1, indexof1);
                    //判断是否与实时的accessToken相同，如果不相同则修改数据
                    if (!(file_access.equals(accessToken))) {
                        Controltemplate controlTemplate1 = new Controltemplate();
                        controlTemplate1.setApprovalNum(list.get(i).getApprovalNum());
                        controlTemplate1.setTemplateFile(list.get(i).getTemplateFile().replace(file_access,accessToken));
                        controltemplateService.update(controlTemplate1);
                    }
                }
            }
            //判断数据库的数据的备注是否为空
            if (!(list.get(i).getRemarks()==null||list.get(i).getRemarks().equals(""))){
                //判断审批编号是否为空，不为空则截取file_access
                if (!(list.get(i).getApprovalNum()==null||list.get(i).getApprovalNum()=="")) {
                    int indexOf = list.get(i).getRemarks().toString().indexOf("=");
                    int indexof1 = list.get(i).getRemarks().toString().indexOf("&");
                    String file_access = list.get(i).getRemarks().toString().substring(indexOf+1, indexof1);
                    //判断file_access是否等于accessToken,不相等则修改数据
                    if (!(file_access.equals(accessToken))) {
                        Controltemplate controlTemplate2 = new Controltemplate();
                        controlTemplate2.setApprovalNum(list.get(i).getApprovalNum());
                        controlTemplate2.setRemarks(list.get(i).getRemarks().replace(file_access,accessToken));
                        controltemplateService.update(controlTemplate2);
                    }
                }
            }
            //判断审批编号是否为空，是否相等
            if (!(list.get(i).getApprovalNum()==null||list.get(i).getApprovalNum()=="")){
                if (list.get(i).getApprovalNum().equals(approvalNum)){
                    boo = false;
                    //判断审批的状态以及审批人的状态是否相等，如果不相等则修改数据
                    if (!(list.get(i).getSpStatus().equals(spStatus)&&list.get(i).getApprover().equals(approver.toString()))){
                        Controltemplate controlTemplate1 = new Controltemplate();
                        controlTemplate1.setApprovalNum(list.get(i).getApprovalNum());
                        controlTemplate1.setSpStatus(list.get(i).getSpStatus());
                        controlTemplate1.setApprover(list.get(i).getApprover());
                        controltemplateService.update(controlTemplate1);
                    }
                    //判断数据备注此刻是否为空
                    if (!(remarks.isEmpty())){
                        //判断数据库的数据备注是否为空，如果为空则修改数据
                        if (list.get(i).getRemarks()==null||list.get(i).getRemarks().equals("")){
                            Controltemplate controlTemplate1 = new Controltemplate();
                            controlTemplate1.setApprovalNum(list.get(i).getApprovalNum());
                            controlTemplate1.setRemarks(remarks);
                            controltemplateService.update(controlTemplate1);
                            //判断数据库的备注是否和此刻的备注相同，不相同则修改数据
                        }else if(!(list.get(i).getRemarks().equals(remarks.toString()))){
                            Controltemplate controlTemplate1 = new Controltemplate();
                            controlTemplate1.setApprovalNum(list.get(i).getApprovalNum());
                            controlTemplate1.setRemarks(remarks);
                            controltemplateService.update(controlTemplate1);
                        }
                    }
                    //如果数据的备注为空，而且数据库的数据的备注不为空，则修改数据为""
                    if (remarks.isEmpty()&&(list.get(i).getRemarks()!=null||list.get(i).getRemarks()!="")){
                        Controltemplate controlTemplate1 = new Controltemplate();
                        controlTemplate1.setApprovalNum(list.get(i).getApprovalNum());
                        controlTemplate1.setRemarks("");
                        controltemplateService.update(controlTemplate1);
                    }
                }
            }
        }
        if (boo==true){
            controltemplateService.insert(controlTemplate);
        }
    }

    /**
     * 将传入的字符串转换为datetime
     * @param dateString
     * @param Multiple
     * @return
     */
    public String changeDatetime(String dateString,int Multiple){
        longTimeStamp = new Long(new Long(dateString) * Multiple);
        date = new Date(longTimeStamp);
        dareString = simpleDateFormat.format(date);
        return dareString;
    }

    /**
     * 将传入的字符串转换为小时
     * @param dateString
     * @param Multiple
     * @return
     */
    public String changeHour(String dateString,int Multiple){
        return String.valueOf((Float.parseFloat(dateString))/Multiple);

    }


    /**
     * 通用文本模板title
     * @param jsonObject
     * @param Titlea
     * @return
     */
    //获取自定义模板中控件的名称，如果一条数据同一类型的控件存在多个，那么则在第二个名称前加+^^
    public String getTitle(JSONObject jsonObject,int Titlea){
        String title="";
        JSONArray jsonArray = JSONArray.parseArray(jsonObject.getString("title"));
        for (int i=0;i<jsonArray.size();i++){
            JSONObject jsonObject1 = JSONObject.parseObject(jsonArray.getString(i));
            if (Titlea==1){
                title+= jsonObject1.getString("text");
            }else if (Titlea>1){
                title+= "%"+ jsonObject1.getString("text");
            }
        }
        return title;
    }

    /**
     * 通用模板文本
     */
    public String getText(JSONObject jsonObject,int Texta) {
        String text = "";
        //通用模板文本
        if (jsonObject.getString("control").equals("Text")) {
            JSONObject jsonObject12 = JSONObject.parseObject(jsonObject.getString("value"));
            if (!(jsonObject12.getString("text").toString().equals(null)||jsonObject12.getString("text").toString().equals(""))){
                if (Texta==1){
                    text += jsonObject12.getString("text");
                }else if (Texta>1){
                    text += "%"+jsonObject12.getString("text");
                }
            }

        }
        return text;
    }

    /**
     * 通用模板多行文本
     */
    public String getTextarea(JSONObject jsonObject,int Textareaa) {
        String textarea = "";
        //通用模板多行文本
        if (jsonObject.getString("control").equals("Textarea")) {
            JSONObject jsonObject13 = JSONObject.parseObject(jsonObject.getString("value"));
            if (!(jsonObject13.getString("text").toString().equals(null)||jsonObject13.getString("text").toString().equals(""))){
                if (Textareaa==1){
                    textarea += jsonObject13.getString("text");
                }else if (Textareaa>1){
                    textarea += "%"+ jsonObject13.getString("text");
                }
            }
        }
        return textarea;
    }

    /**
     * 通用模板数字
     */
    public String getNumber(JSONObject jsonObject,int Numbera) {
        String number = "";
        //通用模板数字
        if (jsonObject.getString("control").equals("Number")) {
            JSONObject jsonObject14 = JSONObject.parseObject(jsonObject.getString("value"));
            if (!(jsonObject14.getString("new_number").toString().equals(null)||jsonObject14.getString("new_number").toString().equals(""))){
                if (Numbera==1){
                    number += jsonObject14.getString("new_number");
                }else if (Numbera>1){
                    number += "%"+ jsonObject14.getString("new_number");
                }
            }
        }
        return number;
    }

    /**
     * 通用模板金额
     */
    public String getMoney(JSONObject jsonObject,int Moneya) {
        String money = "";
        //通用模板金额
        if (jsonObject.getString("control").equals("Money")) {
            JSONObject jsonObject14 = JSONObject.parseObject(jsonObject.getString("value"));
            if (!(jsonObject14.getString("new_money").toString().equals(null)||jsonObject14.getString("new_money").toString().equals(""))){
                if (Moneya==1){
                    money += jsonObject14.getString("new_money");
                }else if (Moneya>1){
                    money += "%"+ jsonObject14.getString("new_money");
                }
            }
        }
        return money;
    }

    /**
     * 通用模板日期
     */
    public String getDate(JSONObject jsonObject,int Datea) {
        String templateDate = "";
        /** 通用模板日期 */
        if (jsonObject.getString("control").equals("Date")) {
            JSONObject jsonObject15 = JSONObject.parseObject(jsonObject.getString("value"));
            JSONObject jsonObject16 = JSONObject.parseObject(jsonObject15.getString("date"));
            if (jsonObject16.getString("type").equals("day")) {
                /** 通用模板日期 */
                if (!(jsonObject16.getString("s_timestamp").toString().equals(null)||jsonObject16.getString("s_timestamp").toString().equals(""))){
                    if (Datea==1){
                        //将时间戳转换为String类型，并且进行拼接
                        longTimeStamp = new Long(new Long(jsonObject16.getString("s_timestamp")) * 1000);
                        date = new Date(longTimeStamp);
                        dareString = simpleDateFormat.format(date);
                        templateDate += dareString;
                    }else if (Datea>1){
                        //将时间戳转换为String类型，并且进行拼接
                        longTimeStamp = new Long(new Long(jsonObject16.getString("s_timestamp")) * 1000);
                        date = new Date(longTimeStamp);
                        dareString = simpleDateFormat.format(date);
                        templateDate += "%" + dareString;
                    }
                }
            }
        }
        return templateDate;
    }

    /**
     * 通用模板日期+时间
     */
    public String getDatatime(JSONObject jsonObject,int Datatimea) {
        String templateDatatime = "";
        /** 通用模板日期+时间 */
        if (jsonObject.getString("control").equals("Date")) {
            JSONObject jsonObject15 = JSONObject.parseObject(jsonObject.getString("value"));
            JSONObject jsonObject16 = JSONObject.parseObject(jsonObject15.getString("date"));
            if (jsonObject16.getString("type").equals("hour")) {
                /** 通用模板日期+时间 */
                if (!(jsonObject16.getString("s_timestamp").toString().equals(null)||jsonObject16.getString("s_timestamp").toString().equals(""))){
                    if (Datatimea==1){
                        //将时间戳转换为String类型，并且进行拼接
                        longTimeStamp = new Long(new Long(jsonObject16.getString("s_timestamp")) * 1000);
                        date = new Date(longTimeStamp);
                        dareString = simpleDateFormat.format(date);
                        templateDatatime += dareString;
                    }else if (Datatimea>1){
                        longTimeStamp = new Long(new Long(jsonObject16.getString("s_timestamp")) * 1000);
                        date = new Date(longTimeStamp);
                        dareString = simpleDateFormat.format(date);
                        templateDatatime += "%"+ dareString;
                    }
                }
            }
        }
        return templateDatatime;
    }

    /**
     * 通用模板开始时间
     */
    public String getNewBegin(JSONObject jsonObject,int Attendancea) {
        String templateNewBegin = "";
        //通用模板开始时间
        if (jsonObject.getString("control").equals("Attendance")) {
            JSONObject jsonObject30 = JSONObject.parseObject(jsonObject.getString("value"));
            JSONObject jsonObject31 = JSONObject.parseObject(jsonObject30.getString("attendance"));
            JSONObject jsonObject32 = JSONObject.parseObject(jsonObject31.getString("date_range"));
            //通用模板开始时间
            if (!(jsonObject32.getString("new_begin").toString().equals(null)||jsonObject32.getString("new_begin").toString().equals(""))){
                if (Attendancea==1){
                    longTimeStamp = new Long(new Long(jsonObject32.getString("new_begin")) * 1000);
                    date = new Date(longTimeStamp);
                    dareString = simpleDateFormat.format(date);
                    templateNewBegin += dareString;
                }else if (Attendancea>1){
                    longTimeStamp = new Long(new Long(jsonObject32.getString("new_begin")) * 1000);
                    date = new Date(longTimeStamp);
                    dareString = simpleDateFormat.format(date);
                    templateNewBegin += "+%"+ dareString;
                }
            }
        }
        return templateNewBegin;
    }

    /**
     * 通用模板结束时间
     */
    public String getNewEnd(JSONObject jsonObject,int Attendancea) {
        String templateNewEnd = "";
        //通用模板开始时间
        if (jsonObject.getString("control").equals("Attendance")) {
            JSONObject jsonObject30 = JSONObject.parseObject(jsonObject.getString("value"));
            JSONObject jsonObject31 = JSONObject.parseObject(jsonObject30.getString("attendance"));
            JSONObject jsonObject32 = JSONObject.parseObject(jsonObject31.getString("date_range"));
            //通用模板结束时间
            if (!(jsonObject32.getString("new_end").toString().equals(null)||jsonObject32.getString("new_end").toString().equals(""))){
                if (Attendancea==1){
                    longTimeStamp = new Long(new Long(jsonObject32.getString("new_end")) * 1000);
                    date = new Date(longTimeStamp);
                    dareString = simpleDateFormat.format(date);
                    templateNewEnd += dareString;
                }else if (Attendancea>1){
                    longTimeStamp = new Long(new Long(jsonObject32.getString("new_end")) * 1000);
                    date = new Date(longTimeStamp);
                    dareString = simpleDateFormat.format(date);
                    templateNewEnd += "%"+ dareString;
                }
            }
        }
        return templateNewEnd;
    }

    /**
     * 通用模板时长
     */
    public String getNewDuration(JSONObject jsonObject,int Attendancea) {
        String templateNewDuration = "";
        //通用模板开始时间
        if (jsonObject.getString("control").equals("Attendance")) {
            JSONObject jsonObject30 = JSONObject.parseObject(jsonObject.getString("value"));
            JSONObject jsonObject31 = JSONObject.parseObject(jsonObject30.getString("attendance"));
            JSONObject jsonObject32 = JSONObject.parseObject(jsonObject31.getString("date_range"));
            //通用模板时长
            if (!(jsonObject32.getString("new_duration").toString().equals(null)||jsonObject32.getString("new_duration").toString().equals(""))){
                if (Attendancea==1){
                    templateNewDuration += String.valueOf((Float.parseFloat(jsonObject32.getString("new_duration")))/3600);
                }else if (Attendancea>1){
                    templateNewDuration += "%"+ String.valueOf((Float.parseFloat(jsonObject32.getString("new_duration")))/3600);
                }
            }
        }
        return templateNewDuration;
    }

    /**
     * 通用模板单选
     */
    public String getSelectorSingle(JSONObject jsonObject,int singlea){
        String templateSelectorSingle = "";
        /** 通用模板单选 */
        if (jsonObject.getString("control").equals("Selector")) {
            JSONObject jsonObject19 = JSONObject.parseObject(jsonObject.getString("value"));
            JSONObject jsonObject20 = JSONObject.parseObject(jsonObject19.getString("selector"));
            if (jsonObject20.getString("type").equals("single")) {
                JSONArray jsonArray21 = JSONArray.parseArray(jsonObject20.getString("options"));
                for (int k = 0; k < jsonArray21.size(); k++) {
                    JSONObject jsonObject22 = JSONObject.parseObject(jsonArray21.getString(k));
                    JSONArray jsonArray23 = JSONArray.parseArray(jsonObject22.getString("value"));
                    for (int j = 0; j < jsonArray23.size(); j++) {
                        JSONObject jsonObject24 = JSONObject.parseObject(jsonArray23.getString(j));
                        /** 通用模板单选 */
                        if (!(jsonObject24.getString("text").toString().equals(null)||jsonObject24.getString("text").toString().equals(""))){
                            if (singlea==1){
                                templateSelectorSingle += jsonObject24.getString("text");
                            }else if (singlea>1){
                                templateSelectorSingle += "%"+ jsonObject24.getString("text");
                            }
                        }
                    }
                }
            }
        }
        return templateSelectorSingle;
    }

    /**
     * 通用模板多选
     */
    public String getSelectorMulti(JSONObject jsonObject,int Multia) {
        String templateSelectorMulti = "";
        /** 通用模板多选 */
        if (jsonObject.getString("control").equals("Selector")) {
            JSONObject jsonObject19 = JSONObject.parseObject(jsonObject.getString("value"));
            JSONObject jsonObject20 = JSONObject.parseObject(jsonObject19.getString("selector"));
            if (jsonObject20.getString("type").equals("multi")) {
                JSONArray jsonArray21 = JSONArray.parseArray(jsonObject20.getString("options"));
                for (int k = 0; k < jsonArray21.size(); k++) {
                    JSONObject jsonObject22 = JSONObject.parseObject(jsonArray21.getString(k));
                    JSONArray jsonArray23 = JSONArray.parseArray(jsonObject22.getString("value"));
                    for (int j = 0; j < jsonArray23.size(); j++) {
                        JSONObject jsonObject24 = JSONObject.parseObject(jsonArray23.getString(j));
                        /** 通用模板多选 */
                        if (!(jsonObject24.getString("text").toString().equals(null)||jsonObject24.getString("text").toString().equals(""))){
                            if (Multia==1){
                                templateSelectorMulti += jsonObject24.getString("text");
                            }else if (Multia>1){
                                templateSelectorMulti += "%"+ jsonObject24.getString("text");
                            }
                        }
                    }
                }
            }
        }
        return templateSelectorMulti;
    }

    /**
     * 通用模板成员
     */
    public String getContactmember(JSONObject jsonObject,int membera) {
        String templateContactmember = "";
        /** 通用模板成员 */
        if (jsonObject.getString("control").equals("Contact")) {
            JSONObject jsonObject25 = JSONObject.parseObject(jsonObject.getString("value"));
            JSONArray jsonArray26 = JSONArray.parseArray(jsonObject25.getString("members"));
            if (jsonArray26.size() >= 1) {
                for (int k = 0; k < jsonArray26.size(); k++) {
                    /** 通用模板成员 */
                    if (!(JSONObject.parseObject(jsonArray26.getString(k)).getString("userid").equals(null)||JSONObject.parseObject(jsonArray26.getString(k)).getString("name").equals(""))){
                        if (membera==1){
                            templateContactmember += JSONObject.parseObject(jsonArray26.getString(k)).getString("userid") + "," + JSONObject.parseObject(jsonArray26.getString(k)).getString("name");
                        }else if (membera>1){
                            templateContactmember += "%" + JSONObject.parseObject(jsonArray26.getString(k)).getString("userid") + "," + JSONObject.parseObject(jsonArray26.getString(k)).getString("name");
                        }
                    }
                }
            }
        }
        return templateContactmember;
    }

    /**
     * 通用模板部门
     */
    public String getContactDepartment(JSONObject jsonObject,int Departmenta) {
        String templateContactDepartment = "";
        /** 通用模板部门 */
        if (jsonObject.getString("control").equals("Contact")) {
            JSONObject jsonObject25 = JSONObject.parseObject(jsonObject.getString("value"));
            JSONArray jsonArray27 = JSONArray.parseArray(jsonObject25.getString("departments"));
            if (jsonArray27.size() >= 1) {
                for (int k = 0; k < jsonArray27.size(); k++) {
                    /** 通用模部门 */
                    if(!(JSONObject.parseObject(jsonArray27.getString(k)).getString("openapi_id").equals(null)||JSONObject.parseObject(jsonArray27.getString(k)).getString("name").equals(""))){
                        if (Departmenta==1){
                            templateContactDepartment += JSONObject.parseObject(jsonArray27.getString(k)).getString("openapi_id") + "," + JSONObject.parseObject(jsonArray27.getString(k)).getString("name");
                        }else if (Departmenta>1){
                            templateContactDepartment +=  "%" + JSONObject.parseObject(jsonArray27.getString(k)).getString("openapi_id") + "," + JSONObject.parseObject(jsonArray27.getString(k)).getString("name");
                        }
                    }
                }
            }
        }
        return templateContactDepartment;
    }

    /**
     * 通用模板附件
     */
    public String getFile(JSONObject jsonObject, String accessToken,int Filea) {
        String templateFile = "";
        /** 通用模板附件 */
        if (jsonObject.getString("control").equals("File")) {
            JSONObject jsonObject28 = JSONObject.parseObject(jsonObject.getString("value"));
            JSONArray jsonArray29 = jsonObject28.getJSONArray("files");
            String[] templateFiles = new String[jsonArray29.size()];
            for (int k = 0; k < jsonArray29.size(); k++) {
                templateFiles[k] = jsonArray29.getString(k);
                /** 通用模板附件 */
                if (!(JSONObject.parseObject(templateFiles[k]).getString("file_id").equals(null)||JSONObject.parseObject(templateFiles[k]).getString("file_id").equals(""))){
                    if (Filea==1&&k==0){
                        templateFile += "https://qyapi.weixin.qq.com/cgi-bin/media/get?access_token=" + accessToken + "&media_id=" + JSONObject.parseObject(templateFiles[k]).getString("file_id");
                    }else if (Filea>1||k>=1){
                        templateFile +=  "%" + "https://qyapi.weixin.qq.com/cgi-bin/media/get?access_token=" + accessToken + "&media_id=" + JSONObject.parseObject(templateFiles[k]).getString("file_id");
                    }
                }
            }
        }
        return templateFile;
    }

    /**
     *请假
     */
    public String[] getVacation(JSONObject jsonObject,int Vacationa){
        Controltemplate controlTemplate =  new Controltemplate();
        String[] contro = new String[4];
        //判断是否是请假类型及时长
        if (jsonObject.getString("control").equals("Vacation")){
            JSONObject jsonObject5 = JSONObject.parseObject(jsonObject.getString("value"));
            JSONObject jsonObject6 = JSONObject.parseObject(jsonObject5.getString("vacation"));
            JSONObject jsonObject7 = JSONObject.parseObject(jsonObject6.getString("selector"));
            JSONObject jsonObject8 = JSONObject.parseObject(jsonObject7.getJSONArray("options").getString(0));
            JSONObject jsonObject9 = JSONObject.parseObject(jsonObject6.getString("attendance"));
            JSONObject jsonObject10 = JSONObject.parseObject(jsonObject9.getString("date_range"));
            JSONObject jsonObject11 = JSONObject.parseObject(jsonObject8.getJSONArray("value").getString(0));

            contro[0] = jsonObject11.getString("text");
            contro[1] = jsonObject10.getString("new_begin");
            contro[2] = jsonObject10.getString("new_end");
            contro[3] = jsonObject10.getString("new_duration");
        }
        return contro;
    }

    /** 通用模板明细 */
    public String getTable(JSONObject jsonObject,String accessToken,String approvalNum) {
        Controltemplate controlTemplate = new Controltemplate();
        if (jsonObject.getString("control").equals("Table")){
            JSONObject jsonObject33 = JSONObject.parseObject(jsonObject.getString("value"));
            JSONArray jsonArray34 = JSONArray.parseArray(jsonObject33.getString("children"));
            //初始化明细table中控件的数量
            int Texta=0;
            int Textareaa=0;
            int Numbera=0;
            int Moneay=0;
            int Datea=0;
            int Datatimea=0;
            int Attendancea=0;
            int Singlea=0;
            int Multia=0;
            int Membera=0;
            int Departmenta=0;
            int Filea=0;
            //初始化明细table中控件名称
            String Titletexta="";
            String Titletextareaa="";
            String Titlenumbera="";
            String Titlemoneaya="";
            String Titledatea="";
            String Titledatetimea="";
            String Titlesinglea="";
            String Titlemultia="";
            String Titlemembera="";
            String Titledepartmenta="";
            String Titlefilea="";
            //初始化明细table中控件内容
            String texta="";
            String textareaa="";
            String numbera="";
            String moneaya="";
            String datea="";
            String datatimea="";
            String singlea="";
            String multia="";
            String membera="";
            String departmenta="";
            String filea="";
            //获取明细table中的详情json
            for (int k=0;k<jsonArray34.size();k++){
                JSONObject jsonObject35 =  JSONObject.parseObject(jsonArray34.getString(k));
                JSONArray jsonArray36 = JSONArray.parseArray(jsonObject35.getString("list"));
                for (int l=0;l<jsonArray36.size();l++){
                    JSONObject jsonObject37 = JSONObject.parseObject(jsonArray36.getString(l));
                    //判断明细table中的控件类型，然后获取其控件名称以及控件内容
                    if (jsonObject37.getString("control").equals("Text")){
                        Texta++;
                        Titletexta+=getTitle(jsonObject37,Texta);
                        controlTemplate.setTitleText(Titletexta);
                        texta +=getText(jsonObject37,Texta);
                        controlTemplate.setTemplateText(texta);
                    }
                    if (jsonObject37.getString("control").equals("Textarea")){
                        Textareaa++;
                        Titletextareaa+=getTitle(jsonObject37,Textareaa);
                        controlTemplate.setTitleTextarea(Titletextareaa);
                        textareaa += getTextarea(jsonObject37,Textareaa);
                        controlTemplate.setTemplateTextarea(textareaa);
                    }
                    if (jsonObject37.getString("control").equals("Number")){
                        Numbera++;
                        Titlenumbera+=getTitle(jsonObject37,Numbera);
                        controlTemplate.setTitleNumber(Titlenumbera);
                        numbera += getNumber(jsonObject37,Numbera);
                        controlTemplate.setTemplateNumber(numbera);
                    }
                    if (jsonObject37.getString("control").equals("Money")){
                        Moneay++;
                        Titlemoneaya+=getTitle(jsonObject37,Numbera);
                        controlTemplate.setTitleMoney(Titlemoneaya);
                        moneaya += getMoney(jsonObject37,Moneay);
                        controlTemplate.setTemplateMoney(moneaya);
                    }
                    if (jsonObject.getString("control").equals("Date")) {
                        JSONObject jsonObject15 = JSONObject.parseObject(jsonObject.getString("value"));
                        JSONObject jsonObject16 = JSONObject.parseObject(jsonObject15.getString("date"));
                        if (jsonObject16.getString("type").equals("day")) {
                            Datea++;
                            Titledatea+=getTitle(jsonObject37,Numbera);
                            controlTemplate.setTitleDate(Titledatea);
                            datea += getDate(jsonObject37,Datea);
                            controlTemplate.setTemplateDate(datea);
                        }
                    }
                    if (jsonObject.getString("control").equals("Date")) {
                        JSONObject jsonObject15 = JSONObject.parseObject(jsonObject.getString("value"));
                        JSONObject jsonObject16 = JSONObject.parseObject(jsonObject15.getString("date"));
                        if (jsonObject16.getString("type").equals("hour")) {
                            Datatimea++;
                            Titledatetimea+=getTitle(jsonObject37,Numbera);
                            controlTemplate.setTitleDatetime(Titledatetimea);
                            datatimea += getDate(jsonObject37,Datatimea);
                            controlTemplate.setTemplateDatetime(datatimea);
                        }
                    }
                    if (jsonObject.getString("control").equals("Attendance")) {
                        JSONObject jsonObject30 = JSONObject.parseObject(jsonObject.getString("value"));
                        JSONObject jsonObject31 = JSONObject.parseObject(jsonObject30.getString("attendance"));
                        JSONObject jsonObject32 = JSONObject.parseObject(jsonObject31.getString("date_range"));
                        //通用模板开始时间
                        if (Integer.parseInt(jsonObject32.getString("new_begin").toString())>=0){
                            controlTemplate.setTitleNewBegin(getTitle(jsonObject37,1));
                            controlTemplate.setTemplateNewBegin(getNewBegin(jsonObject37,Attendancea));
                        }
                    }
                    if (jsonObject.getString("control").equals("Attendance")) {
                        JSONObject jsonObject30 = JSONObject.parseObject(jsonObject.getString("value"));
                        JSONObject jsonObject31 = JSONObject.parseObject(jsonObject30.getString("attendance"));
                        JSONObject jsonObject32 = JSONObject.parseObject(jsonObject31.getString("date_range"));
                        //通用模板结束时间
                        if (Integer.parseInt(jsonObject32.getString("new_end").toString())>=0){
                            controlTemplate.setTemplateNewEnd(getNewEnd(jsonObject37,Attendancea));
                        }
                    }
                    if (jsonObject.getString("control").equals("Attendance")) {
                        JSONObject jsonObject30 = JSONObject.parseObject(jsonObject.getString("value"));
                        JSONObject jsonObject31 = JSONObject.parseObject(jsonObject30.getString("attendance"));
                        JSONObject jsonObject32 = JSONObject.parseObject(jsonObject31.getString("date_range"));
                        //通用模板时长
                        if (Integer.parseInt(jsonObject32.getString("new_duration").toString())>=0){
                            controlTemplate.setTemplateNewDuration(getNewDuration(jsonObject37,Attendancea));
                        }
                    }
                    /** 通用模板单选 */
                    if (jsonObject.getString("control").equals("Selector")) {
                        JSONObject jsonObject19 = JSONObject.parseObject(jsonObject.getString("value"));
                        JSONObject jsonObject20 = JSONObject.parseObject(jsonObject19.getString("selector"));
                        if (jsonObject20.getString("type").equals("single")) {
                            Singlea++;
                            Titlesinglea+=getTitle(jsonObject37,Singlea);
                            controlTemplate.setTitleSelectorSingle(Titlesinglea);
                            singlea+= getSelectorSingle(jsonObject37,Singlea);
                            controlTemplate.setTemplateSelectorSingle(singlea);
                        }
                    }
                    /** 通用模板多选 */
                    if (jsonObject.getString("control").equals("Selector")) {
                        JSONObject jsonObject19 = JSONObject.parseObject(jsonObject.getString("value"));
                        JSONObject jsonObject20 = JSONObject.parseObject(jsonObject19.getString("selector"));
                        if (jsonObject20.getString("type").equals("multi")) {
                            Multia++;
                            Titlemultia+=getTitle(jsonObject37,Singlea);
                            controlTemplate.setTitleSelectorMulti(Titlemultia);
                            multia+=getSelectorMulti(jsonObject37,Multia);
                            controlTemplate.setTemplateSelectorMulti(multia);
                        }
                    }
                    /** 通用模板成员 */
                    if (jsonObject.getString("control").equals("Contact")) {
                        JSONObject jsonObject25 = JSONObject.parseObject(jsonObject.getString("value"));
                        JSONArray jsonArray26 = JSONArray.parseArray(jsonObject25.getString("members"));
                        if (jsonArray26.size()>=1){
                            Membera++;
                            Titlemembera+=getTitle(jsonObject37,Singlea);
                            controlTemplate.setTitleContactMember(Titlemembera);
                            membera+=getContactmember(jsonObject37,Membera);
                            controlTemplate.setTemplateContactMember(membera);
                        }
                    }
                    /** 通用模板部门 */
                    if (jsonObject.getString("control").equals("Contact")) {
                        JSONObject jsonObject25 = JSONObject.parseObject(jsonObject.getString("value"));
                        JSONArray jsonArray27 = JSONArray.parseArray(jsonObject25.getString("departments"));
                        if (jsonArray27.size() >= 1){
                            Departmenta++;
                            Titledepartmenta+=getTitle(jsonObject37,Singlea);
                            controlTemplate.setTitleContactDepartment(Titledepartmenta);
                            departmenta+=getContactDepartment(jsonObject37,Departmenta);
                            controlTemplate.setTemplateContactDepartment(departmenta);
                        }
                    }
                    /**通用模板附件*/
                    if (jsonObject.getString("control").equals("File")) {
                        Filea++;
                        Titlefilea+=getTitle(jsonObject37, Filea);
                        controlTemplate.setTitleFile(Titlefilea);
                        filea+=getFile(jsonObject37,accessToken,Filea);
                        if (filea.equals(null)||filea.equals("")){
                            controlTemplate.setTemplateFile(null);
                        }else {
                            controlTemplate.setTemplateFile(filea);
                        }

                    }
                }
            }
            controlTemplate.setTemplateTable(approvalNum);
            //查询数据库存不存在此条数据
            List<Controltemplate> list = controltemplateService.queryAll(controlTemplate);
            //如果不存在则添加此数据
            if (list.size()<1){
                controltemplateService.insert(controlTemplate);
            }
        }
        return approvalNum;
    }
}
