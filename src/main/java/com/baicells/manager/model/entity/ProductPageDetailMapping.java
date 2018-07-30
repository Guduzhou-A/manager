package com.baicells.manager.model.entity;

import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.*;

@Table(name = "`t_product_page_detail_mapping`")
public class ProductPageDetailMapping {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private Integer productPageId;
    private String groupTitle;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductPageId() {
        return productPageId;
    }

    public void setProductPageId(Integer productPageId) {
        this.productPageId = productPageId;
    }

    public String getGroupTitle() {
        return groupTitle;
    }

    public void setGroupTitle(String groupTitle) {
        this.groupTitle = groupTitle;
    }
}