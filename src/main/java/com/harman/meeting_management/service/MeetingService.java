package com.harman.meeting_management.service;

import com.harman.meeting_management.dto.MeetingResultParam;
import com.harman.meeting_management.entity.Meeting;

import java.util.List;

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

    /**
     * 搜索会议
     */

    List<MeetingResultParam> searchMeeting(Meeting meetingDto);

    /**
     * 会议的详细信息
     */
    Meeting getMeetingDetailsByMeetingId(Long mid);

    /**
     * 得到当前取消的会议
     */
    List<MeetingResultParam> getCanceledMeeting(Long uId);


    /**
     * 未来7天的会议信息
     */
    List<MeetingResultParam> getMeeting7Days(Long uId);

    /**
     * 得到我的会议信息
     */

    List<MeetingResultParam> getMyMeeting(Long uId);

}
