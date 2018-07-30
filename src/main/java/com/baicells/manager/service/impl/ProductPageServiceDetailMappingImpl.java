package com.baicells.manager.service.impl;

import com.baicells.manager.mapper.ProductPageContentDao;
import com.baicells.manager.mapper.ProductPageDetailMappingDao;
import com.baicells.manager.model.entity.ProductPageContent;
import com.baicells.manager.model.entity.ProductPageDetailMapping;
import com.baicells.manager.service.ProductPageContentService;
import com.baicells.manager.service.ProductPageDetailMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class ProductPageServiceDetailMappingImpl implements ProductPageDetailMappingService {


    @Autowired
    private ProductPageDetailMappingDao productPageDetailMappingDao;
    @Override
    public List<ProductPageDetailMapping> getByPageId(Integer id) {
        Example example = new Example(ProductPageDetailMapping.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("productPageId",id);
        return productPageDetailMappingDao.selectByExample(example);
    }

}
