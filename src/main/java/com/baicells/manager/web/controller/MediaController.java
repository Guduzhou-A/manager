package com.baicells.manager.web.controller;


import com.baicells.manager.model.dto.*;
import com.baicells.manager.model.entity.*;
import com.baicells.manager.service.*;
import com.baicells.manager.utils.*;
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
@RequestMapping("/media")
public class MediaController {

    private static Logger logger = LoggerFactory.getLogger(MediaController.class);

    @Autowired
    private MediaNewsService mediaNewsServiceImpl;
    @Autowired
    private MediaCustomerService mediaCustomerServiceImpl;
    @Autowired
    private MediaBrandService mediaBrandServiceImpl;


    @RequestMapping("/news")
    public String index(HttpServletRequest request) {
        return "site.baicells.media.news";
    }

    @RequestMapping("/news/ajax")
    @ResponseBody
    public Result ajax(String data) {
        Result result = new Result();
        result.setCode(ResultCode.SUCCESS);
        MediaQuery4WebDto dto = ConvertQuery4WebData.convertMediaQuery(data);
        PageInfo<MediaNews> mediaNewsPageInfo = mediaNewsServiceImpl.listByQuery(dto);
        DataTablesMap dataTablesMap = new DataTablesMap();
        dataTablesMap.setData(mediaNewsPageInfo.getList());
        dataTablesMap.setiTotalRecords(mediaNewsPageInfo.getPageNum());
        dataTablesMap.setiTotalDisplayRecords(mediaNewsPageInfo.getTotal());
        result.setData(dataTablesMap);
        return result;
    }


    @RequestMapping("/news/delete/{id}")
    @ResponseBody
    public Result delete(@PathVariable(name = "id") int id) {
        Result result = new Result();
        result.setCode(ResultCode.SUCCESS);
        mediaNewsServiceImpl.delete(id);

        return result;
    }


    @RequestMapping("/news/addOrUpdate")
    @ResponseBody
    public Result addOrUpdate(@RequestBody MediaNewsDataDto dto) {
        Result result = new Result();
        result.setCode(ResultCode.SUCCESS);
        logger.info(FastJsonUtil.toJSONString(dto));
        try {
            mediaNewsServiceImpl.addOrUpdateByDto(dto);
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(ResultCode.INTERNAL_SERVER_ERROR);
        }


        return result;
    }

    @RequestMapping("/news/edit/{id}")
    @ResponseBody
    public Result editDataNews(@PathVariable(name = "id") int id) {
        Result result = new Result();
        result.setCode(ResultCode.SUCCESS);
        MediaNews mediaNews = mediaNewsServiceImpl.getById(id);
        if (null != mediaNews) {
            MediaNewsDataDto dto = new MediaNewsDataDto();
            dto.setId(mediaNews.getId());
            dto.setNavPicUrl(mediaNews.getNavPicUrl());
            dto.setNavTitle(mediaNews.getNavTitle());
            dto.setNavDesc(mediaNews.getNavDesc());
            dto.setContent(mediaNews.getContent());
            result.setData(dto);
        }


        return result;
    }


    @RequestMapping("/customer")
    public String customerIndex(HttpServletRequest request) {
        return "site.baicells.media.customer";
    }

    @RequestMapping("/customer/ajax")
    @ResponseBody
    public Result customerAjax(String data) {
        Result result = new Result();
        result.setCode(ResultCode.SUCCESS);
        MediaQuery4WebDto dto = ConvertQuery4WebData.convertMediaQuery(data);
        PageInfo<MediaCustomer> mediaCustomerPageInfo = mediaCustomerServiceImpl.listByQuery(dto);
        DataTablesMap dataTablesMap = new DataTablesMap();
        dataTablesMap.setData(mediaCustomerPageInfo.getList());
        dataTablesMap.setiTotalRecords(mediaCustomerPageInfo.getPageNum());
        dataTablesMap.setiTotalDisplayRecords(mediaCustomerPageInfo.getTotal());
        result.setData(dataTablesMap);
        return result;
    }


    @RequestMapping("/customer/delete/{id}")
    @ResponseBody
    public Result customerDelete(@PathVariable(name = "id") int id) {
        Result result = new Result();
        result.setCode(ResultCode.SUCCESS);
        mediaCustomerServiceImpl.delete(id);

        return result;
    }


    @RequestMapping("/customer/addOrUpdate")
    @ResponseBody
    public Result customerAddOrUpdate(@RequestBody MediaCustomerDataDto dto) {
        Result result = new Result();
        result.setCode(ResultCode.SUCCESS);
        logger.info(FastJsonUtil.toJSONString(dto));
        try {
            mediaCustomerServiceImpl.addOrUpdateByDto(dto);
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(ResultCode.INTERNAL_SERVER_ERROR);
        }


        return result;
    }

    @RequestMapping("/customer/edit/{id}")
    @ResponseBody
    public Result customerEditDataNews(@PathVariable(name = "id") int id) {
        Result result = new Result();
        result.setCode(ResultCode.SUCCESS);
        MediaCustomer mediaCustomer = mediaCustomerServiceImpl.getById(id);
        if (null != mediaCustomer) {
            MediaCustomerDataDto dto = new MediaCustomerDataDto();
            dto.setId(mediaCustomer.getId());
            dto.setNavPicUrl(mediaCustomer.getNavPicUrl());
            dto.setNavTitle(mediaCustomer.getNavTitle());
            dto.setNavDesc(mediaCustomer.getNavDesc());
            dto.setFileUrl(mediaCustomer.getFileUrl());
            result.setData(dto);
        }


        return result;
    }



    @RequestMapping("/brand")
    public String brandIndex(HttpServletRequest request) {
        return "site.baicells.media.brand";
    }

    @RequestMapping("/brand/ajax")
    @ResponseBody
    public Result brandAjax(String data) {
        Result result = new Result();
        result.setCode(ResultCode.SUCCESS);
        MediaQuery4WebDto dto = ConvertQuery4WebData.convertMediaQuery(data);
        PageInfo<MediaBrand> mediaBrandPageInfo = mediaBrandServiceImpl.listByQuery(dto);
        DataTablesMap dataTablesMap = new DataTablesMap();
        dataTablesMap.setData(mediaBrandPageInfo.getList());
        dataTablesMap.setiTotalRecords(mediaBrandPageInfo.getPageNum());
        dataTablesMap.setiTotalDisplayRecords(mediaBrandPageInfo.getTotal());
        result.setData(dataTablesMap);
        return result;
    }


    @RequestMapping("/brand/add")
    @ResponseBody
    public Result brandAdd(@RequestBody MediaBrandDataDto dto) {
        Result result = new Result();
        result.setCode(ResultCode.SUCCESS);
        logger.info(FastJsonUtil.toJSONString(dto));
        try {
            mediaBrandServiceImpl.addByDto(dto);
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
        mediaBrandServiceImpl.delete(id);

        return result;
    }



}
