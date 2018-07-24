package com.baicells.manager.web.controller;


import com.baicells.manager.utils.Result;
import com.baicells.manager.utils.ResultCode;
import com.baicells.manager.utils.ResultMessage;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
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


    @RequestMapping("/5g")
    public String index(HttpServletRequest request) {
        return "site.baicells.5g-solution.list";
    }
    @RequestMapping("/5g/ajax")
    @ResponseBody
    public Result ajax() {
        Result result = new Result();
        result.setCode(ResultCode.SUCCESS);
        result.setMessage(ResultMessage.SUCCESS.messagae());

        List<Map<String,Object>> datas = new ArrayList<>();
        int i = 0;
        while (i < 10 ){
            Map<String,Object> data = new HashMap<>();
            data.put("id",i);
            data.put("title","test--"+ (i+1));
            datas.add(data);
            i++;
        }


        result.setData(datas);

        return result;
    }

    public static void main(String[] args) throws Exception {
        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Resource resource = new ClassPathResource("generatorConfig.xml");
        Configuration config =
                cp.parseConfiguration(resource.getFile());
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
        for (String warning : warnings) {
            System.out.println(warning);
        }
    }
}
