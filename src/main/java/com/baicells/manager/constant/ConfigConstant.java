package com.baicells.manager.constant;

/**
 * 各个配置所需常量
 */
public class ConfigConstant {

    public static final String ROOT_PREFIX = "baicells";

    public static final String PROJECT_PREFIX = "project";
    //freemarker config
    public static final String TILES_JSP_TLD_PATH = "/static/tags/tiles2-jsp.tld";
    public static final String TILES_GENERAL_CLASS_PATH ="classpath:tiles-general.xml";


    //config
    //按照优先级逐级
    public static final String CLASSPATH_CONFIG_PATH = "classpath:config.yml";
    public static final String FILE_CONFIG_PATH = "file:./config.yml";
    public static final String LOCATION_CONFIG_PATH = "file:${config-location.baicells}";


}
