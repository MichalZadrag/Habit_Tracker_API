package com.example.habit_tracker_api.payload;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.Date;

public class EventSummary {

    private long id;

    private String event_name;

    private String color;

    private long user_id;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date date;

    private String location;

    private String startTime;

    private String endTime;


    public EventSummary(long id,
                        String event_name,
                        String color,
                        long user_id,
                        Date date,
                        String location,
                        String startTime,
                        String endTime) {
        this.id = id;
        this.event_name = event_name;
        this.color = color;
        this.user_id = user_id;
        this.date = date;
        this.location = location;
        this.startTime = startTime;
        this.endTime = endTime;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
