package com.hsjc.central.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @author : zga
 * @date : 2015-12-2
 */
public class TbEmailActivate implements Serializable{
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbemailactivate.ActivateID
     *
     * @mbggenerated
     */
    private Integer activateid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbemailactivate.UserID
     *
     * @mbggenerated
     */
    private Long userid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbemailactivate.CreateTime
     *
     * @mbggenerated
     */
    private Date createtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbemailactivate.ActiveTime
     *
     * @mbggenerated
     */
    private Date activetime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbemailactivate.ActivateKey
     *
     * @mbggenerated
     */
    private String activatekey;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbemailactivate.State
     *
     * @mbggenerated
     */
    private String state;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbemailactivate.ValidSeconds
     *
     * @mbggenerated
     */
    private Integer validseconds;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbemailactivate.ActivateID
     *
     * @return the value of tbemailactivate.ActivateID
     *
     * @mbggenerated
     */
    public Integer getActivateid() {
        return activateid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbemailactivate.ActivateID
     *
     * @param activateid the value for tbemailactivate.ActivateID
     *
     * @mbggenerated
     */
    public void setActivateid(Integer activateid) {
        this.activateid = activateid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbemailactivate.UserID
     *
     * @return the value of tbemailactivate.UserID
     *
     * @mbggenerated
     */
    public Long getUserid() {
        return userid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbemailactivate.UserID
     *
     * @param userid the value for tbemailactivate.UserID
     *
     * @mbggenerated
     */
    public void setUserid(Long userid) {
        this.userid = userid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbemailactivate.CreateTime
     *
     * @return the value of tbemailactivate.CreateTime
     *
     * @mbggenerated
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbemailactivate.CreateTime
     *
     * @param createtime the value for tbemailactivate.CreateTime
     *
     * @mbggenerated
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbemailactivate.ActiveTime
     *
     * @return the value of tbemailactivate.ActiveTime
     *
     * @mbggenerated
     */
    public Date getActivetime() {
        return activetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbemailactivate.ActiveTime
     *
     * @param activetime the value for tbemailactivate.ActiveTime
     *
     * @mbggenerated
     */
    public void setActivetime(Date activetime) {
        this.activetime = activetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbemailactivate.ActivateKey
     *
     * @return the value of tbemailactivate.ActivateKey
     *
     * @mbggenerated
     */
    public String getActivatekey() {
        return activatekey;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbemailactivate.ActivateKey
     *
     * @param activatekey the value for tbemailactivate.ActivateKey
     *
     * @mbggenerated
     */
    public void setActivatekey(String activatekey) {
        this.activatekey = activatekey == null ? null : activatekey.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbemailactivate.State
     *
     * @return the value of tbemailactivate.State
     *
     * @mbggenerated
     */
    public String getState() {
        return state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbemailactivate.State
     *
     * @param state the value for tbemailactivate.State
     *
     * @mbggenerated
     */
    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbemailactivate.ValidSeconds
     *
     * @return the value of tbemailactivate.ValidSeconds
     *
     * @mbggenerated
     */
    public Integer getValidseconds() {
        return validseconds;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbemailactivate.ValidSeconds
     *
     * @param validseconds the value for tbemailactivate.ValidSeconds
     *
     * @mbggenerated
     */
    public void setValidseconds(Integer validseconds) {
        this.validseconds = validseconds;
    }
}