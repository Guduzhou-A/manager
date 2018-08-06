package com.baicells.manager.service;

import com.baicells.manager.model.dto.SupportDownloadDataDto;
import com.baicells.manager.model.dto.SupportQuery4WebDto;
import com.baicells.manager.model.entity.SupportDownload;
import com.github.pagehelper.PageInfo;

public interface SupportDownloadService {
    PageInfo<SupportDownload> listByQuery(SupportQuery4WebDto dto);

    void delete(int id);

    void addOrUpdateByDto(SupportDownloadDataDto dto);

    SupportDownload getById(int id);

    PageInfo<SupportDownload> listByIndexAndSort(int index, int size, int type);
}
