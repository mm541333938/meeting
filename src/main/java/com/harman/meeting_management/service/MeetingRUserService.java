package com.harman.meeting_management.service;

/**
 * @author L.Willian
 * @date 11/11/2019 4:05 PM
 */
public interface MeetingRUserService {
    /**
     * 通过获取刚插入meeting id 来对 参加会议的人进行插入
     *
     * @return
     */
    public int addMeetingPerson(Long meetingId, String[] userIds);


}
