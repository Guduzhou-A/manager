package com.baicells.manager.web.controller;

import com.alibaba.fastjson.JSON;
import com.baicells.manager.base.properties.ProjectProperties;
import com.baicells.manager.config.properties.ConfigProperties;
import com.baicells.manager.exception.UserException;
import com.baicells.manager.model.entity.User;
import com.baicells.manager.service.IndexService;
import com.baicells.manager.service.UserService;
import com.baicells.manager.utils.FileUtil;
import com.baicells.manager.utils.Result;
import com.baicells.manager.utils.ResultCode;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("")
public class IndexController {
    private static Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Resource
    private ProjectProperties projectProperties;
    @Autowired
    private ConfigProperties configProperties;
    @Autowired
    private IndexService indexServiceImpl;
    @Autowired
    private UserService userServiceImpl;


    @RequestMapping("")
    public String index(HttpServletRequest request) {
        return "redirect:/home/banner";
    }

    @RequestMapping("editorUpload")
    @ResponseBody
    public Map<String,Object> editorUpload(MultipartFile file, HttpServletRequest request) {
        Map<String,Object> resultMap = new HashMap<>();
        try {
            final String imageDir = "images/";
            final String imagePath = configProperties.getUploadUrl() + imageDir;
            String dataPath = FileUtil.upload(file, imagePath, "editor_img");
            String nginxPath = configProperties.getNginxUploadUrl() + imageDir+ dataPath;
            List<String> urls = new ArrayList<>();
            urls.add(nginxPath);
            resultMap.put("data",urls);
            resultMap.put("errno",0);
        }catch (Exception e){
            resultMap.put("errno",500);
        }


        return resultMap;
    }


    @RequestMapping("upload")
    @ResponseBody
    public Result upload(MultipartFile file, String tag, HttpServletRequest request) {
        Result result = new Result();
        final String imageDir = "images/";
        final String imagePath = configProperties.getUploadUrl() + imageDir;
        String dataPath = FileUtil.upload(file, imagePath, tag);
        String nginxPath = configProperties.getNginxUploadUrl() + imageDir+ dataPath;
        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("url", nginxPath);
        result.setData(resultMap);
        return result;
    }

    @RequestMapping("uploadPDF")
    @ResponseBody
    public Result uploadPDF(MultipartFile pdf_file, String pdf_tag, HttpServletRequest request) {
        Result result = new Result();
        final String fileDir = "file/";
        final String pdfPath = configProperties.getUploadUrl() + fileDir;
        String dataPath = FileUtil.upload(pdf_file, pdfPath, pdf_tag);
        String nginxPath = configProperties.getNginxUploadUrl() + fileDir+ dataPath;
        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("url", nginxPath);
        result.setData(resultMap);
        return result;
    }


    @RequestMapping("updateUrl")
    @ResponseBody
    public Result updateUrl(String url , HttpServletRequest request) {
        Result result = new Result();
        result.setCode(ResultCode.SUCCESS);
        if (StringUtils.isBlank(url)){
            return result;
        }
        indexServiceImpl.updateUrl(url);
        return result;
    }


    @RequestMapping(value = "login",method = RequestMethod.GET)
    public String toLogin(HttpServletRequest request) {
        return "raw.baicells.index.login";
    }

    @RequestMapping(value = "login",method = RequestMethod.POST)
    @ResponseBody
    public Result login(String username,String password,HttpServletRequest request) {
        Result result = new Result();
        result.setCode(ResultCode.SUCCESS);
        User user = userServiceImpl.getByUsernameAndPass(username,password);
        if (null == user){
            result.setCode(ResultCode.FAIL);
        }else{
            request.getSession().setAttribute("currentUser",user);
        }

        return result;
    }

    @RequestMapping(value = "changeUser",method = RequestMethod.POST)
    @ResponseBody
    public Result changeUser(int id,String nickName,String newPass,String oldPass,HttpServletRequest request) {
        Result result = new Result();
        result.setCode(ResultCode.SUCCESS);

        try {
            userServiceImpl.updateUser(id,nickName,oldPass,newPass);
            request.getSession().removeAttribute("currentUser");
        } catch (UserException e) {
            result.setCode(ResultCode.FAIL);
            result.setMessage(e.getMessage());
            e.printStackTrace();
        }catch (Exception e){
            result.setCode(ResultCode.INTERNAL_SERVER_ERROR);
            result.setMessage("服务器错误，请联系管理员");
        }


        return result;
    }



    @RequestMapping(value = "logout",method = RequestMethod.GET)
    public String loginOut(HttpServletRequest request) {

        request.getSession().removeAttribute("currentUser");
        return "raw.baicells.index.login";
    }


}


