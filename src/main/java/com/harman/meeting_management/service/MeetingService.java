package com.harman.meeting_management.service;

import com.harman.meeting_management.entity.Meeting;

/**
 * @author L.Willian
 * @date 11/11/2019 4:05 PM
 */
public interface MeetingService {

    /**
     * 预约会议
     *
     * @param meetingDto
     * @return
     */
    public int addMeeting(Meeting meetingDto);

    /**
     * 取消会议预约
     *
     * @param meetingDto
     * @return
     */
    public int cancelMeeting(Meeting meetingDto);

}
