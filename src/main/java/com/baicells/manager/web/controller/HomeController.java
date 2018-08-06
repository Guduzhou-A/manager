package com.baicells.manager.web.controller;


import com.baicells.manager.model.dto.*;
import com.baicells.manager.model.entity.*;
import com.baicells.manager.service.*;
import com.baicells.manager.utils.DataTablesMap;
import com.baicells.manager.utils.FastJsonUtil;
import com.baicells.manager.utils.Result;
import com.baicells.manager.utils.ResultCode;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

//import org.mybatis.generator.api.MyBatisGenerator;
//import org.mybatis.generator.config.Configuration;
//import org.mybatis.generator.config.xml.ConfigurationParser;
//import org.mybatis.generator.internal.DefaultShellCallback;

@Controller
@RequestMapping("/home")
public class HomeController {

    private static Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private HomeBannerService homeBannerServiceImpl;

    @Autowired
    private HomerBrandService homerBrandServiceImpl;


    @RequestMapping("/banner")
    public String index(HttpServletRequest request) {
        return "site.baicells.home.banner";
    }


    @RequestMapping("/banner/ajax")
    @ResponseBody
    public Result bannerAjax(String data) {
        Result result = new Result();
        result.setCode(ResultCode.SUCCESS);
        HomeQuery4WebDto dto = ConvertQuery4WebData.convertHomeQuery(data);
        PageInfo<HomeBanner> homeBannerPageInfo = homeBannerServiceImpl.listByQuery(dto);
        DataTablesMap dataTablesMap = new DataTablesMap();
        dataTablesMap.setData(homeBannerPageInfo.getList());
        dataTablesMap.setiTotalRecords(homeBannerPageInfo.getPageNum());
        dataTablesMap.setiTotalDisplayRecords(homeBannerPageInfo.getTotal());
        result.setData(dataTablesMap);
        return result;
    }


    @RequestMapping("/banner/add")
    @ResponseBody
    public Result bannerAdd(@RequestBody HomeBannerDataDto dto) {
        Result result = new Result();
        result.setCode(ResultCode.SUCCESS);
        logger.info(FastJsonUtil.toJSONString(dto));
        try {
            homeBannerServiceImpl.addByDto(dto);
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(ResultCode.INTERNAL_SERVER_ERROR);
        }


        return result;
    }



    @RequestMapping("/banner/delete/{id}")
    @ResponseBody
    public Result bannerDelete(@PathVariable(name = "id") int id) {
        Result result = new Result();
        result.setCode(ResultCode.SUCCESS);
        homeBannerServiceImpl.delete(id);

        return result;
    }


    @RequestMapping("/brand")
    public String brandIndex(HttpServletRequest request) {
        return "site.baicells.home.brand";
    }


    @RequestMapping("/brand/ajax")
    @ResponseBody
    public Result brandAjax(String data) {
        Result result = new Result();
        result.setCode(ResultCode.SUCCESS);
        HomeQuery4WebDto dto = ConvertQuery4WebData.convertHomeQuery(data);
        PageInfo<HomeBrand> homeBrandPageInfo = homerBrandServiceImpl.listByQuery(dto);
        DataTablesMap dataTablesMap = new DataTablesMap();
        dataTablesMap.setData(homeBrandPageInfo.getList());
        dataTablesMap.setiTotalRecords(homeBrandPageInfo.getPageNum());
        dataTablesMap.setiTotalDisplayRecords(homeBrandPageInfo.getTotal());
        result.setData(dataTablesMap);
        return result;
    }


    @RequestMapping("/brand/add")
    @ResponseBody
    public Result brandAdd(@RequestBody HomeBannerDataDto dto) {
        Result result = new Result();
        result.setCode(ResultCode.SUCCESS);
        logger.info(FastJsonUtil.toJSONString(dto));
        try {
            homerBrandServiceImpl.addByDto(dto);
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(ResultCode.INTERNAL_SERVER_ERROR);
        }


        return result;
    }



    @RequestMapping("/brand/delete/{id}")
    @ResponseBody
    public Result brandDelete(@PathVariable(name = "id") int id) {
        Result result = new Result();
        result.setCode(ResultCode.SUCCESS);
        homerBrandServiceImpl.delete(id);

        return result;
    }

    @RequestMapping("/brand/status/{id}")
    @ResponseBody
    public Result updateBrandStatus(@PathVariable(name = "id") int id) {
        Result result = new Result();
        result.setCode(ResultCode.SUCCESS);
        homerBrandServiceImpl.updateBrandStatus(id);

        return result;
    }

}
