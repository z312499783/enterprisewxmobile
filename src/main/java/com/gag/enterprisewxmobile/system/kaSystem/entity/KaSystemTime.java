package com.gag.enterprisewxmobile.system.kaSystem.entity;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.servlet.http.HttpServletRequest;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class KaSystemTime {


    private String StartTime;

    private String EndTime;


    public KaSystemTime getSystemTime(HttpServletRequest request){
        String StartTime = request.getParameter("StartTime");
        String EndTime = request.getParameter("EndTime");
        return new KaSystemTime(StartTime,EndTime);
    }

 }
