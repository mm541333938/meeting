package com.harman.meeting_management.service.Impl;

import com.harman.meeting_management.dto.CancelMeetingResult;
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

    //todo
    @Override
    public List<Meeting> searchMeeting(Meeting meetingDto) {
        return meetingMapper.selectSelective(meetingDto);
    }

    @Override
    public Meeting getMeetingDetailsByMeetingId(Long mid) {
        return meetingMapper.selectByPrimaryKey(mid);
    }

    @Override
    public List<CancelMeetingResult> getCanceledMeeting(Long uId) {

        return null;
    }

    @Override
    public List<Meeting> getMeeting7Days(Long uId) {
        return null;
    }

    @Override
    public List<Meeting> getMyMeeting(Long uId) {
        return null;
    }


}
