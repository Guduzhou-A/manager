package com.baicells.manager.service;

import com.baicells.manager.model.dto.SolutionLetDataDto;
import com.baicells.manager.model.dto.SolutionQuery4WebDto;
import com.baicells.manager.model.entity.SolutionLetPage;
import com.github.pagehelper.PageInfo;

public interface SolutionLETService {
    PageInfo<SolutionLetPage> listByQuery(SolutionQuery4WebDto dto);

    SolutionLetPage getById(int id);

    void addOrUpdateByDto(SolutionLetDataDto dto) throws Exception;

    void delete(int id);

    void updateById(SolutionLetPage solutionLetPage);
}
