package com.gag.enterprisewxmobile.system.zcheck.service.impl;

import com.gag.enterprisewxmobile.system.zcheck.dao.ZcheckDao;
import com.gag.enterprisewxmobile.system.zcheck.entity.Zcheck;
import com.gag.enterprisewxmobile.system.zcheck.service.ZcheckService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("zcheckService")
public class ZcheckServiceImpl implements ZcheckService {

    @Resource
    private ZcheckDao zcheckDao;

    public List<Zcheck> selectZcheck(){
        return zcheckDao.selectZcheck();
    };
}
