package com.gag.enterprisewxmobile.system.kaSystem.service;

import com.gag.enterprisewxmobile.system.kaSystem.entity.KaSystemTime;
import com.gag.enterprisewxmobile.system.kaSystem.entity.ShopPos;
import com.gag.enterprisewxmobile.tool.gen.entity.ColumnInfo;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * 商品销售(ShopPos)表服务接口
 *
 * @author makejava
 * @since 2020-11-02 15:59:49
 */
public interface ShopPosService {

    LinkedList<LinkedHashMap> kaSystem(KaSystemTime kaSystemTime);

    List<ColumnInfo> selectTableColumnsByName();

    List<LinkedHashMap> selectmseg(ShopPos shopPos);

}