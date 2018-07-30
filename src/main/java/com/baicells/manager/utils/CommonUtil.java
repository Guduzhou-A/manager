package com.baicells.manager.utils;


import org.apache.commons.lang3.StringUtils;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class CommonUtil {


	public static String getUUID(int length) {
		return UUID.randomUUID().toString().replaceAll("-","").substring(0,length);
	}


	/**
	 * 生成密码 md5(salt+md5(md5(password+username)))
	 * 
	 * @param salt
	 * @param pw
	 * @param username
	 * @return
	 */
	public static String getPassword(String salt, String pw,
                                     String username) {
		String md5First = Md5Util.getMD5(StringUtils.trim(pw) + StringUtils.trim(username));
		String md5Second = Md5Util.getMD5(StringUtils.lowerCase(md5First));
		return Md5Util.getMD5(pw + StringUtils.lowerCase(md5Second));
	}

	public static void main(String[] args){
	    try {
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
        }catch (Exception e){
e.printStackTrace();
        }

	}

	

}
