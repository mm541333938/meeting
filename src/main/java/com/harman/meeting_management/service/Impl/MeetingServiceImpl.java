package com.harman.meeting_management.service.Impl;

import com.harman.meeting_management.entity.Meeting;
import com.harman.meeting_management.mapper.MeetingMapper;
import com.harman.meeting_management.service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

/**
 * @author L.Willian
 * @date 11/15/2019 11:25 AM
 */
@Service
public class MeetingServiceImpl implements MeetingService {
    @Autowired
    private MeetingMapper meetingMapper;

    public int addMeeting(Meeting meetingDto) throws DataAccessException {
        return meetingMapper.insertSelective(meetingDto);
    }
}
