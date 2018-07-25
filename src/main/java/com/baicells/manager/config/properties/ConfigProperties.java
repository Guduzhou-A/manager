package com.baicells.manager.config.properties;

import com.baicells.manager.constant.ConfigConstant;
import com.baicells.manager.constant.GlobalConstant;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = {ConfigConstant.CLASSPATH_CONFIG_PATH, ConfigConstant.FILE_CONFIG_PATH, ConfigConstant.LOCATION_CONFIG_PATH}, encoding = GlobalConstant.ENCODEING, ignoreResourceNotFound = true)
@ConfigurationProperties
public class ConfigProperties {
    private String uploadUrl;
    private String nginxUrl;

    public String getUploadUrl() {
        return uploadUrl;
    }

    public void setUploadUrl(String uploadUrl) {
        this.uploadUrl = uploadUrl;
    }

    public String getNginxUrl() {
        return nginxUrl;
    }

    public void setNginxUrl(String nginxUrl) {
        this.nginxUrl = nginxUrl;
    }
}
