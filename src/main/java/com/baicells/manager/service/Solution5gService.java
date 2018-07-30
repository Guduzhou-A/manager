package com.baicells.manager.service;

import com.baicells.manager.model.dto.Solution5gDataDto;
import com.baicells.manager.model.dto.SolutionQuery4WebDto;
import com.baicells.manager.model.entity.Solution5gPage;
import com.github.pagehelper.PageInfo;

public interface Solution5gService {
    PageInfo<Solution5gPage> listByQuery(SolutionQuery4WebDto dto);

    void addOrUpdateByDto(Solution5gDataDto dto) throws Exception;

    void delete(int id);

    void updateById(Solution5gPage solution5gPage);

    Solution5gPage getById(int id);
}
