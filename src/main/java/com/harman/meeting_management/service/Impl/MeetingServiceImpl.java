package com.harman.meeting_management.service.Impl;

import com.harman.meeting_management.entity.Meeting;
import com.harman.meeting_management.mapper.MeetingMapper;
import com.harman.meeting_management.mapper.UserMapper;
import com.harman.meeting_management.service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author L.Willian
 * @date 11/15/2019 11:25 AM
 */
@Service
public class MeetingServiceImpl implements MeetingService {
    @Autowired
    private MeetingMapper meetingMapper;

    @Autowired
    private UserMapper userMapper;

    public int addMeeting(Meeting meetingDto) throws DataAccessException {
        return meetingMapper.insertSelective(meetingDto);
    }

    public int cancelMeeting(Meeting meetingDto) {
        return meetingMapper.updateByPrimaryKeySelective(meetingDto);
    }

    @Override
    public List<Meeting> searchMeeting(String meetingName, String roomName, String reserverName, String reserveFromDate, String reserveToDate, String meetingFromDate, String meetingToDate) {
        return null;
    }

    @Override
    public Meeting getMeetingDetailsByMeetingId(long mid) {
        return null;
    }

    @Override
    public List<Meeting> getCanceledMeeting(int uId) {
        return null;
    }

    @Override
    public List<Meeting> getMeeting7Days(int uId) {
        return null;
    }

    @Override
    public List<Meeting> getMyMeeting(long uId) {
        return null;
    }
}
