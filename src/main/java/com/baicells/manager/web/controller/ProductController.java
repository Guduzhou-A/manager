package com.baicells.manager.web.controller;


import com.baicells.manager.model.dto.*;
import com.baicells.manager.model.entity.ProductPage;
import com.baicells.manager.model.entity.ProductPageContent;
import com.baicells.manager.model.entity.ProductPageDetail;
import com.baicells.manager.model.entity.ProductPageDetailMapping;
import com.baicells.manager.service.ProductPageContentService;
import com.baicells.manager.service.ProductPageDetailMappingService;
import com.baicells.manager.service.ProductPageDetailService;
import com.baicells.manager.service.ProductPageService;
import com.baicells.manager.utils.*;
import com.github.pagehelper.PageInfo;
import org.apache.commons.math.stat.descriptive.summary.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/product")
public class ProductController {

    private static Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductPageService productPageServiceImpl;
    @Autowired
    private ProductPageContentService productPageContentService;
    @Autowired
    private ProductPageDetailService productPageDetailService;
    @Autowired
    private ProductPageDetailMappingService productPageDetailMappingService;


    @RequestMapping("/list")
    public String index(HttpServletRequest request) {

        return "site.baicells.product.list";
    }

    @RequestMapping("/ajax")
    @ResponseBody
    public Result ajax(String data) {
        Result result = new Result();
        result.setCode(ResultCode.SUCCESS);
        ProductPageQuery4WebDto dto = ConvertQuery4WebData.convertCommentQuery(data);
        PageInfo<ProductPage> pagePageInfo = productPageServiceImpl.listByQuery(dto);
        DataTablesMap dataTablesMap = new DataTablesMap();
        dataTablesMap.setData(pagePageInfo.getList());
        dataTablesMap.setiTotalRecords(pagePageInfo.getPageNum());
        dataTablesMap.setiTotalDisplayRecords(pagePageInfo.getTotal());

        result.setData(dataTablesMap);
        return result;
    }

    @RequestMapping("/edit/{id}")
    @ResponseBody
    public Result editData(@PathVariable(name = "id") int id) {
        Result result = new Result();
        result.setCode(ResultCode.SUCCESS);
        ProductDataDto dto = new ProductDataDto();
        ProductPage productPage = productPageServiceImpl.getById(id);
        if (productPage == null) {
            result.setCode(ResultCode.NOT_FOUND);
            return result;
        }
        dto.setId(productPage.getId());
        dto.setBgPicUrl(productPage.getBgPicUrl());
        dto.setNavPicUrl(productPage.getNavPicUrl());
        dto.setPortalPicUrl(productPage.getPortalPicUrl());
        dto.setTitle(productPage.getTitle());
        List<ProductPageContent> productPageContents = productPageContentService.getByPageId(productPage.getId());
        if (productPageContents != null && productPageContents.size() > 0) {
            if (productPageContents.size() > 1) {
                dto.setTwoContent(true);
            } else {
                dto.setTwoContent(false);
            }

            List<ProductContentDto> contentDtos = new ArrayList<>();
            productPageContents.forEach(ppc -> {
                ProductContentDto contentDto = new ProductContentDto();
                contentDto.setContent(ppc.getContent());
                contentDto.setId(ppc.getId());
                contentDto.setTitle(ppc.getTitle());
                contentDtos.add(contentDto);
            });
            dto.setContents(contentDtos);
        }

        List<ProductPageDetailMapping> productPageDetailMappings = productPageDetailMappingService.getByPageId(productPage.getId());
        if (productPageDetailMappings != null && productPageDetailMappings.size() > 0) {
            List<ProductGroupDto> productGroupDtos = new ArrayList<>();

            productPageDetailMappings.forEach(ppdm -> {
                ProductGroupDto productGroupDto = new ProductGroupDto();
                productGroupDto.setGroupTitle(ppdm.getGroupTitle());
                productGroupDto.setId(ppdm.getId());
                List<ProductPageDetail> productPageDetails = productPageDetailService.getByGGId(ppdm.getId());
                if (productPageDetails != null && productPageDetails.size() > 0) {
                    List<ProductDto> productDtos = new ArrayList<>();
                    productPageDetails.forEach(ppd -> {
                        ProductDto productDto = new ProductDto();
                        productDto.setId(ppd.getId());
                        productDto.setDesc(ppd.getDesc());
                        productDto.setPdf(ppd.getFileUrl());
                        productDto.setUrl(ppd.getPicUrl());
                        productDto.setSpecifications(ppd.getSpecifications());
                        productDtos.add(productDto);
                    });
                    productGroupDto.setProducts(productDtos);
                }
                productGroupDtos.add(productGroupDto);
            });
            dto.setProductGroups(productGroupDtos);
        }
        result.setData(dto);
        return result;
    }


    @RequestMapping("/editStatus/{id}")
    @ResponseBody
    public Result editStatus(@PathVariable(name = "id") int id) {
        Result result = new Result();
        result.setCode(ResultCode.SUCCESS);
        ProductPage productPage = productPageServiceImpl.getById(id);
        if (null != productPage) {
            productPage.setEnable(!productPage.getEnable());
            productPage.setUpdateTime(DateUtils.now());
            productPageServiceImpl.updateById(productPage);
        }
        return result;
    }

    @RequestMapping("/delete/{id}")
    @ResponseBody
    public Result delete(@PathVariable(name = "id") int id) {
        Result result = new Result();
        result.setCode(ResultCode.SUCCESS);
        productPageServiceImpl.delete(id);

        return result;
    }


    @RequestMapping("/addOrUpdate")
    @ResponseBody
    public Result addOrUpdate(@RequestBody ProductDataDto dto) {
        Result result = new Result();
        result.setCode(ResultCode.SUCCESS);
        logger.info(FastJsonUtil.toJSONString(dto));
        try {
            productPageServiceImpl.addOrUpdateProductByDto(dto);
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(ResultCode.INTERNAL_SERVER_ERROR);
        }


        return result;
    }

}
