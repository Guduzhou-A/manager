package com.baicells.manager.web.controller;


import com.baicells.manager.model.dto.*;
import com.baicells.manager.model.entity.Solution5gPage;
import com.baicells.manager.model.entity.SupportDownload;
import com.baicells.manager.service.SupportDownloadService;
import com.baicells.manager.utils.DataTablesMap;
import com.baicells.manager.utils.FastJsonUtil;
import com.baicells.manager.utils.Result;
import com.baicells.manager.utils.ResultCode;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/support")
public class SupportController {

    private static Logger logger = LoggerFactory.getLogger(SupportController.class);

    @Autowired
    private SupportDownloadService supportDownloadServiceImpl;


    @RequestMapping("/downloads")
    public String index(HttpServletRequest request) {
        return "site.baicells.support.downloads";
    }

    @RequestMapping("/downloads/ajax")
    @ResponseBody
    public Result ajax(String data) {
        Result result = new Result();
        result.setCode(ResultCode.SUCCESS);
        SupportQuery4WebDto dto = ConvertQuery4WebData.convertSupportQuery(data);
        PageInfo<SupportDownload> supportDownloadPageInfo = supportDownloadServiceImpl.listByQuery(dto);
        DataTablesMap dataTablesMap = new DataTablesMap();
        dataTablesMap.setData(supportDownloadPageInfo.getList());
        dataTablesMap.setiTotalRecords(supportDownloadPageInfo.getPageNum());
        dataTablesMap.setiTotalDisplayRecords(supportDownloadPageInfo.getTotal());
        result.setData(dataTablesMap);
        return result;
    }

    @RequestMapping("downloads/edit/{id}")
    @ResponseBody
    public Result editData(@PathVariable(name = "id") int id) {
        Result result = new Result();
        result.setCode(ResultCode.SUCCESS);
        SupportDownload supportDownload = supportDownloadServiceImpl.getById(id);
        if (null != supportDownload){
            SupportDownloadDataDto dto = new SupportDownloadDataDto();
            dto.setId(supportDownload.getId());
            dto.setTitle(supportDownload.getTitle());
            dto.setType(supportDownload.getType());
            dto.setUrl(supportDownload.getUrl());
            result.setData(dto);
        }


        return result;
    }


    @RequestMapping("/downloads/delete/{id}")
    @ResponseBody
    public Result delete(@PathVariable(name = "id") int id) {
        Result result = new Result();
        result.setCode(ResultCode.SUCCESS);
        supportDownloadServiceImpl.delete(id);

        return result;
    }


    @RequestMapping("/downloads/addOrUpdate")
    @ResponseBody
    public Result addOrUpdate(@RequestBody SupportDownloadDataDto dto) {
        Result result = new Result();
        result.setCode(ResultCode.SUCCESS);
        logger.info(FastJsonUtil.toJSONString(dto));
        try {
            supportDownloadServiceImpl.addOrUpdateByDto(dto);
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(ResultCode.INTERNAL_SERVER_ERROR);
        }


        return result;
    }



}
