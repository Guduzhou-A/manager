package com.baicells.manager.model.entity;

import tk.mybatis.mapper.annotation.KeySql;

import java.util.Date;
import javax.persistence.*;

@Table(name = "`t_media_customer`")
public class MediaCustomer {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_media_customer.id
     *
     * @mbg.generated Mon Jul 30 22:13:03 CST 2018
     */
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_media_customer.nav_pic_url
     *
     * @mbg.generated Mon Jul 30 22:13:03 CST 2018
     */
    private String navPicUrl;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_media_customer.nav_title
     *
     * @mbg.generated Mon Jul 30 22:13:03 CST 2018
     */
    private String navTitle;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_media_customer.nav_desc
     *
     * @mbg.generated Mon Jul 30 22:13:03 CST 2018
     */
    private String navDesc;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_media_customer.create_time
     *
     * @mbg.generated Mon Jul 30 22:13:03 CST 2018
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_media_customer.file_url
     *
     * @mbg.generated Mon Jul 30 22:13:03 CST 2018
     */
    private String fileUrl;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_media_customer.id
     *
     * @return the value of t_media_customer.id
     *
     * @mbg.generated Mon Jul 30 22:13:03 CST 2018
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_media_customer.id
     *
     * @param id the value for t_media_customer.id
     *
     * @mbg.generated Mon Jul 30 22:13:03 CST 2018
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_media_customer.nav_pic_url
     *
     * @return the value of t_media_customer.nav_pic_url
     *
     * @mbg.generated Mon Jul 30 22:13:03 CST 2018
     */
    public String getNavPicUrl() {
        return navPicUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_media_customer.nav_pic_url
     *
     * @param navPicUrl the value for t_media_customer.nav_pic_url
     *
     * @mbg.generated Mon Jul 30 22:13:03 CST 2018
     */
    public void setNavPicUrl(String navPicUrl) {
        this.navPicUrl = navPicUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_media_customer.nav_title
     *
     * @return the value of t_media_customer.nav_title
     *
     * @mbg.generated Mon Jul 30 22:13:03 CST 2018
     */
    public String getNavTitle() {
        return navTitle;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_media_customer.nav_title
     *
     * @param navTitle the value for t_media_customer.nav_title
     *
     * @mbg.generated Mon Jul 30 22:13:03 CST 2018
     */
    public void setNavTitle(String navTitle) {
        this.navTitle = navTitle;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_media_customer.nav_desc
     *
     * @return the value of t_media_customer.nav_desc
     *
     * @mbg.generated Mon Jul 30 22:13:03 CST 2018
     */
    public String getNavDesc() {
        return navDesc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_media_customer.nav_desc
     *
     * @param navDesc the value for t_media_customer.nav_desc
     *
     * @mbg.generated Mon Jul 30 22:13:03 CST 2018
     */
    public void setNavDesc(String navDesc) {
        this.navDesc = navDesc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_media_customer.create_time
     *
     * @return the value of t_media_customer.create_time
     *
     * @mbg.generated Mon Jul 30 22:13:03 CST 2018
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_media_customer.create_time
     *
     * @param createTime the value for t_media_customer.create_time
     *
     * @mbg.generated Mon Jul 30 22:13:03 CST 2018
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_media_customer.file_url
     *
     * @return the value of t_media_customer.file_url
     *
     * @mbg.generated Mon Jul 30 22:13:03 CST 2018
     */
    public String getFileUrl() {
        return fileUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_media_customer.file_url
     *
     * @param fileUrl the value for t_media_customer.file_url
     *
     * @mbg.generated Mon Jul 30 22:13:03 CST 2018
     */
    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }
}