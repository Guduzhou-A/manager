package com.baicells.manager.service;

import com.baicells.manager.model.dto.MediaBrandDataDto;
import com.baicells.manager.model.dto.MediaQuery4WebDto;
import com.baicells.manager.model.entity.MediaBrand;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface MediaBrandService {
    PageInfo<MediaBrand> listByQuery(MediaQuery4WebDto dto);

    void delete(int id);

    void addByDto(MediaBrandDataDto dto);

    List<MediaBrand> listByIndexAndSort(int first, int maxSize);
}
