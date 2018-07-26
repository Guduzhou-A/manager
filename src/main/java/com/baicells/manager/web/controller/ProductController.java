package com.baicells.manager.web.controller;


import com.baicells.manager.model.dto.ConvertQuery4WebData;
import com.baicells.manager.model.dto.ProductPageQuery4WebDto;
import com.baicells.manager.model.entity.ProductPage;
import com.baicells.manager.service.ProductPageService;
import com.baicells.manager.utils.DataTablesMap;
import com.baicells.manager.utils.FastJsonUtil;
import com.baicells.manager.utils.Result;
import com.baicells.manager.utils.ResultCode;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/product")
public class ProductController {

    private static Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductPageService productPageServiceImpl;


    @RequestMapping("/list")
    public String index(HttpServletRequest request) {

        return "site.baicells.product.list";
    }

    @RequestMapping("/ajax")
    @ResponseBody
    public Result ajax(String data) {
        Result Result = new Result();
        Result.setCode(ResultCode.SUCCESS);
        ProductPageQuery4WebDto dto = ConvertQuery4WebData.convertCommentQuery(data);
        PageInfo<ProductPage> pagePageInfo = productPageServiceImpl.listByQuery(dto);
        DataTablesMap dataTablesMap = new DataTablesMap();
        dataTablesMap.setData(pagePageInfo.getList());
        dataTablesMap.setiTotalRecords(pagePageInfo.getPageNum());
        dataTablesMap.setiTotalDisplayRecords(pagePageInfo.getTotal());

        Result.setData(dataTablesMap);
        return Result;
    }

    @RequestMapping("/addP")
    @ResponseBody
    public Result add(@RequestBody Map param) {
        Result Result = new Result();
        Result.setCode(ResultCode.SUCCESS);
        logger.info(FastJsonUtil.toJSONString(param));
        return Result;
    }

}
