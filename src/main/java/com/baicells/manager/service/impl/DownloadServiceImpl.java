package com.baicells.manager.service.impl;

import com.baicells.manager.mapper.DownloadDao;
import com.baicells.manager.model.entity.Download;
import com.baicells.manager.model.entity.SysPageConfig;
import com.baicells.manager.service.DownloadService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DownloadServiceImpl implements DownloadService {
    @Autowired
    private DownloadDao downloadDao;


    @Override
    public void add(String url) {
        Download download =  downloadDao.selectByPrimaryKey(1);
        if (download == null){
            download = new Download();
            download.setId(1);
            downloadDao.insert(download);
        }
        if (StringUtils.isNotBlank(url)){
            download.setUrl(url);
            downloadDao.updateByPrimaryKey(download);
        }
    }

    @Override
    public String getOne() {
        Download download =  downloadDao.selectByPrimaryKey(1);
        if (download == null){
            return "";
        }
        return download.getUrl();
    }
}
