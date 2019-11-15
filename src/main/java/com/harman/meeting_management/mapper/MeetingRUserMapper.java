package com.harman.meeting_management.mapper;

import com.harman.meeting_management.entity.MeetingRUser;
import org.springframework.stereotype.Repository;

@Repository
public interface MeetingRUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MeetingRUser record);

    int insertSelective(MeetingRUser record);

    int insertArrUserId(Long meetingId, String[] uIds);

    MeetingRUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MeetingRUser record);

    int updateByPrimaryKey(MeetingRUser record);
}