package com.harman.meeting_management.mapper;

import com.harman.meeting_management.entity.mUser;

public interface mUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(mUser record);

    int insertSelective(mUser record);

    mUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(mUser record);

    int updateByPrimaryKey(mUser record);
}