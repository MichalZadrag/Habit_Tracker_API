package com.example.habit_tracker_api.payload;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;
import static com.example.habit_tracker_api.utils.RegularExpressions.*;

public class AddEventRequest {

    @NotNull
    @Pattern(regexp = HABIT_AND_TASK_AND_EVENT_REGEXP, message = "Nieprawidłowa nazwa")
    private String event_text;

    @NotNull
    private String color;

    @NotNull
    private Long user_id;

    @NotNull
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date date;

    @NotNull
    @Pattern(regexp = LOCATION_REGEXP, message = "Nieprawidłowa lokalizacja")
    private String location;

    @NotNull
    private String startTime;

    @NotNull
    private String endTime;

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

    public String getEvent_text() {
        return event_text;
    }

    public void setEvent_text(String event_text) {
        this.event_text = event_text;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
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
}
