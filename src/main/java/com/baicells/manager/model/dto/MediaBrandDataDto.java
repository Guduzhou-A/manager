package com.baicells.manager.model.dto;

import java.io.Serializable;

public class MediaBrandDataDto implements Serializable {
    private String title;
    private String imgUrl;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}

