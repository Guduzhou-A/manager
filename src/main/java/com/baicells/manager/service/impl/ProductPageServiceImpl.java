package com.baicells.manager.service.impl;

import com.baicells.manager.config.properties.ConfigProperties;
import com.baicells.manager.mapper.ProductPageContentDao;
import com.baicells.manager.mapper.ProductPageDao;
import com.baicells.manager.mapper.ProductPageDetailDao;
import com.baicells.manager.mapper.ProductPageDetailMappingDao;
import com.baicells.manager.model.dto.ProductDataDto;
import com.baicells.manager.model.dto.ProductPageQuery4WebDto;
import com.baicells.manager.model.entity.*;
import com.baicells.manager.service.ProductPageService;
import com.baicells.manager.utils.CommonUtil;
import com.baicells.manager.utils.CreateHtmlUtils;
import com.baicells.manager.utils.DateUtils;
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
public class ProductPageServiceImpl implements ProductPageService {

    @Autowired
    private ProductPageDao productPageDao;

    @Autowired
    private ConfigProperties configProperties;

    @Autowired
    private ProductPageContentDao productPageContentDao;
    @Autowired
    private ProductPageDetailMappingDao productPageDetailMappingDao;
    @Autowired
    private ProductPageDetailDao productPageDetailDao;

    @Override
    public ProductPage getById(int id) {
        return productPageDao.selectByPrimaryKey(id);
    }

    @Override
    public void updateById(ProductPage productPage) {
        productPageDao.updateByPrimaryKey(productPage);
    }

    @Override
    public void delete(int id) {
        productPageDao.deleteByPrimaryKey(id);
    }

    @Override
    public PageInfo<ProductPage> listByQuery(ProductPageQuery4WebDto dto) {
        PageHelper.startPage(dto.getIndex(), dto.getSize());
        Example example = new Example(ProductPage.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(dto.getTitle()))
            criteria.andLike("title", dto.getTitle()+"%");
        if (dto.getStatus() >=0)
            criteria.andEqualTo("enable", dto.getStatus());
        if (StringUtils.isNotBlank(dto.getBeginTime()))
            criteria.andGreaterThanOrEqualTo("createTime", dto.getBeginTime());
        if (StringUtils.isNotBlank(dto.getEndTime()))
            criteria.andLessThan("createTime", dto.getEndTime());
        List<ProductPage> productPages = productPageDao.selectByExample(example);
        PageInfo<ProductPage> pagePageInfo = new PageInfo<>(productPages, dto.getSize());
        return pagePageInfo;
    }

    @Override
    public void addOrUpdateProductByDto(ProductDataDto dto) throws Exception {
        //
        String htmlName = "product_" + CommonUtil.getUUID(16);
        Map<String, Object> fltMap = new HashMap<>();
        fltMap.put("data", dto);
        String oldUrl = null;

        ProductPage productPage;
        if (dto.getId() > 0) {
            productPage = productPageDao.selectByPrimaryKey(dto.getId());
            if (null == productPage) {
                productPage = new ProductPage();
                productPage.setEnable(false);
                productPage.setCreateTime(DateUtils.now());
            } else {
                oldUrl=productPage.getPortalForwardUrl();
                productPage.setUpdateTime(DateUtils.now());
            }
        } else {
            productPage = new ProductPage();
            productPage.setEnable(false);
            productPage.setCreateTime(DateUtils.now());
        }

        if (dto.isTwoContent()){
            CreateHtmlUtils.createHtml(configProperties.getWebRoot(), "product_detail_double_template.ftl", htmlName, fltMap,oldUrl);
        }else{
            CreateHtmlUtils.createHtml(configProperties.getWebRoot(), "product_detail_template.ftl", htmlName, fltMap,oldUrl);
        }

        String forwardUrl = configProperties.getNginxWebRoot() + htmlName+".html";

        productPage.setTitle(dto.getTitle());
        productPage.setBgPicUrl(dto.getBgPicUrl());
        productPage.setNavPicUrl(dto.getNavPicUrl());
        productPage.setPortalPicUrl(dto.getPortalPicUrl());
        productPage.setPortalForwardUrl(forwardUrl);

        if (productPage.getId() != null && productPage.getId() > 0) {
            productPageDao.updateByPrimaryKey(productPage);
        } else {
            productPageDao.insert(productPage);
        }
        int pid = productPage.getId();


        if (dto.getContents() != null && dto.getContents().size() > 0) {
            dto.getContents().forEach(c -> {
                ProductPageContent productPageContent;
                if (c.getId() > 0) {
                    productPageContent = productPageContentDao.selectByPrimaryKey(c.getId());
                    if (null == productPageContent) {
                        productPageContent = new ProductPageContent();
                    }
                } else {
                    productPageContent = new ProductPageContent();
                }
                productPageContent.setContent(c.getContent());
                productPageContent.setTitle(c.getTitle());
                productPageContent.setProductPageId(pid);

                if (productPageContent.getId() != null && productPageContent.getId() > 0) {
                    productPageContentDao.updateByPrimaryKey(productPageContent);
                } else {
                    productPageContentDao.insert(productPageContent);
                }
            });
        }

        if (dto.getProductGroups() != null && dto.getProductGroups().size() > 0) {
            dto.getProductGroups().forEach(gg -> {
                ProductPageDetailMapping productPageDetailMapping;
                if (gg.getId() > 0) {
                    productPageDetailMapping = productPageDetailMappingDao.selectByPrimaryKey(gg.getId());
                    if (null == productPageDetailMapping) {
                        productPageDetailMapping = new ProductPageDetailMapping();
                    }
                } else {
                    productPageDetailMapping = new ProductPageDetailMapping();
                }
                productPageDetailMapping.setGroupTitle(gg.getGroupTitle());
                productPageDetailMapping.setProductPageId(pid);
                if (productPageDetailMapping.getId() != null && productPageDetailMapping.getId() > 0) {
                    productPageDetailMappingDao.updateByPrimaryKey(productPageDetailMapping);
                } else {
                    productPageDetailMappingDao.insert(productPageDetailMapping);
                }
                int ppdmId = productPageDetailMapping.getId();
                if (gg.getProducts() != null && gg.getProducts().size() > 0) {
                    gg.getProducts().forEach(g -> {
                        ProductPageDetail productPageDetail;
                        if (g.getId() > 0) {
                            productPageDetail = productPageDetailDao.selectByPrimaryKey(g.getId());
                            if (null == productPageDetail) {
                                productPageDetail = new ProductPageDetail();
                            }
                        } else {
                            productPageDetail = new ProductPageDetail();
                        }
                        productPageDetail.setPicUrl(g.getUrl());
                        productPageDetail.setSpecifications(g.getSpecifications());
                        productPageDetail.setDesc(g.getDesc());
                        productPageDetail.setFileUrl(g.getPdf());
                        productPageDetail.setGroupMappingId(ppdmId);
                        if (productPageDetail.getId() != null && productPageDetail.getId() > 0) {
                            productPageDetailDao.updateByPrimaryKey(productPageDetail);
                        } else {
                            productPageDetailDao.insert(productPageDetail);
                        }

                    });

                }

            });
        }


    }

    @Override
    public List<ProductPage> listByEnable() {
        Example example = new Example(ProductPage.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("enable", true);
        return productPageDao.selectByExample(example);
    }
}
