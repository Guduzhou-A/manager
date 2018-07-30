package com.baicells.manager.model.dto;

import java.io.Serializable;

public class MediaCustomerDataDto implements Serializable {
    private Integer id;
    private String navPicUrl;
    private String navTitle;
    private String navDesc;

    private String fileUrl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNavPicUrl() {
        return navPicUrl;
    }

    public void setNavPicUrl(String navPicUrl) {
        this.navPicUrl = navPicUrl;
    }

    public String getNavTitle() {
        return navTitle;
    }

    public void setNavTitle(String navTitle) {
        this.navTitle = navTitle;
    }

    public String getNavDesc() {
        return navDesc;
    }

    public void setNavDesc(String navDesc) {
        this.navDesc = navDesc;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }
}

