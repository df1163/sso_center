package com.hsjc.central.domain;

import java.io.Serializable;
import java.util.Date;

public class TbEmailResetPwd implements Serializable{
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbemailresetpwd.ID
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbemailresetpwd.UserID
     *
     * @mbggenerated
     */
    private Long userid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbemailresetpwd.CreateTime
     *
     * @mbggenerated
     */
    private Date createtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbemailresetpwd.UseTime
     *
     * @mbggenerated
     */
    private Date usetime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbemailresetpwd.Code
     *
     * @mbggenerated
     */
    private String code;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbemailresetpwd.State
     *
     * @mbggenerated
     */
    private String state;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbemailresetpwd.ValidSeconds
     *
     * @mbggenerated
     */
    private Integer validseconds;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbemailresetpwd.ID
     *
     * @return the value of tbemailresetpwd.ID
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbemailresetpwd.ID
     *
     * @param id the value for tbemailresetpwd.ID
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbemailresetpwd.UserID
     *
     * @return the value of tbemailresetpwd.UserID
     *
     * @mbggenerated
     */
    public Long getUserid() {
        return userid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbemailresetpwd.UserID
     *
     * @param userid the value for tbemailresetpwd.UserID
     *
     * @mbggenerated
     */
    public void setUserid(Long userid) {
        this.userid = userid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbemailresetpwd.CreateTime
     *
     * @return the value of tbemailresetpwd.CreateTime
     *
     * @mbggenerated
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbemailresetpwd.CreateTime
     *
     * @param createtime the value for tbemailresetpwd.CreateTime
     *
     * @mbggenerated
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbemailresetpwd.UseTime
     *
     * @return the value of tbemailresetpwd.UseTime
     *
     * @mbggenerated
     */
    public Date getUsetime() {
        return usetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbemailresetpwd.UseTime
     *
     * @param usetime the value for tbemailresetpwd.UseTime
     *
     * @mbggenerated
     */
    public void setUsetime(Date usetime) {
        this.usetime = usetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbemailresetpwd.Code
     *
     * @return the value of tbemailresetpwd.Code
     *
     * @mbggenerated
     */
    public String getCode() {
        return code;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbemailresetpwd.Code
     *
     * @param code the value for tbemailresetpwd.Code
     *
     * @mbggenerated
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbemailresetpwd.State
     *
     * @return the value of tbemailresetpwd.State
     *
     * @mbggenerated
     */
    public String getState() {
        return state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbemailresetpwd.State
     *
     * @param state the value for tbemailresetpwd.State
     *
     * @mbggenerated
     */
    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbemailresetpwd.ValidSeconds
     *
     * @return the value of tbemailresetpwd.ValidSeconds
     *
     * @mbggenerated
     */
    public Integer getValidseconds() {
        return validseconds;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbemailresetpwd.ValidSeconds
     *
     * @param validseconds the value for tbemailresetpwd.ValidSeconds
     *
     * @mbggenerated
     */
    public void setValidseconds(Integer validseconds) {
        this.validseconds = validseconds;
    }
}