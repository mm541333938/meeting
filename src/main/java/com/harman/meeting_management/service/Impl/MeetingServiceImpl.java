package com.harman.meeting_management.service.Impl;

import com.harman.meeting_management.dao.MeetingResultDao;
import com.harman.meeting_management.dto.MeetingResultParam;
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

    @Autowired
    private MeetingResultDao meetingResultDao;

    public int addMeeting(Meeting meetingDto) throws DataAccessException {
        return meetingMapper.insertSelective(meetingDto);
    }

    public int cancelMeeting(Meeting meetingDto) {
        return meetingMapper.updateByPrimaryKeySelective(meetingDto);
    }

    //todo
    @Override
    public List<MeetingResultParam> searchMeeting(Meeting meetingDto) {
        return meetingResultDao.selectBySelective(meetingDto);
    }

    //todo
    @Override
    public Meeting getMeetingDetailsByMeetingId(Long mid) {
        return meetingMapper.selectByPrimaryKey(mid);
    }

    //todo
    @Override
    public List<MeetingResultParam> getCanceledMeeting(Long uId) {
        return meetingResultDao.selectCancelMeetingByUid(uId);
    }

    //todo
    @Override
    public List<MeetingResultParam> getMeeting7Days(Long uId) {
        return meetingResultDao.selectCurrent7Days(uId);
    }

    //todo
    @Override
    public List<MeetingResultParam> getMyMeeting(Long uId) {
        return meetingResultDao.selectSelfMeeting(uId);
    }


}
