package com.baicells.manager.base.properties;

import com.baicells.manager.constant.ConfigConstant;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = ConfigConstant.PROJECT_PREFIX,ignoreInvalidFields = true)
public class ProjectProperties {
    private String name;
    private String title;
    private String staticDomain;
    private String version;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStaticDomain() {
        return staticDomain;
    }

    public void setStaticDomain(String staticDomain) {
        this.staticDomain = staticDomain;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
