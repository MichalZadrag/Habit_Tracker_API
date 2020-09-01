package com.example.habit_tracker_api.payload;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

public class AddEventRequest {

    @NotNull
    @Pattern(regexp = "^[a-zA-Z]{3,}?( +[a-zA-Z]+)?( +[a-zA-Z]{2,})?$", message = "Nieprawidłowa nazwa")
    private String event_text;

    @NotNull
    private String color;

    @NotNull
    private Long user_id;

    @NotNull
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date date;


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
}
