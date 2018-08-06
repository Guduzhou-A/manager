package com.baicells.manager.service.impl;

import com.baicells.manager.mapper.SupportDownloadDao;
import com.baicells.manager.model.dto.SupportDownloadDataDto;
import com.baicells.manager.model.dto.SupportQuery4WebDto;
import com.baicells.manager.model.entity.ProductPage;
import com.baicells.manager.model.entity.SupportDownload;
import com.baicells.manager.service.SupportDownloadService;
import com.baicells.manager.utils.DateUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class SupportDownloadServiceImpl implements SupportDownloadService {
    @Autowired
    private SupportDownloadDao supportDownloadDao;

    @Override
    public PageInfo<SupportDownload> listByQuery(SupportQuery4WebDto dto) {
        PageHelper.startPage(dto.getIndex(), dto.getSize());
        Example example = new Example(SupportDownload.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(dto.getTitle()))
            criteria.andLike("title", dto.getTitle() + "%");
        if (dto.getType() > 0)
            criteria.andEqualTo("type", dto.getType());
        if (StringUtils.isNotBlank(dto.getBeginTime()))
            criteria.andGreaterThanOrEqualTo("createTime", dto.getBeginTime());
        if (StringUtils.isNotBlank(dto.getEndTime()))
            criteria.andLessThan("createTime", dto.getEndTime());
        List<SupportDownload> productPages = supportDownloadDao.selectByExample(example);
        PageInfo<SupportDownload> pagePageInfo = new PageInfo<>(productPages, dto.getSize());
        return pagePageInfo;
    }

    @Override
    public void delete(int id) {
        supportDownloadDao.deleteByPrimaryKey(id);
    }

    @Override
    public void addOrUpdateByDto(SupportDownloadDataDto dto) {
        SupportDownload supportDownload;
        if (dto.getId() != null && dto.getId() > 0) {
            supportDownload = supportDownloadDao.selectByPrimaryKey(dto.getId());
            if (supportDownload == null) {
                supportDownload = new SupportDownload();
                supportDownload.setCreateTime(DateUtils.now());
            }
        } else {
            supportDownload = new SupportDownload();
            supportDownload.setCreateTime(DateUtils.now());
        }

        supportDownload.setTitle(dto.getTitle());
        supportDownload.setType(dto.getType());
        supportDownload.setUrl(dto.getUrl());
        if (supportDownload.getId() != null && supportDownload.getId() > 0) {
            supportDownloadDao.updateByPrimaryKey(supportDownload);
        } else {
            supportDownloadDao.insert(supportDownload);
        }


    }

    @Override
    public SupportDownload getById(int id) {
        return supportDownloadDao.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo<SupportDownload> listByIndexAndSort(int index, int size, int type) {
        PageHelper.startPage(index, size);
        Example example = new Example(SupportDownload.class);
        Example.Criteria criteria = example.createCriteria();
        if (type > 0)
            criteria.andEqualTo("type", type);
        example.orderBy("createTime").desc();
        List<SupportDownload> productPages = supportDownloadDao.selectByExample(example);
        PageInfo<SupportDownload> pagePageInfo = new PageInfo<>(productPages, size);
        return pagePageInfo;
    }
}
