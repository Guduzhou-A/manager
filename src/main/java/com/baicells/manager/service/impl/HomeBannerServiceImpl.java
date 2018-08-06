package com.baicells.manager.service.impl;

import com.baicells.manager.mapper.HomeBannerDao;
import com.baicells.manager.model.dto.HomeBannerDataDto;
import com.baicells.manager.model.dto.HomeQuery4WebDto;
import com.baicells.manager.model.entity.HomeBanner;
import com.baicells.manager.model.entity.MediaBrand;
import com.baicells.manager.service.HomeBannerService;
import com.baicells.manager.utils.DateUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
@Service
public class HomeBannerServiceImpl implements HomeBannerService {

    @Autowired
    private HomeBannerDao homeBannerDao;

    @Override
    public PageInfo<HomeBanner> listByQuery(HomeQuery4WebDto dto) {
        PageHelper.startPage(dto.getIndex(), dto.getSize());
        Example example = new Example(HomeBanner.class);
        List<HomeBanner> productPages = homeBannerDao.selectByExample(example);
        PageInfo<HomeBanner> pagePageInfo = new PageInfo<>(productPages, dto.getSize());
        return pagePageInfo;
    }

    @Override
    public void addByDto(HomeBannerDataDto dto) {
        HomeBanner homeBanner = new HomeBanner();
        if (StringUtils.isBlank(dto.getTitle()) || StringUtils.isBlank(dto.getTitle()))
            return;
        homeBanner.setTitle(dto.getTitle());
        homeBanner.setImgUrl(dto.getImgUrl());
        homeBanner.setCreateTime(DateUtils.now());
        homeBannerDao.insert(homeBanner);

    }

    @Override
    public void delete(int id) {
        homeBannerDao.deleteByPrimaryKey(id);

    }

    @Override
    public List<HomeBanner> list() {
        return homeBannerDao.selectAll();
    }



}
