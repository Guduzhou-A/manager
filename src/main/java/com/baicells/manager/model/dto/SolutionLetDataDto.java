package com.baicells.manager.model.dto;

import java.io.Serializable;

public class SolutionLetDataDto implements Serializable {
    private Integer id;
    private String portalPicUrl;
    private String bgPicUrl;
    private String navPicUrl;
    private String title;
    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPortalPicUrl() {
        return portalPicUrl;
    }

    public void setPortalPicUrl(String portalPicUrl) {
        this.portalPicUrl = portalPicUrl;
    }

    public String getBgPicUrl() {
        return bgPicUrl;
    }

    public void setBgPicUrl(String bgPicUrl) {
        this.bgPicUrl = bgPicUrl;
    }

    public String getNavPicUrl() {
        return navPicUrl;
    }

    public void setNavPicUrl(String navPicUrl) {
        this.navPicUrl = navPicUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

