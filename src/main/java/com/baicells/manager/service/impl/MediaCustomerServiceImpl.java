package com.baicells.manager.service.impl;

import com.baicells.manager.config.properties.ConfigProperties;
import com.baicells.manager.mapper.MediaCustomerDao;
import com.baicells.manager.mapper.MediaNewsDao;
import com.baicells.manager.model.dto.MediaCustomerDataDto;
import com.baicells.manager.model.dto.MediaNewsDataDto;
import com.baicells.manager.model.dto.MediaQuery4WebDto;
import com.baicells.manager.model.entity.MediaCustomer;
import com.baicells.manager.model.entity.MediaNews;
import com.baicells.manager.service.MediaCustomerService;
import com.baicells.manager.service.MediaNewsService;
import com.baicells.manager.utils.CommonUtil;
import com.baicells.manager.utils.CreateHtmlUtils;
import com.baicells.manager.utils.DateUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MediaCustomerServiceImpl implements MediaCustomerService {

    @Autowired
    private MediaCustomerDao mediaCustomerDao;
    @Autowired
    private ConfigProperties configProperties;

    @Override
    public PageInfo<MediaCustomer> listByQuery(MediaQuery4WebDto dto) {
        PageHelper.startPage(dto.getIndex(), dto.getSize());
        Example example = new Example(MediaCustomer.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(dto.getTitle()))
            criteria.andLike("title", dto.getTitle() + "%");
        if (StringUtils.isNotBlank(dto.getBeginTime()))
            criteria.andGreaterThanOrEqualTo("createTime", dto.getBeginTime());
        if (StringUtils.isNotBlank(dto.getEndTime()))
            criteria.andLessThan("createTime", dto.getEndTime());
        List<MediaCustomer> productPages = mediaCustomerDao.selectByExample(example);
        PageInfo<MediaCustomer> pagePageInfo = new PageInfo<>(productPages, dto.getSize());
        return pagePageInfo;
    }

    @Override
    public void delete(int id) {
        mediaCustomerDao.deleteByPrimaryKey(id);
    }

    @Override
    public void addOrUpdateByDto(MediaCustomerDataDto dto) {
        MediaCustomer mediaCustomer;
        if (dto.getId() > 0) {
            mediaCustomer = mediaCustomerDao.selectByPrimaryKey(dto.getId());
            if (mediaCustomer == null) {
                mediaCustomer = new MediaCustomer();
                mediaCustomer.setCreateTime(DateUtils.now());
            }
        } else {
            mediaCustomer = new MediaCustomer();
            mediaCustomer.setCreateTime(DateUtils.now());
        }

        mediaCustomer.setNavTitle(dto.getNavTitle());
        mediaCustomer.setNavPicUrl(dto.getNavPicUrl());
        mediaCustomer.setNavDesc(dto.getNavDesc());
        mediaCustomer.setNavPicUrl(dto.getNavPicUrl());
        mediaCustomer.setFileUrl(dto.getFileUrl());
        if (mediaCustomer.getId() != null && mediaCustomer.getId() > 0) {
            mediaCustomerDao.updateByPrimaryKey(mediaCustomer);
        } else {
            mediaCustomerDao.insert(mediaCustomer);
        }
    }

    @Override
    public MediaCustomer getById(int id) {
        return mediaCustomerDao.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo<MediaCustomer> listByIndexAndSort(int first, int maxSize) {
        PageHelper.startPage(first, maxSize);
        Example example = new Example(MediaCustomer.class);
        example.orderBy("createTime").desc();
        List<MediaCustomer> productPages = mediaCustomerDao.selectByExample(example);
        PageInfo<MediaCustomer> pagePageInfo = new PageInfo<>(productPages, maxSize);
        return pagePageInfo;
    }
}
