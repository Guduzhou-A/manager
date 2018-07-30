package com.baicells.manager.model.entity;

import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.*;

@Table(name = "`t_product_page_detail`")
public class ProductPageDetail {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private String specifications;
    private String picUrl;
    private String fileUrl;
    @Column(name = "`desc`")
    private String desc;
    private Integer groupMappingId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getGroupMappingId() {
        return groupMappingId;
    }

    public void setGroupMappingId(Integer groupMappingId) {
        this.groupMappingId = groupMappingId;
    }
}