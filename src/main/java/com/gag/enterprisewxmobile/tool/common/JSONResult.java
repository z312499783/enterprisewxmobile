package com.gag.enterprisewxmobile.tool.common;

import com.gag.enterprisewxmobile.system.user.entity.QywxSysUser;
import com.github.pagehelper.PageInfo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class JSONResult<T> extends ResponseEntity<Message> {


    public QywxSysUser getSysUser()
    {
        return ShiroUtils.getSysUser();
    }

    public JSONResult(HttpStatus code) {
        super(code);
    }

    public JSONResult(int code, String msg) {
        super(Message.custom(code, msg), Message.num2HttpStatus(code));
    }

    public JSONResult(int code, String msg, T data) {
        super(Message.custom(code, msg, data), Message.num2HttpStatus(code));
    }

    public JSONResult(int code, String msg, List<T> data) {
        super(Message.custom(code, msg, data,new PageInfo(data).getTotal()), Message.num2HttpStatus(code));
    }


    public static <T> JSONResult<T> custom(T data){
        if (data == null){
            return new JSONResult(200, "数据为空", data);
        }
        return new JSONResult(200, "成功", data);
    }

    public static <T> JSONResult<T> custom(int i, T data){
        if (i>0){
            return new JSONResult(200, "成功", data);
        }
        return new JSONResult(500, "失败", data);
    }

    public static <T> JSONResult<T> customint(int i){
        if (i>0){
            return new JSONResult(200, "成功");
        }
        return new JSONResult(500, "失败");
    }

    public static <T> JSONResult<T> custom(boolean bo, T data){
        if (bo){
            return new JSONResult(200, "成功", data);
        }
        return new JSONResult(500, "失败", data);
    }

    public static <T> JSONResult<T> customboo(boolean bo){
        if (bo){
            return new JSONResult(200, "成功");
        }
        return new JSONResult(500, "失败");
    }

    public static <T> JSONResult<T> custom(boolean bo,String msg, T data){
        if (bo){
            return new JSONResult(200, "成功", data);
        }
        return new JSONResult(500, msg, data);
    }

    public static <T> JSONResult<T> success(T data) {
        return new JSONResult(200, "成功", data);
    }

    public static <T> JSONResult<T> failed(T data) {
        return new JSONResult(500, "失败", data);
    }

    public static <T> JSONResult<T> failMsg(String msg) {
        return new JSONResult(500, msg);
    }

    public static <T> JSONResult<T> custom(int code, String msg, T data) {
        return new JSONResult(code, msg, data);
    }

    public static <T> JSONResult<T> tablePage(int code, String msg, List<T> data){
        return new JSONResult(code,msg,data);
    }

    public static <T> JSONResult<T> tablePageLayui(List<T> data){
        return new JSONResult(0,"",data);
    }

    public static <T> JSONResult<T> tablePage(List<T> data){
        return new JSONResult(200,"成功",data);
    }



}

@Getter
@Setter
class Message<T>{
    //状态码
    int code;
    //向前端返回的内容
    String msg;
    //返回的数据
    T data;
    //返回数据的条数
    long count;

    public Message() {
    }

    public Message(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Message(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Message(int code, String msg, T data, long count) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.count = count;
    }

    public static <T> Message<T> custom(int code,String msg , T data,long count) {
        return new Message(code, msg, data,count);
    }

    public static <T> Message<T> custom(int code,String msg , T data) {
        return new Message(code, msg, data);
    }

    public static <T> Message<T> custom(int code, String msg) {
        return new Message(code, msg);
    }

    public static HttpStatus num2HttpStatus(int code) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        for (HttpStatus httpStatus : HttpStatus.values()) {
            boolean b = code == httpStatus.value();
            if (b) {
                return httpStatus;
            }
        }
        return status;
    }

}
