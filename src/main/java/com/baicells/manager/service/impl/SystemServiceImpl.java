package com.baicells.manager.service.impl;

import com.baicells.manager.mapper.SysPageConfigDao;
import com.baicells.manager.model.entity.SysPageConfig;
import com.baicells.manager.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SystemServiceImpl implements SystemService {


    @Autowired
    private SysPageConfigDao sysPageConfigDao;

    @Override
    public SysPageConfig execGetOne() {
        SysPageConfig sysPageConfig =  sysPageConfigDao.selectByPrimaryKey(1);
        if (sysPageConfig == null){
            sysPageConfig = new SysPageConfig();
            sysPageConfig.setId(1);
            sysPageConfigDao.insert(sysPageConfig);
        }
        return sysPageConfig;
    }

    @Override
    public void update5gContent(String content) {
        SysPageConfig sysPageConfig =  sysPageConfigDao.selectByPrimaryKey(1);
        if (sysPageConfig == null){
            sysPageConfig = new SysPageConfig();
            sysPageConfig.setId(1);
            sysPageConfig.setSolution5gNavDesc(content);
            sysPageConfigDao.insert(sysPageConfig);
        }else{
            sysPageConfig.setSolution5gNavDesc(content);
            sysPageConfigDao.updateByPrimaryKey(sysPageConfig);
        }
    }
}
