package com.baicells.manager.web.controller;

import com.alibaba.fastjson.JSON;
import com.baicells.manager.base.properties.ProjectProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("")
public class IndexController {
	private static Logger logger = LoggerFactory.getLogger(IndexController.class);

	@Resource
	private ProjectProperties projectProperties;


	@RequestMapping("")
	public String index(HttpServletRequest request) {
		logger.info(JSON.toJSONString(projectProperties.getName()));
		return "site.baicells.index.index";
	}

}


