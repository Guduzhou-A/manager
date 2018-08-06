package com.baicells.manager.utils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.Map;

public class CreateHtmlUtils {


    public static void createHtml(String path, String templateName, String targetFileName, Object object,String oldUrl) throws Exception {
        if (StringUtils.isNotBlank(oldUrl)){
            String oldPath = path+"/"+oldUrl.substring(oldUrl.lastIndexOf("/")+1,oldUrl.length());

            File oldFile = new File(oldPath);
            if (oldFile.exists()){
                oldFile.delete();
            }
        }
        //创建fm的配置
        Configuration config = new Configuration();
        //指定默认编码格式
        config.setDefaultEncoding("UTF-8");
        //设置模版文件的路径
        config.setClassForTemplateLoading(CreateHtmlUtils.class, "/htmlTemplates/");
        //获得模版包
        Template template = config.getTemplate(templateName);
        //从参数文件中获取指定输出路径 ,路径示例：C:/Workspace/shop-test/src/main/webapp/html
        File dir = new File(path);
        if (!dir.exists() && !dir.isDirectory()) {//判断文件目录是否存在
            dir.mkdirs();
        }
        File file = new File(path + "/" + targetFileName + ".html");

        //定义输出流，注意必须指定编码
        Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
        //生成模版
        template.process(object, writer);
        writer.close();

    }
}
