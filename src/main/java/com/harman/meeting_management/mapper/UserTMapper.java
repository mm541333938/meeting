package com.harman.meeting_management.mapper;

import com.harman.meeting_management.dto.UserDetailsDto;
import com.harman.meeting_management.entity.UserT;

public interface UserTMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserT record);

    int insertSelective(UserT record);

    UserT selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserT record);

    int updateByPrimaryKey(UserT record);

    UserDetailsDto selectByUsername(String userName);
}