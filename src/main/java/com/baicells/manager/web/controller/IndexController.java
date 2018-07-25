package com.baicells.manager.web.controller;

import com.alibaba.fastjson.JSON;
import com.baicells.manager.base.properties.ProjectProperties;
import com.baicells.manager.config.properties.ConfigProperties;
import com.baicells.manager.utils.FileUtil;
import com.baicells.manager.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("")
public class IndexController {
    private static Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Resource
    private ProjectProperties projectProperties;
    @Autowired
    private ConfigProperties configProperties;


    @RequestMapping("")
    public String index(HttpServletRequest request) {
        logger.info(JSON.toJSONString(projectProperties.getName()));
        return "site.baicells.index.index";
    }

    @RequestMapping("upload")
    @ResponseBody
    public Result upload(MultipartFile file, String tag, HttpServletRequest request) {
        Result result = new Result();
        String dataPath = FileUtil.upload(file, configProperties.getUploadUrl(), tag);
        String nginxPath =configProperties.getNginxUrl()+dataPath;
        Map<String,String> resultMap = new HashMap<>();
        resultMap.put("file",dataPath);
        resultMap.put("nginx",nginxPath);
        result.setData(resultMap);
        return result;
    }


}


