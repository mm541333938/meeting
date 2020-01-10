package com.harman.meeting_management.entity;

import java.util.Date;

public class Meeting {
    private Long id;

    private String meetingName;

    private Long roomId;

    private Long reservationId;

    private Date startTime;

    private Date endTime;

    private Date reserveTime;

    private Date canceledTime;

    private String description;

    private Integer status;

    private String canceledReason;

    private Integer prePersonNum;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMeetingName() {
        return meetingName;
    }

    public void setMeetingName(String meetingName) {
        this.meetingName = meetingName == null ? null : meetingName.trim();
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getReserveTime() {
        return reserveTime;
    }

    public void setReserveTime(Date reserveTime) {
        this.reserveTime = reserveTime;
    }

    public Date getCanceledTime() {
        return canceledTime;
    }

    public void setCanceledTime(Date canceledTime) {
        this.canceledTime = canceledTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCanceledReason() {
        return canceledReason;
    }

    public void setCanceledReason(String canceledReason) {
        this.canceledReason = canceledReason == null ? null : canceledReason.trim();
    }

    public Integer getPrePersonNum() {
        return prePersonNum;
    }

    public void setPrePersonNum(Integer prePersonNum) {
        this.prePersonNum = prePersonNum;
    }

    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }
}