package com.baicells.manager.web.controller;


import com.baicells.manager.model.dto.*;
import com.baicells.manager.model.entity.*;
import com.baicells.manager.service.*;
import com.baicells.manager.utils.*;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
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

@Controller
@RequestMapping("/about")
public class AboutController {

    private static Logger logger = LoggerFactory.getLogger(AboutController.class);

    @Autowired
    private AboutInfoService aboutInfoServiceImpl;
    @Autowired
    private DownloadService downloadServiceImpl;


    @RequestMapping("/list")
    public String index(HttpServletRequest request) {
//        request.setAttribute("countryMap",LanguageUtil.map);
        String url = downloadServiceImpl.getOne();
        if (StringUtils.isNotBlank(url)) {
            String file = url.substring(url.lastIndexOf("/") + 1, url.length());
            request.setAttribute("file", file);
        }


        return "site.baicells.about.list";
    }

    @RequestMapping("/ajax")
    @ResponseBody
    public Result ajax(String data) {
        Result result = new Result();
        result.setCode(ResultCode.SUCCESS);
        AboutPageQuery4WebDto dto = ConvertQuery4WebData.convertAboutQuery(data);
        PageInfo<AboutInfo> aboutInfoPageInfo = aboutInfoServiceImpl.listByQuery(dto);
        DataTablesMap dataTablesMap = new DataTablesMap();
        dataTablesMap.setData(aboutInfoPageInfo.getList());
        dataTablesMap.setiTotalRecords(aboutInfoPageInfo.getPageNum());
        dataTablesMap.setiTotalDisplayRecords(aboutInfoPageInfo.getTotal());

        result.setData(dataTablesMap);
        return result;
    }


    @RequestMapping("/edit/{id}")
    @ResponseBody
    public Result editData(@PathVariable(name = "id") int id) {
        Result result = new Result();
        result.setCode(ResultCode.SUCCESS);
        AboutInfo aboutInfo = aboutInfoServiceImpl.getById(id);
        if (aboutInfo != null) {
            result.setData(aboutInfo);
        } else {
            result.setCode(ResultCode.FAIL);
        }

        return result;
    }

    @RequestMapping("/delete/{id}")
    @ResponseBody
    public Result deleteData(@PathVariable(name = "id") int id) {
        Result result = new Result();
        result.setCode(ResultCode.SUCCESS);
        aboutInfoServiceImpl.deleteDataById(id);


        return result;
    }


    @RequestMapping("/addFile")
    @ResponseBody
    public Result addFile(String url) {
        Result result = new Result();
        result.setCode(ResultCode.SUCCESS);
//        aboutInfoServiceImpl.deleteDataById(id);
        downloadServiceImpl.add(url);

        return result;
    }


}
