package com.baicells.manager.service;

import com.baicells.manager.model.dto.MediaCustomerDataDto;
import com.baicells.manager.model.dto.MediaNewsDataDto;
import com.baicells.manager.model.dto.MediaQuery4WebDto;
import com.baicells.manager.model.entity.MediaBrand;
import com.baicells.manager.model.entity.MediaCustomer;
import com.baicells.manager.model.entity.MediaNews;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface MediaCustomerService {

    PageInfo<MediaCustomer> listByQuery(MediaQuery4WebDto dto);

    void delete(int id);


    void addOrUpdateByDto(MediaCustomerDataDto dto);

    MediaCustomer getById(int id);

    PageInfo<MediaCustomer> listByIndexAndSort(int first, int maxSize);
}
