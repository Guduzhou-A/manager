package com.baicells.manager.service.impl;

import com.baicells.manager.mapper.ProductPageDao;
import com.baicells.manager.model.dto.ProductPageQuery4WebDto;
import com.baicells.manager.model.entity.ProductPage;
import com.baicells.manager.service.ProductPageService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class ProductPageServiceImpl implements ProductPageService {

    @Autowired
    private ProductPageDao productPageDao;

    @Override
    public PageInfo<ProductPage> listByQuery(ProductPageQuery4WebDto dto) {
        PageHelper.startPage(dto.getIndex(),dto.getSize());
        Example example = new Example(ProductPage.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(dto.getTitle()))
            criteria.andEqualTo("title",dto.getTitle());
        if (StringUtils.isNotBlank(dto.getBeginTime()))
            criteria.andGreaterThanOrEqualTo("createTime",dto.getBeginTime());
        if (StringUtils.isNotBlank(dto.getEndTime()))
            criteria.andLessThan("createTime",dto.getEndTime());
        List<ProductPage> productPages = productPageDao.selectByExample(example);
        PageInfo<ProductPage> pagePageInfo = new PageInfo<>(productPages,dto.getSize());
        return pagePageInfo;
    }
}
