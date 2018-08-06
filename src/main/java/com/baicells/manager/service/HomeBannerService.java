package com.baicells.manager.service;

import com.baicells.manager.model.dto.HomeBannerDataDto;
import com.baicells.manager.model.dto.HomeQuery4WebDto;
import com.baicells.manager.model.entity.HomeBanner;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface HomeBannerService {
    PageInfo<HomeBanner> listByQuery(HomeQuery4WebDto dto);

    void addByDto(HomeBannerDataDto dto);

    void delete(int id);

    List<HomeBanner> list();
}
