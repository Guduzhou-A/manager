package com.baicells.manager.service;

import com.baicells.manager.model.entity.ProductPage;
import com.baicells.manager.model.entity.ProductPageDetail;

import java.util.List;

public interface ProductPageDetailService {


    List<ProductPageDetail> getByGGId(Integer id);
}
