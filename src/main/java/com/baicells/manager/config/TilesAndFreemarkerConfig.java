package com.baicells.manager.config;

import com.alibaba.fastjson.JSON;
import com.baicells.manager.constant.ConfigConstant;
import com.baicells.manager.constant.GlobalConstant;
import freemarker.ext.servlet.FreemarkerServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class TilesAndFreemarkerConfig {
    private static final Logger log = LoggerFactory.getLogger(TilesAndFreemarkerConfig.class);
    private String templateLoaderPath = "classpath:/templates/";

    //    config
    private static final String INIT_PARAM_DEBUG = "n";
//    private static final String INIT_PARAM_LOCALE = "zh_CN";
//    private static final String INIT_PARAM_URL_ESCAPING_CHARSET = "UTF-8";
//    private static final String INIT_PARAM_WHITESPACE_STRIPPING = "true";
//    private static final String INIT_PARAM_TEMPLATE_UPDATE_DELAY = "0";

    @Autowired
    private FreeMarkerProperties freeMarkerProperties;



    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        log.info(JSON.toJSONString(freeMarkerProperties));
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean();
        servletRegistrationBean.setServlet(new FreemarkerServlet());
        List urls = new ArrayList();
        urls.add("*.ftl");
        servletRegistrationBean.setUrlMappings(urls);
        servletRegistrationBean.setInitParameters(getFreeMarkerInitParams());
        servletRegistrationBean.setLoadOnStartup(2);
        return servletRegistrationBean;
    }

    private Map<String, String> getFreeMarkerInitParams() {
        Map<String, String> initParams = new HashMap<>();
        initParams.put("NoCache", freeMarkerProperties.isCache() ? GlobalConstant.STR_TRUE : GlobalConstant.STR_FALSE);
        String templatePath;
        try {
            templatePath = "";
            for (int i = 0; i < freeMarkerProperties.getTemplateLoaderPath().length; i++) {
                templatePath += freeMarkerProperties.getTemplateLoaderPath()[i];
                if (i != freeMarkerProperties.getTemplateLoaderPath().length - 1) {
                    templatePath += GlobalConstant.Symbol.COMMA;
                }
            }
        } catch (Exception e) {
            templatePath = templateLoaderPath;
        }
        initParams.put("TemplatePath", templatePath);
        initParams.put("ContentType", freeMarkerProperties.getContentType().toString());
        initParams.put("default_encoding", freeMarkerProperties.getCharsetName());
        initParams.put("number_format", freeMarkerProperties.getSettings().get("number_format"));
        initParams.put("date_format", freeMarkerProperties.getSettings().get("date_format"));
        initParams.put("template_update_delay", freeMarkerProperties.getSettings().get("template_update_delay"));
        initParams.put("locale", freeMarkerProperties.getSettings().get("locale"));
        initParams.put("url_escaping_charset", freeMarkerProperties.getSettings().get("url_escaping_charset"));
        initParams.put("whitespace_stripping", freeMarkerProperties.getSettings().get("whitespace_stripping"));
        initParams.put("Debug", INIT_PARAM_DEBUG);
        initParams.put("ClasspathTlds", ConfigConstant.TILES_JSP_TLD_PATH);
        return initParams;
    }


    @Bean
    public TilesConfigurer tilesConfigurer() {
        TilesConfigurer configurer = new TilesConfigurer();
        List<String> definitions = new ArrayList<>();
        definitions.add(ConfigConstant.TILES_GENERAL_CLASS_PATH);
        configurer.setDefinitions(definitions.toArray(new String[definitions.size()]));
        return configurer;
    }

    @Bean
    public UrlBasedViewResolver urlBasedViewResolver() {
        UrlBasedViewResolver viewResolver = new UrlBasedViewResolver();
        viewResolver.setOrder(1);
        viewResolver.setViewClass(TilesView.class);
        return viewResolver;
    }

}
