package com.baicells.manager.web.controller;

import com.baicells.manager.model.entity.ProductPage;
import com.baicells.manager.model.entity.Solution5gPage;
import com.baicells.manager.model.entity.SolutionLetPage;
import com.baicells.manager.model.entity.SysPageConfig;
import com.baicells.manager.service.ProductPageService;
import com.baicells.manager.service.Solution5gService;
import com.baicells.manager.service.SolutionLETService;
import com.baicells.manager.service.SystemService;
import com.baicells.manager.utils.Result;
import com.baicells.manager.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/system")
public class SysController {
    @Autowired
    private SystemService systemServiceImpl;


    @RequestMapping("solution/5g")
    public String solution5g(HttpServletRequest request, HttpServletResponse response) {

        SysPageConfig sysPageConfig = systemServiceImpl.execGetOne();
        request.setAttribute("data", sysPageConfig);

        return "site.baicells.system.solution-5g";
    }


    @RequestMapping("solution/5g/update")
    @ResponseBody
    public Result updateSolution5g(String content) {
        Result result = new Result();
        result.setCode(ResultCode.SUCCESS);
        try {

            systemServiceImpl.update5gContent(content);
        } catch (Exception e) {
            result.setCode(ResultCode.INTERNAL_SERVER_ERROR);
        }
        return result;
    }

}
