package com.baicells.manager.config.properties;

import com.baicells.manager.constant.ConfigConstant;
import com.baicells.manager.constant.GlobalConstant;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = {ConfigConstant.CLASSPATH_CONFIG_PATH, ConfigConstant.FILE_CONFIG_PATH, ConfigConstant.FILE_CONFIG_PATH_SECOND,ConfigConstant.LOCATION_CONFIG_PATH}, encoding = GlobalConstant.ENCODEING, ignoreResourceNotFound = true)
@ConfigurationProperties
public class ConfigProperties {
    private String uploadUrl;
    private String nginxUploadUrl;
    private String webRoot;
    private String nginxWebRoot;

    public String getUploadUrl() {
        return uploadUrl;
    }

    public void setUploadUrl(String uploadUrl) {
        this.uploadUrl = uploadUrl;
    }

    public String getNginxUploadUrl() {
        return nginxUploadUrl;
    }

    public void setNginxUploadUrl(String nginxUploadUrl) {
        this.nginxUploadUrl = nginxUploadUrl;
    }

    public String getWebRoot() {
        return webRoot;
    }

    public void setWebRoot(String webRoot) {
        this.webRoot = webRoot;
    }

    public String getNginxWebRoot() {
        return nginxWebRoot;
    }

    public void setNginxWebRoot(String nginxWebRoot) {
        this.nginxWebRoot = nginxWebRoot;
    }
}
