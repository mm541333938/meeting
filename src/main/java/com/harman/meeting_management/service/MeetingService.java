package com.harman.meeting_management.service;

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
     * 查询会议
     */

    List<Meeting> searchMeeting(String meetingName, String roomName, String reserverName, String reserveFromDate, String reserveToDate, String meetingFromDate, String meetingToDate);

    /**
     * 会议的详细信息
     */
    Meeting getMeetingDetailsByMeetingId(long mid);

    /**
     * 得到取消的会议
     */
    List<Meeting> getCanceledMeeting(int uId);


    /**
     * 得到7天的会议信息
     */
    List<Meeting> getMeeting7Days(int uId);

    /**
     * 得到我的会议信息
     */

    List<Meeting> getMyMeeting(long uId);

}
