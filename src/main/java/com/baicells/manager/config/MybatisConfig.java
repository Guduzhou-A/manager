package com.baicells.manager.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Configuration;
import tk.mybatis.spring.annotation.MapperScan;


@Configuration
@MapperScan("com.baicells.*.mapper.**")
public class MybatisConfig    {

    private static Log logger = LogFactory.getLog(MybatisConfig.class);







}
