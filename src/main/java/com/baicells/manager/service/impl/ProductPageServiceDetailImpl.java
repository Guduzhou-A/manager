package com.baicells.manager.service.impl;

import com.baicells.manager.mapper.ProductPageContentDao;
import com.baicells.manager.mapper.ProductPageDetailDao;
import com.baicells.manager.model.entity.ProductPage;
import com.baicells.manager.model.entity.ProductPageDetail;
import com.baicells.manager.service.ProductPageContentService;
import com.baicells.manager.service.ProductPageDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class ProductPageServiceDetailImpl implements ProductPageDetailService {


    @Autowired
    private ProductPageDetailDao productPageDetailDao;


    @Override
    public List<ProductPageDetail> getByGGId(Integer id) {
        Example example = new Example(ProductPageDetail.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("groupMappingId",id);
        return productPageDetailDao.selectByExample(example);
    }
}
