package com.gag.enterprisewxmobile.system.zcheck.dao;

import com.gag.enterprisewxmobile.system.zcheck.entity.Zcheck;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ZcheckDao {

    List<Zcheck> selectZcheck();
}
