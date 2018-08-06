package com.baicells.manager.model.entity;

import tk.mybatis.mapper.annotation.KeySql;

import java.util.Date;
import javax.persistence.*;

@Table(name = "`t_home_brand`")
public class HomeBrand {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_home_brand.id
     *
     * @mbg.generated Sun Aug 05 20:42:45 CST 2018
     */
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_home_brand.create_time
     *
     * @mbg.generated Sun Aug 05 20:42:45 CST 2018
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_home_brand.title
     *
     * @mbg.generated Sun Aug 05 20:42:45 CST 2018
     */
    private String title;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_home_brand.img_url
     *
     * @mbg.generated Sun Aug 05 20:42:45 CST 2018
     */
    private String imgUrl;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_home_brand.enable
     *
     * @mbg.generated Sun Aug 05 20:42:45 CST 2018
     */
    private Boolean enable;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_home_brand.id
     *
     * @return the value of t_home_brand.id
     *
     * @mbg.generated Sun Aug 05 20:42:45 CST 2018
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_home_brand.id
     *
     * @param id the value for t_home_brand.id
     *
     * @mbg.generated Sun Aug 05 20:42:45 CST 2018
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_home_brand.create_time
     *
     * @return the value of t_home_brand.create_time
     *
     * @mbg.generated Sun Aug 05 20:42:45 CST 2018
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_home_brand.create_time
     *
     * @param createTime the value for t_home_brand.create_time
     *
     * @mbg.generated Sun Aug 05 20:42:45 CST 2018
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_home_brand.title
     *
     * @return the value of t_home_brand.title
     *
     * @mbg.generated Sun Aug 05 20:42:45 CST 2018
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_home_brand.title
     *
     * @param title the value for t_home_brand.title
     *
     * @mbg.generated Sun Aug 05 20:42:45 CST 2018
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_home_brand.img_url
     *
     * @return the value of t_home_brand.img_url
     *
     * @mbg.generated Sun Aug 05 20:42:45 CST 2018
     */
    public String getImgUrl() {
        return imgUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_home_brand.img_url
     *
     * @param imgUrl the value for t_home_brand.img_url
     *
     * @mbg.generated Sun Aug 05 20:42:45 CST 2018
     */
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_home_brand.enable
     *
     * @return the value of t_home_brand.enable
     *
     * @mbg.generated Sun Aug 05 20:42:45 CST 2018
     */
    public Boolean getEnable() {
        return enable;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_home_brand.enable
     *
     * @param enable the value for t_home_brand.enable
     *
     * @mbg.generated Sun Aug 05 20:42:45 CST 2018
     */
    public void setEnable(Boolean enable) {
        this.enable = enable;
    }
}