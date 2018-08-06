package com.baicells.manager.service;

import com.baicells.manager.model.dto.ProductDataDto;
import com.baicells.manager.model.dto.ProductPageQuery4WebDto;
import com.baicells.manager.model.entity.ProductPage;
import com.baicells.manager.model.entity.SolutionLetPage;
import com.github.pagehelper.PageInfo;

import java.io.IOException;
import java.util.List;

public interface ProductPageService {
    PageInfo<ProductPage> listByQuery(ProductPageQuery4WebDto dto);

    void addOrUpdateProductByDto(ProductDataDto dto) throws Exception;

    ProductPage getById(int id);

    void updateById(ProductPage productPage);

    void delete(int id);

    List<ProductPage> listByEnable();
}
