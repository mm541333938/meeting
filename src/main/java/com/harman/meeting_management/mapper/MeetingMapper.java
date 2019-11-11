package com.harman.meeting_management.mapper;

import com.harman.meeting_management.entity.Meeting;
import com.harman.meeting_management.entity.MeetingWithBLOBs;

public interface MeetingMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MeetingWithBLOBs record);

    int insertSelective(MeetingWithBLOBs record);

    MeetingWithBLOBs selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MeetingWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(MeetingWithBLOBs record);

    int updateByPrimaryKey(Meeting record);
}