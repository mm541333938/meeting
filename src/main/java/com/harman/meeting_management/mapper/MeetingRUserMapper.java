package com.harman.meeting_management.mapper;

import com.harman.meeting_management.entity.MeetingRUser;

public interface MeetingRUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MeetingRUser record);

    int insertSelective(MeetingRUser record);

    MeetingRUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MeetingRUser record);

    int updateByPrimaryKey(MeetingRUser record);
}