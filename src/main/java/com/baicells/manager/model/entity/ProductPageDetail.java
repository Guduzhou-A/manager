package com.baicells.manager.model.entity;

import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.*;

@Table(name = "`t_product_page_detail`")
public class ProductPageDetail {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_product_page_detail.id
     *
     * @mbggenerated Mon Jul 23 18:04:48 CST 2018
     */
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_product_page_detail.title
     *
     * @mbggenerated Mon Jul 23 18:04:48 CST 2018
     */
    private String title;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_product_page_detail.specifications
     *
     * @mbggenerated Mon Jul 23 18:04:48 CST 2018
     */
    private Integer specifications;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_product_page_detail.file_url
     *
     * @mbggenerated Mon Jul 23 18:04:48 CST 2018
     */
    private String fileUrl;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_product_page_detail.desc
     *
     * @mbggenerated Mon Jul 23 18:04:48 CST 2018
     */
    private String desc;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_product_page_detail.show_desc
     *
     * @mbggenerated Mon Jul 23 18:04:48 CST 2018
     */
    private Boolean showDesc;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_product_page_detail.id
     *
     * @return the value of t_product_page_detail.id
     *
     * @mbggenerated Mon Jul 23 18:04:48 CST 2018
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_product_page_detail.id
     *
     * @param id the value for t_product_page_detail.id
     *
     * @mbggenerated Mon Jul 23 18:04:48 CST 2018
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_product_page_detail.title
     *
     * @return the value of t_product_page_detail.title
     *
     * @mbggenerated Mon Jul 23 18:04:48 CST 2018
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_product_page_detail.title
     *
     * @param title the value for t_product_page_detail.title
     *
     * @mbggenerated Mon Jul 23 18:04:48 CST 2018
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_product_page_detail.specifications
     *
     * @return the value of t_product_page_detail.specifications
     *
     * @mbggenerated Mon Jul 23 18:04:48 CST 2018
     */
    public Integer getSpecifications() {
        return specifications;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_product_page_detail.specifications
     *
     * @param specifications the value for t_product_page_detail.specifications
     *
     * @mbggenerated Mon Jul 23 18:04:48 CST 2018
     */
    public void setSpecifications(Integer specifications) {
        this.specifications = specifications;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_product_page_detail.file_url
     *
     * @return the value of t_product_page_detail.file_url
     *
     * @mbggenerated Mon Jul 23 18:04:48 CST 2018
     */
    public String getFileUrl() {
        return fileUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_product_page_detail.file_url
     *
     * @param fileUrl the value for t_product_page_detail.file_url
     *
     * @mbggenerated Mon Jul 23 18:04:48 CST 2018
     */
    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_product_page_detail.desc
     *
     * @return the value of t_product_page_detail.desc
     *
     * @mbggenerated Mon Jul 23 18:04:48 CST 2018
     */
    public String getDesc() {
        return desc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_product_page_detail.desc
     *
     * @param desc the value for t_product_page_detail.desc
     *
     * @mbggenerated Mon Jul 23 18:04:48 CST 2018
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_product_page_detail.show_desc
     *
     * @return the value of t_product_page_detail.show_desc
     *
     * @mbggenerated Mon Jul 23 18:04:48 CST 2018
     */
    public Boolean getShowDesc() {
        return showDesc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_product_page_detail.show_desc
     *
     * @param showDesc the value for t_product_page_detail.show_desc
     *
     * @mbggenerated Mon Jul 23 18:04:48 CST 2018
     */
    public void setShowDesc(Boolean showDesc) {
        this.showDesc = showDesc;
    }
}