package com.harman.meeting_management.entity;

import java.io.Serializable;
import java.util.Date;

public class Room implements Serializable {
    private Integer id;

    private Integer roomNum;

    private String roomType;

    private String roomMoney;

    private Date createTime;

    private Date updateTime;

    private Integer roomStatus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(Integer roomNum) {
        this.roomNum = roomNum;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType == null ? null : roomType.trim();
    }

    public String getRoomMoney() {
        return roomMoney;
    }

    public void setRoomMoney(String roomMoney) {
        this.roomMoney = roomMoney == null ? null : roomMoney.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(Integer roomStatus) {
        this.roomStatus = roomStatus;
    }
}