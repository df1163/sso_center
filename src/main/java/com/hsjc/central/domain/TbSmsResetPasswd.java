package com.hsjc.central.domain;

import java.io.Serializable;

/**
 * @author : zga
 * @date : 2015-12-2
 */
public class TbSmsResetPasswd implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbsmsresetpasswd.ResetID
     *
     * @mbggenerated
     */
    private Integer resetid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbsmsresetpasswd.AuthID
     *
     * @mbggenerated
     */
    private Integer authid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbsmsresetpasswd.UserID
     *
     * @mbggenerated
     */
    private Long userid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbsmsresetpasswd.NewPwdPlain
     *
     * @mbggenerated
     */
    private String newpwdplain;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbsmsresetpasswd.NewPwdCrypt
     *
     * @mbggenerated
     */
    private String newpwdcrypt;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbsmsresetpasswd.ResetID
     *
     * @return the value of tbsmsresetpasswd.ResetID
     *
     * @mbggenerated
     */
    public Integer getResetid() {
        return resetid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbsmsresetpasswd.ResetID
     *
     * @param resetid the value for tbsmsresetpasswd.ResetID
     *
     * @mbggenerated
     */
    public void setResetid(Integer resetid) {
        this.resetid = resetid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbsmsresetpasswd.AuthID
     *
     * @return the value of tbsmsresetpasswd.AuthID
     *
     * @mbggenerated
     */
    public Integer getAuthid() {
        return authid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbsmsresetpasswd.AuthID
     *
     * @param authid the value for tbsmsresetpasswd.AuthID
     *
     * @mbggenerated
     */
    public void setAuthid(Integer authid) {
        this.authid = authid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbsmsresetpasswd.UserID
     *
     * @return the value of tbsmsresetpasswd.UserID
     *
     * @mbggenerated
     */
    public Long getUserid() {
        return userid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbsmsresetpasswd.UserID
     *
     * @param userid the value for tbsmsresetpasswd.UserID
     *
     * @mbggenerated
     */
    public void setUserid(Long userid) {
        this.userid = userid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbsmsresetpasswd.NewPwdPlain
     *
     * @return the value of tbsmsresetpasswd.NewPwdPlain
     *
     * @mbggenerated
     */
    public String getNewpwdplain() {
        return newpwdplain;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbsmsresetpasswd.NewPwdPlain
     *
     * @param newpwdplain the value for tbsmsresetpasswd.NewPwdPlain
     *
     * @mbggenerated
     */
    public void setNewpwdplain(String newpwdplain) {
        this.newpwdplain = newpwdplain == null ? null : newpwdplain.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbsmsresetpasswd.NewPwdCrypt
     *
     * @return the value of tbsmsresetpasswd.NewPwdCrypt
     *
     * @mbggenerated
     */
    public String getNewpwdcrypt() {
        return newpwdcrypt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbsmsresetpasswd.NewPwdCrypt
     *
     * @param newpwdcrypt the value for tbsmsresetpasswd.NewPwdCrypt
     *
     * @mbggenerated
     */
    public void setNewpwdcrypt(String newpwdcrypt) {
        this.newpwdcrypt = newpwdcrypt == null ? null : newpwdcrypt.trim();
    }
}