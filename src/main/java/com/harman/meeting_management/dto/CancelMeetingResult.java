package com.harman.meeting_management.dto;

import com.harman.meeting_management.entity.Meeting;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 用于getCanceledMeeting()方法
 * 当前用户取消的会议的信息的返回结果
 *
 * @author L.Willian
 * @date 2020/1/3
 */
@Getter
@Setter
public class CancelMeetingResult extends Meeting implements Serializable {
    private String roomName;
    private String userName;
}
