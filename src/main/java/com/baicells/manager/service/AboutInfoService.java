package com.baicells.manager.service;

import com.baicells.manager.model.dto.AboutPageQuery4WebDto;
import com.baicells.manager.model.entity.AboutInfo;
import com.github.pagehelper.PageInfo;

public interface AboutInfoService {

    PageInfo<AboutInfo> listByQuery(AboutPageQuery4WebDto dto);

    AboutInfo getById(int id);

    void deleteDataById(int id);

    void updateByEntity(AboutInfo aboutInfo);
}
