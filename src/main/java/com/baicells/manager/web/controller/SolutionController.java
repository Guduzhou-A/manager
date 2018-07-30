package com.baicells.manager.web.controller;


import com.baicells.manager.model.dto.*;
import com.baicells.manager.model.entity.ProductPage;
import com.baicells.manager.model.entity.Solution5gPage;
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

    @RequestMapping("/5g/editStatus/{id}")
    @ResponseBody
    public Result editStatus(@PathVariable(name = "id") int id) {
        Result result = new Result();
        result.setCode(ResultCode.SUCCESS);
        System.out.println(id);
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


}
