package com.hsjc.central.mapper;

import com.hsjc.central.domain.TbEmailActivate;

public interface TbEmailActivateMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbemailactivate
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer activateid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbemailactivate
     *
     * @mbggenerated
     */
    int insert(TbEmailActivate record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbemailactivate
     *
     * @mbggenerated
     */
    int insertSelective(TbEmailActivate record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbemailactivate
     *
     * @mbggenerated
     */
    TbEmailActivate selectByPrimaryKey(Integer activateid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbemailactivate
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(TbEmailActivate record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbemailactivate
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(TbEmailActivate record);
}