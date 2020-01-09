package com.harman.meeting_management.dao;

import com.harman.meeting_management.dto.MeetingResultParam;
import com.harman.meeting_management.entity.Meeting;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 查询当前用户取消的会议信息的dao
 *
 * @author L.Willian
 * @date 2020/1/3
 */
@Repository
public interface MeetingResultDao {
    /**
     * 通过用户id 查询用户已经取消的会议信息
     *
     * @param uId
     * @return
     */
    List<MeetingResultParam> selectCancelMeetingByUid(Long uId);

    /**
     * 通过不同条件检索查询
     *
     * @param meetingDto
     * @return
     */
    List<MeetingResultParam> selectBySelective(Meeting meetingDto);

    /**
     * 查询当前用户指定7天的会议信息
     *
     * @param uId
     * @return
     */
    List<MeetingResultParam> selectCurrent7Days(Long uId);

    /**
     * 查询自己的会议信息
     *
     * @param uId
     * @return
     */
    List<MeetingResultParam> selectSelfMeeting(Long uId);
}
