package com.baicells.manager.service;

import com.baicells.manager.model.dto.MediaNewsDataDto;
import com.baicells.manager.model.dto.MediaQuery4WebDto;
import com.baicells.manager.model.entity.MediaBrand;
import com.baicells.manager.model.entity.MediaNews;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface MediaNewsService {
    void delete(int id);

    PageInfo<MediaNews> listByQuery(MediaQuery4WebDto dto);


    void addOrUpdateByDto(MediaNewsDataDto dto) throws Exception;

    MediaNews getById(int id);

    PageInfo<MediaNews> listByIndexAndSort(int first, int maxSize);
}
