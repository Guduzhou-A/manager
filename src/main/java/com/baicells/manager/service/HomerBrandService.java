package com.baicells.manager.service;

import com.baicells.manager.model.dto.HomeBannerDataDto;
import com.baicells.manager.model.dto.HomeQuery4WebDto;
import com.baicells.manager.model.entity.HomeBrand;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface HomerBrandService {
    PageInfo<HomeBrand> listByQuery(HomeQuery4WebDto dto);

    void delete(int id);

    void addByDto(HomeBannerDataDto dto);

    void updateBrandStatus(int id);

    HomeBrand getByEnable();
}
