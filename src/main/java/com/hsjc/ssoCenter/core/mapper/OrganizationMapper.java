package com.hsjc.ssoCenter.core.mapper;

import com.hsjc.ssoCenter.core.domain.Organization;

/**
 * @author : zga
 * @date : 2015-12-10
 *
 * 组织机构Mapper类
 *
 */
public interface OrganizationMapper {
    int insert(Organization organization);

    int insertSelective(Organization organization);

    int deleteByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Organization organization);

    int updateByPrimaryKey(Organization organization);

    Organization selectByPrimaryKey(Long id);
}