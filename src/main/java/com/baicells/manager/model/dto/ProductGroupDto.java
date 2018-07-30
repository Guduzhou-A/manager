package com.baicells.manager.model.dto;

import java.io.Serializable;
import java.util.List;

public class ProductGroupDto implements Serializable {
    private Integer id;
    private String groupTitle;
    private List<ProductDto> products;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGroupTitle() {
        return groupTitle;
    }

    public void setGroupTitle(String groupTitle) {
        this.groupTitle = groupTitle;
    }

    public List<ProductDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDto> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "ProductGroupDto{" +
                "id=" + id +
                ", groupTitle='" + groupTitle + '\'' +
                ", products=" + products +
                '}';
    }
}
