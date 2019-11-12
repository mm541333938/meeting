package com.harman.meeting_management.entity;

import java.io.Serializable;
import java.util.Date;

public class Log implements Serializable {
    private Long id;

    private Date times;

    private String text;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getTimes() {
        return times;
    }

    public void setTimes(Date times) {
        this.times = times;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text == null ? null : text.trim();
    }
}