package com.harman.meeting_management.service.Impl;

import com.harman.meeting_management.mapper.MeetingRUserMapper;
import com.harman.meeting_management.service.MeetingRUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author L.Willian
 * @date 11/15/2019 3:38 PM
 */
@Service
public class MeetingRUserServiceImpl implements MeetingRUserService {
    @Autowired
    private MeetingRUserMapper meetingRUserMapper;

    public int addMeetingPerson(Long meetingId, String[] userIds) {
        return meetingRUserMapper.insertArrUserId(meetingId, userIds);
    }
}
