package com.baicells.manager.web.controller;


import com.baicells.manager.model.dto.*;
import com.baicells.manager.model.entity.*;
import com.baicells.manager.service.Solution5gService;
import com.baicells.manager.service.SolutionLETService;
import com.baicells.manager.utils.*;
//import org.mybatis.generator.api.MyBatisGenerator;
//import org.mybatis.generator.config.Configuration;
//import org.mybatis.generator.config.xml.ConfigurationParser;
//import org.mybatis.generator.internal.DefaultShellCallback;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/solution")
public class SolutionController {

    private static Logger logger = LoggerFactory.getLogger(SolutionController.class);

    @Autowired
    private SolutionLETService solutionLETServiceImpl;
    @Autowired
    private Solution5gService solution5gServiceImpl;


    @RequestMapping("/5g")
    public String index(HttpServletRequest request) {
        return "site.baicells.5g-solution.list";
    }

    @RequestMapping("/5g/ajax")
    @ResponseBody
    public Result ajax(String data) {
        Result result = new Result();
        result.setCode(ResultCode.SUCCESS);
        SolutionQuery4WebDto dto = ConvertQuery4WebData.convertSolutionQuery(data);
        PageInfo<Solution5gPage> solution5gPageInfo = solution5gServiceImpl.listByQuery(dto);
        DataTablesMap dataTablesMap = new DataTablesMap();
        dataTablesMap.setData(solution5gPageInfo.getList());
        dataTablesMap.setiTotalRecords(solution5gPageInfo.getPageNum());
        dataTablesMap.setiTotalDisplayRecords(solution5gPageInfo.getTotal());
        result.setData(dataTablesMap);
        return result;
    }

    @RequestMapping("5g/edit/{id}")
    @ResponseBody
    public Result editData(@PathVariable(name = "id") int id) {
        Result result = new Result();
        result.setCode(ResultCode.SUCCESS);
        Solution5gPage solution5gPage = solution5gServiceImpl.getById(id);
        if (null != solution5gPage){
            Solution5gDataDto dto = new Solution5gDataDto();
            dto.setId(solution5gPage.getId());
            dto.setBgPicUrl(solution5gPage.getBgPicUrl());
            dto.setContentBottom(solution5gPage.getContentBottom());
            dto.setContentDesc(solution5gPage.getContentDesc());
            dto.setContentTop(solution5gPage.getContentTop());
            dto.setMiddleBgUrl(solution5gPage.getMiddleBgPic());
            dto.setMiddlePic1(solution5gPage.getMiddlePic1());
            dto.setMiddlePic2(solution5gPage.getMiddlePic2());
            dto.setMiddlePic3(solution5gPage.getMiddlePic3());
            dto.setMiddleTitle(solution5gPage.getMiddleTitle());
            dto.setMiddleTitle1(solution5gPage.getMiddleText1());
            dto.setMiddleTitle2(solution5gPage.getMiddleText2());
            dto.setMiddleTitle3(solution5gPage.getMiddleText3());
            dto.setNavDesc(solution5gPage.getNavDesc());
            dto.setNavPicUrl(solution5gPage.getNavPicUrl());
            dto.setTitle(solution5gPage.getTitle());
            result.setData(dto);
        }


        return result;
    }

    @RequestMapping("/5g/editStatus/{id}")
    @ResponseBody
    public Result editStatus(@PathVariable(name = "id") int id) {
        Result result = new Result();
        result.setCode(ResultCode.SUCCESS);
        Solution5gPage solution5gPage = solution5gServiceImpl.getById(id);
        if (null != solution5gPage) {
            solution5gPage.setEnable(!solution5gPage.getEnable());
            solution5gPage.setUpdateTime(DateUtils.now());
            solution5gServiceImpl.updateById(solution5gPage);
        }
        return result;
    }

    @RequestMapping("/5g/delete/{id}")
    @ResponseBody
    public Result delete(@PathVariable(name = "id") int id) {
        Result result = new Result();
        result.setCode(ResultCode.SUCCESS);
        solution5gServiceImpl.delete(id);

        return result;
    }


    @RequestMapping("/5g/addOrUpdate")
    @ResponseBody
    public Result addOrUpdate(@RequestBody Solution5gDataDto dto) {
        Result result = new Result();
        result.setCode(ResultCode.SUCCESS);
        logger.info(FastJsonUtil.toJSONString(dto));
        try {
            solution5gServiceImpl.addOrUpdateByDto(dto);
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(ResultCode.INTERNAL_SERVER_ERROR);
        }


        return result;
    }



    @RequestMapping("/let")
    public String indexLet(HttpServletRequest request) {
        return "site.baicells.let-solution.list";
    }

    @RequestMapping("/let/ajax")
    @ResponseBody
    public Result ajaxLet(String data) {
        Result result = new Result();
        result.setCode(ResultCode.SUCCESS);
        SolutionQuery4WebDto dto = ConvertQuery4WebData.convertSolutionQuery(data);
        PageInfo<SolutionLetPage> solutionLetPagePageInfo = solutionLETServiceImpl.listByQuery(dto);
        DataTablesMap dataTablesMap = new DataTablesMap();
        dataTablesMap.setData(solutionLetPagePageInfo.getList());
        dataTablesMap.setiTotalRecords(solutionLetPagePageInfo.getPageNum());
        dataTablesMap.setiTotalDisplayRecords(solutionLetPagePageInfo.getTotal());
        result.setData(dataTablesMap);
        return result;
    }

    @RequestMapping("let/edit/{id}")
    @ResponseBody
    public Result editDataLet(@PathVariable(name = "id") int id) {
        Result result = new Result();
        result.setCode(ResultCode.SUCCESS);
        SolutionLetPage solutionLetPage = solutionLETServiceImpl.getById(id);
        if (null != solutionLetPage){
            SolutionLetDataDto dto = new SolutionLetDataDto();
            dto.setId(solutionLetPage.getId());
            dto.setBgPicUrl(solutionLetPage.getBgPicUrl());
            dto.setNavPicUrl(solutionLetPage.getNavPicUrl());
            dto.setTitle(solutionLetPage.getTitle());
            dto.setContent(solutionLetPage.getContent());
            result.setData(dto);
        }


        return result;
    }

    @RequestMapping("/let/editStatus/{id}")
    @ResponseBody
    public Result editStatusLet(@PathVariable(name = "id") int id) {
        Result result = new Result();
        result.setCode(ResultCode.SUCCESS);
        SolutionLetPage solutionLetPage = solutionLETServiceImpl.getById(id);
        if (null != solutionLetPage) {
            solutionLetPage.setEnable(!solutionLetPage.getEnable());
            solutionLetPage.setUpdateTime(DateUtils.now());
            solutionLETServiceImpl.updateById(solutionLetPage);
        }
        return result;
    }

    @RequestMapping("/let/delete/{id}")
    @ResponseBody
    public Result deleteLet(@PathVariable(name = "id") int id) {
        Result result = new Result();
        result.setCode(ResultCode.SUCCESS);
        solutionLETServiceImpl.delete(id);

        return result;
    }


    @RequestMapping("/let/addOrUpdate")
    @ResponseBody
    public Result addOrUpdateLet(@RequestBody SolutionLetDataDto dto) {
        Result result = new Result();
        result.setCode(ResultCode.SUCCESS);
        logger.info(FastJsonUtil.toJSONString(dto));
        try {
            solutionLETServiceImpl.addOrUpdateByDto(dto);
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(ResultCode.INTERNAL_SERVER_ERROR);
        }


        return result;
    }


}
