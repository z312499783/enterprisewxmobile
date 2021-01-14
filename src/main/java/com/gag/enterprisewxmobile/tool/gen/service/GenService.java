package com.gag.enterprisewxmobile.tool.gen.service;

import com.gag.enterprisewxmobile.tool.gen.entity.ColumnInfo;
import com.gag.enterprisewxmobile.system.kaSystem.entity.KaSystemTime;
import com.gag.enterprisewxmobile.tool.gen.entity.TableInfo;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public interface GenService {

    LinkedList<LinkedHashMap> selectmseg(String tableName);

    int insertmsg(Map map);

    /**
     * 根据表名称查询信息
     *
     * @param tableName 表名称
     * @return 表信息
     */
    TableInfo selectTableByName(String tableName);

    TableInfo selectTableBySelectName(String tableName);

    /**
     * 根据表名称查询列信息
     *
     * @param tableName 表名称
     * @return 列信息
     */
    List<ColumnInfo> selectTableColumnsByName(String tableName);

    List<ColumnInfo> selectTableCoulumnsBySelectName(String tableName);

    /**
     * 根据表名称查询表名，拥有着，数据行数，是否拥有主键(1是拥有,0是没有)，表描述，表的主键名称，表的主键类型
     * @param tableName
     * @return
     */
    Map selectTablestructure(String tableName);


}