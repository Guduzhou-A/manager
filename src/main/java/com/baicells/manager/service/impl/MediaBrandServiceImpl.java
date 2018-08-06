package com.baicells.manager.service.impl;

import com.baicells.manager.mapper.MediaBrandDao;
import com.baicells.manager.model.dto.MediaBrandDataDto;
import com.baicells.manager.model.dto.MediaQuery4WebDto;
import com.baicells.manager.model.entity.MediaBrand;
import com.baicells.manager.model.entity.MediaCustomer;
import com.baicells.manager.service.MediaBrandService;
import com.baicells.manager.utils.DateUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class MediaBrandServiceImpl implements MediaBrandService {
    @Autowired
    private MediaBrandDao mediaBrandDao;

    @Override
    public PageInfo<MediaBrand> listByQuery(MediaQuery4WebDto dto) {
        PageHelper.startPage(dto.getIndex(), dto.getSize());
        Example example = new Example(MediaBrand.class);
        List<MediaBrand> productPages = mediaBrandDao.selectByExample(example);
        PageInfo<MediaBrand> pagePageInfo = new PageInfo<>(productPages, dto.getSize());
        return pagePageInfo;
    }

    @Override
    public void delete(int id) {
        mediaBrandDao.deleteByPrimaryKey(id);
    }

    @Override
    public void addByDto(MediaBrandDataDto dto) {
        MediaBrand mediaBrand = new MediaBrand();
        if (StringUtils.isBlank(dto.getTitle()) || StringUtils.isBlank(dto.getTitle()))
            return;
        mediaBrand.setTitle(dto.getTitle());
        mediaBrand.setImgUrl(dto.getImgUrl());
        mediaBrand.setCreateTime(DateUtils.now());
        mediaBrandDao.insert(mediaBrand);
    }

    @Override
    public List<MediaBrand> listByIndexAndSort(int first, int maxSize) {
//        PageHelper.startPage(first, maxSize);
//        Example example = new Example(MediaBrand.class);
//        example.orderBy("createTime").desc();
//        List<MediaBrand> productPages = mediaBrandDao.selectByExample(example);
//
//        PageInfo<MediaBrand> pagePageInfo = new PageInfo<>(productPages, maxSize);
//
//        return pagePageInfo.getList();
        return mediaBrandDao.selectAll();
    }
}
