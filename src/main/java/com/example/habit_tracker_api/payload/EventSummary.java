package com.example.habit_tracker_api.payload;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class EventSummary {

    private long id;

    private String event_name;

    private String color;

    private long user_id;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date date;

    public EventSummary(long id, String event_name, String color, long user_id, Date date) {
        this.id = id;
        this.event_name = event_name;
        this.color = color;
        this.user_id = user_id;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
