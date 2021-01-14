package com.gag.enterprisewxmobile.system.zcheck.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Zcheck extends BaseRowModel implements Serializable {

    private static final long serialVersionUID = -23983322005441711L;
    @ExcelProperty(value = "ecode",index = 0)
    private String ecode;
    @ExcelProperty(value = "event_ID",index = 1)
    private String event_ID;
    @ExcelProperty(value = "returnMsg",index = 2)
    private String returnMsg;
    @ExcelProperty(value = "poSo",index = 3)
    private String poSo;
}
