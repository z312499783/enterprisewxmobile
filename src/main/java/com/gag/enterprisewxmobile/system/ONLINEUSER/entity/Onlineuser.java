package com.gag.enterprisewxmobile.system.ONLINEUSER.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.io.Serializable;

/**
 * (Onlineuser)实体类
 *
 * @author makejava
 * @since 2020-09-25 13:53:56
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Onlineuser implements Serializable {
    private static final long serialVersionUID = -23983322005331711L;
    
    private String name;
    
    private String machinealias;
    
    private Integer connecttype;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date checktime;
    
    private String checktype;
    
    private Integer machinenumber;
    
    private String ip;
    
    private Integer userid;
    
    private String addtime;

    private String unit;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date sendtime;

    private int sendcount;

    private String sn;

}