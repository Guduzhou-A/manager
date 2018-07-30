package com.baicells.manager.service.impl;

import com.baicells.manager.config.properties.ConfigProperties;
import com.baicells.manager.mapper.ProductPageContentDao;
import com.baicells.manager.mapper.ProductPageDao;
import com.baicells.manager.mapper.ProductPageDetailDao;
import com.baicells.manager.mapper.ProductPageDetailMappingDao;
import com.baicells.manager.model.dto.ProductDataDto;
import com.baicells.manager.model.dto.ProductPageQuery4WebDto;
import com.baicells.manager.model.entity.ProductPage;
import com.baicells.manager.model.entity.ProductPageContent;
import com.baicells.manager.model.entity.ProductPageDetail;
import com.baicells.manager.model.entity.ProductPageDetailMapping;
import com.baicells.manager.service.ProductPageContentService;
import com.baicells.manager.service.ProductPageService;
import com.baicells.manager.utils.CommonUtil;
import com.baicells.manager.utils.CreateHtmlUtils;
import com.baicells.manager.utils.DateUtils;
import com.baicells.manager.utils.FastJsonUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductPageServiceContentImpl implements ProductPageContentService {


    @Autowired
    private ProductPageContentDao productPageContentDao;

    @Override
    public List<ProductPageContent> getByPageId(Integer id) {
        Example example = new Example(ProductPageContent.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("productPageId",id);
        return productPageContentDao.selectByExample(example);
    }
}
