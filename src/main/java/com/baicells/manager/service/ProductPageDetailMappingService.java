package com.baicells.manager.service;

import com.baicells.manager.model.entity.ProductPageContent;
import com.baicells.manager.model.entity.ProductPageDetailMapping;

import java.util.List;

public interface ProductPageDetailMappingService {

    List<ProductPageDetailMapping> getByPageId(Integer id);
}
