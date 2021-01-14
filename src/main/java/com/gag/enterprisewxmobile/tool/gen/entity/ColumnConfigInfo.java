package com.gag.enterprisewxmobile.tool.gen.entity;

import lombok.Data;

@Data
public class ColumnConfigInfo {
    /**
     * 属性标题
     */
    private String title;

    /**
     * 属性类型 dict(字典，value对应字典管理的字典类型), date(包括date)
     */
    private String type;

    /**
     * 属性值，参考数据类型，可为空
     */
    private String value;

    public ColumnConfigInfo()
    {
        super();
    }

    public ColumnConfigInfo(String title, String type, String value)
    {
        super();
        this.title = title;
        this.type = type;
        this.value = value;
    }

}
