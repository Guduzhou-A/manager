package com.baicells.manager.model.dto;

import java.io.Serializable;
import java.util.List;

public class ProductDataDto implements Serializable {
    private Integer id;
    private String portalPicUrl;
    private String bgPicUrl;
    private String navPicUrl;
    private String title;
    private boolean isTwoContent;
    private List<ProductGroupDto> productGroups;
    private List<ProductContentDto> contents;

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


    public boolean isTwoContent() {
        return isTwoContent;
    }

    public void setTwoContent(boolean twoContent) {
        isTwoContent = twoContent;
    }

    public List<ProductGroupDto> getProductGroups() {
        return productGroups;
    }

    public void setProductGroups(List<ProductGroupDto> productGroups) {
        this.productGroups = productGroups;
    }

    public List<ProductContentDto> getContents() {
        return contents;
    }

    public void setContents(List<ProductContentDto> contents) {
        this.contents = contents;
    }
}

