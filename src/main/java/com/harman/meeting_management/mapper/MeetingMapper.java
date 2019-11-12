package com.harman.meeting_management.mapper;

import com.harman.meeting_management.entity.Meeting;

public interface MeetingMapper {
    int deleteByPrimaryKey(Long id);

    int updateByPrimaryKey(Meeting record);
}