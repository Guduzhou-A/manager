package com.baicells.manager.service;

import com.baicells.manager.model.dto.ProductDataDto;
import com.baicells.manager.model.dto.ProductPageQuery4WebDto;
import com.baicells.manager.model.entity.ProductPage;
import com.baicells.manager.model.entity.ProductPageContent;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ProductPageContentService {

    List<ProductPageContent> getByPageId(Integer id);
}
