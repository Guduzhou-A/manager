package com.baicells.manager.model.entity;

import tk.mybatis.mapper.annotation.KeySql;

import java.util.Date;
import javax.persistence.*;

@Table(name = "`t_solution_5g_page`")
public class Solution5gPage {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private String title;
    private String portalForwardUrl;
    private String navPicUrl;
    private String navDesc;
    private String bgPicUrl;
    private String contentDesc;
    private String contentTop;
    private String contentBottom;
    private String middleTitle;
    private String middleBgPic;
    private String middlePic1;
    private String middlePic2;
    private String middlePic3;
    private String middleText1;
    private String middleText2;
    private String middleText3;
    private Date createTime;
    private Date updateTime;
    private Boolean enable;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPortalForwardUrl() {
        return portalForwardUrl;
    }

    public void setPortalForwardUrl(String portalForwardUrl) {
        this.portalForwardUrl = portalForwardUrl;
    }

    public String getNavPicUrl() {
        return navPicUrl;
    }

    public void setNavPicUrl(String navPicUrl) {
        this.navPicUrl = navPicUrl;
    }

    public String getNavDesc() {
        return navDesc;
    }

    public void setNavDesc(String navDesc) {
        this.navDesc = navDesc;
    }

    public String getBgPicUrl() {
        return bgPicUrl;
    }

    public void setBgPicUrl(String bgPicUrl) {
        this.bgPicUrl = bgPicUrl;
    }

    public String getContentDesc() {
        return contentDesc;
    }

    public void setContentDesc(String contentDesc) {
        this.contentDesc = contentDesc;
    }

    public String getContentTop() {
        return contentTop;
    }

    public void setContentTop(String contentTop) {
        this.contentTop = contentTop;
    }

    public String getContentBottom() {
        return contentBottom;
    }

    public void setContentBottom(String contentBottom) {
        this.contentBottom = contentBottom;
    }

    public String getMiddleTitle() {
        return middleTitle;
    }

    public void setMiddleTitle(String middleTitle) {
        this.middleTitle = middleTitle;
    }

    public String getMiddlePic1() {
        return middlePic1;
    }

    public void setMiddlePic1(String middlePic1) {
        this.middlePic1 = middlePic1;
    }

    public String getMiddlePic2() {
        return middlePic2;
    }

    public void setMiddlePic2(String middlePic2) {
        this.middlePic2 = middlePic2;
    }

    public String getMiddlePic3() {
        return middlePic3;
    }

    public void setMiddlePic3(String middlePic3) {
        this.middlePic3 = middlePic3;
    }

    public String getMiddleText1() {
        return middleText1;
    }

    public void setMiddleText1(String middleText1) {
        this.middleText1 = middleText1;
    }

    public String getMiddleText2() {
        return middleText2;
    }

    public void setMiddleText2(String middleText2) {
        this.middleText2 = middleText2;
    }

    public String getMiddleText3() {
        return middleText3;
    }

    public void setMiddleText3(String middleText3) {
        this.middleText3 = middleText3;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public String getMiddleBgPic() {
        return middleBgPic;
    }

    public void setMiddleBgPic(String middleBgPic) {
        this.middleBgPic = middleBgPic;
    }
}