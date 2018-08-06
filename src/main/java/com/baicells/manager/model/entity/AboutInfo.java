package com.baicells.manager.model.entity;

import tk.mybatis.mapper.annotation.KeySql;

import java.util.Date;
import javax.persistence.*;

@Table(name = "`about_info`")
public class AboutInfo {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column about_info.id
     *
     * @mbg.generated Sun Aug 05 21:48:19 CST 2018
     */
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column about_info.company_name
     *
     * @mbg.generated Sun Aug 05 21:48:19 CST 2018
     */
    private String companyName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column about_info.region
     *
     * @mbg.generated Sun Aug 05 21:48:19 CST 2018
     */
    private String region;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column about_info.country
     *
     * @mbg.generated Sun Aug 05 21:48:19 CST 2018
     */
    private String country;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column about_info.street_address
     *
     * @mbg.generated Sun Aug 05 21:48:19 CST 2018
     */
    private String streetAddress;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column about_info.city
     *
     * @mbg.generated Sun Aug 05 21:48:19 CST 2018
     */
    private String city;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column about_info.state
     *
     * @mbg.generated Sun Aug 05 21:48:19 CST 2018
     */
    private String state;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column about_info.zip
     *
     * @mbg.generated Sun Aug 05 21:48:19 CST 2018
     */
    private String zip;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column about_info.website
     *
     * @mbg.generated Sun Aug 05 21:48:19 CST 2018
     */
    private String website;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column about_info.first_name
     *
     * @mbg.generated Sun Aug 05 21:48:19 CST 2018
     */
    private String firstName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column about_info.last_name
     *
     * @mbg.generated Sun Aug 05 21:48:19 CST 2018
     */
    private String lastName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column about_info.phone
     *
     * @mbg.generated Sun Aug 05 21:48:19 CST 2018
     */
    private String phone;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column about_info.email
     *
     * @mbg.generated Sun Aug 05 21:48:19 CST 2018
     */
    private String email;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column about_info.create_time
     *
     * @mbg.generated Sun Aug 05 21:48:19 CST 2018
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column about_info.question_one
     *
     * @mbg.generated Sun Aug 05 21:48:19 CST 2018
     */
    private String questionOne;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column about_info.question_two
     *
     * @mbg.generated Sun Aug 05 21:48:19 CST 2018
     */
    private String questionTwo;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column about_info.question_three
     *
     * @mbg.generated Sun Aug 05 21:48:19 CST 2018
     */
    private String questionThree;


    private String jobTitle;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column about_info.id
     *
     * @return the value of about_info.id
     *
     * @mbg.generated Sun Aug 05 21:48:19 CST 2018
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column about_info.id
     *
     * @param id the value for about_info.id
     *
     * @mbg.generated Sun Aug 05 21:48:19 CST 2018
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column about_info.company_name
     *
     * @return the value of about_info.company_name
     *
     * @mbg.generated Sun Aug 05 21:48:19 CST 2018
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column about_info.company_name
     *
     * @param companyName the value for about_info.company_name
     *
     * @mbg.generated Sun Aug 05 21:48:19 CST 2018
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column about_info.region
     *
     * @return the value of about_info.region
     *
     * @mbg.generated Sun Aug 05 21:48:19 CST 2018
     */
    public String getRegion() {
        return region;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column about_info.region
     *
     * @param region the value for about_info.region
     *
     * @mbg.generated Sun Aug 05 21:48:19 CST 2018
     */
    public void setRegion(String region) {
        this.region = region;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column about_info.country
     *
     * @return the value of about_info.country
     *
     * @mbg.generated Sun Aug 05 21:48:19 CST 2018
     */
    public String getCountry() {
        return country;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column about_info.country
     *
     * @param country the value for about_info.country
     *
     * @mbg.generated Sun Aug 05 21:48:19 CST 2018
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column about_info.street_address
     *
     * @return the value of about_info.street_address
     *
     * @mbg.generated Sun Aug 05 21:48:19 CST 2018
     */
    public String getStreetAddress() {
        return streetAddress;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column about_info.street_address
     *
     * @param streetAddress the value for about_info.street_address
     *
     * @mbg.generated Sun Aug 05 21:48:19 CST 2018
     */
    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column about_info.city
     *
     * @return the value of about_info.city
     *
     * @mbg.generated Sun Aug 05 21:48:19 CST 2018
     */
    public String getCity() {
        return city;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column about_info.city
     *
     * @param city the value for about_info.city
     *
     * @mbg.generated Sun Aug 05 21:48:19 CST 2018
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column about_info.state
     *
     * @return the value of about_info.state
     *
     * @mbg.generated Sun Aug 05 21:48:19 CST 2018
     */
    public String getState() {
        return state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column about_info.state
     *
     * @param state the value for about_info.state
     *
     * @mbg.generated Sun Aug 05 21:48:19 CST 2018
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column about_info.zip
     *
     * @return the value of about_info.zip
     *
     * @mbg.generated Sun Aug 05 21:48:19 CST 2018
     */
    public String getZip() {
        return zip;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column about_info.zip
     *
     * @param zip the value for about_info.zip
     *
     * @mbg.generated Sun Aug 05 21:48:19 CST 2018
     */
    public void setZip(String zip) {
        this.zip = zip;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column about_info.website
     *
     * @return the value of about_info.website
     *
     * @mbg.generated Sun Aug 05 21:48:19 CST 2018
     */
    public String getWebsite() {
        return website;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column about_info.website
     *
     * @param website the value for about_info.website
     *
     * @mbg.generated Sun Aug 05 21:48:19 CST 2018
     */
    public void setWebsite(String website) {
        this.website = website;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column about_info.first_name
     *
     * @return the value of about_info.first_name
     *
     * @mbg.generated Sun Aug 05 21:48:19 CST 2018
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column about_info.first_name
     *
     * @param firstName the value for about_info.first_name
     *
     * @mbg.generated Sun Aug 05 21:48:19 CST 2018
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column about_info.last_name
     *
     * @return the value of about_info.last_name
     *
     * @mbg.generated Sun Aug 05 21:48:19 CST 2018
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column about_info.last_name
     *
     * @param lastName the value for about_info.last_name
     *
     * @mbg.generated Sun Aug 05 21:48:19 CST 2018
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column about_info.phone
     *
     * @return the value of about_info.phone
     *
     * @mbg.generated Sun Aug 05 21:48:19 CST 2018
     */
    public String getPhone() {
        return phone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column about_info.phone
     *
     * @param phone the value for about_info.phone
     *
     * @mbg.generated Sun Aug 05 21:48:19 CST 2018
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column about_info.email
     *
     * @return the value of about_info.email
     *
     * @mbg.generated Sun Aug 05 21:48:19 CST 2018
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column about_info.email
     *
     * @param email the value for about_info.email
     *
     * @mbg.generated Sun Aug 05 21:48:19 CST 2018
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column about_info.create_time
     *
     * @return the value of about_info.create_time
     *
     * @mbg.generated Sun Aug 05 21:48:19 CST 2018
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column about_info.create_time
     *
     * @param createTime the value for about_info.create_time
     *
     * @mbg.generated Sun Aug 05 21:48:19 CST 2018
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column about_info.question_one
     *
     * @return the value of about_info.question_one
     *
     * @mbg.generated Sun Aug 05 21:48:19 CST 2018
     */
    public String getQuestionOne() {
        return questionOne;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column about_info.question_one
     *
     * @param questionOne the value for about_info.question_one
     *
     * @mbg.generated Sun Aug 05 21:48:19 CST 2018
     */
    public void setQuestionOne(String questionOne) {
        this.questionOne = questionOne;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column about_info.question_two
     *
     * @return the value of about_info.question_two
     *
     * @mbg.generated Sun Aug 05 21:48:19 CST 2018
     */
    public String getQuestionTwo() {
        return questionTwo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column about_info.question_two
     *
     * @param questionTwo the value for about_info.question_two
     *
     * @mbg.generated Sun Aug 05 21:48:19 CST 2018
     */
    public void setQuestionTwo(String questionTwo) {
        this.questionTwo = questionTwo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column about_info.question_three
     *
     * @return the value of about_info.question_three
     *
     * @mbg.generated Sun Aug 05 21:48:19 CST 2018
     */
    public String getQuestionThree() {
        return questionThree;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column about_info.question_three
     *
     * @param questionThree the value for about_info.question_three
     *
     * @mbg.generated Sun Aug 05 21:48:19 CST 2018
     */
    public void setQuestionThree(String questionThree) {

        this.questionThree = questionThree;
    }
}