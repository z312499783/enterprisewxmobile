package com.gag.enterprisewxmobile.tool.common.utils;

import java.util.HashMap;
import java.util.Map;

public class CommonMap {
    /** 状态编码转换 */
    public static Map<String, String> javaTypeMap = new HashMap<String, String>();


    static
    {
        initJavaTypeMap();
    }

    /**
     * 返回状态映射
     */
    public static void initJavaTypeMap()
    {
        javaTypeMap.put("tinyint", "java.lang.Integer");
        javaTypeMap.put("smallint", "java.lang.Integer");
        javaTypeMap.put("mediumint", "java.lang.Integer");
        javaTypeMap.put("int","java.lang.Integer");
        javaTypeMap.put("integer", "java.lang.Integer");
        javaTypeMap.put("bigint", "java.lang.Long");
        javaTypeMap.put("float", "java.lang.Float");
        javaTypeMap.put("double", "java.lang.Double");
        javaTypeMap.put("decimal", "java.math.BigDecimal");
        javaTypeMap.put("bit", "java.lang.Boolean");
        javaTypeMap.put("char", "java.lang.String");
        javaTypeMap.put("varchar", "java.lang.String");
        javaTypeMap.put("tinytext", "java.lang.String");
        javaTypeMap.put("text", "java.lang.String");
        javaTypeMap.put("mediumtext", "java.lang.String");
        javaTypeMap.put("longtext", "java.lang.String");
        javaTypeMap.put("time", "java.lang.String");
        javaTypeMap.put("date", "java.util.Date");
        javaTypeMap.put("datetime", "java.util.Date");
        javaTypeMap.put("timestamp", "java.lang.String");
        javaTypeMap.put("nvarchar","java.lang.String");
    }
}