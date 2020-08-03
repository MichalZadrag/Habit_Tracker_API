package com.example.habit_tracker_api.payload;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

public class AddTaskRequest {

    @NotNull
    @Pattern(regexp = "^[a-zA-Z]{3,}?( +[a-zA-Z]+)?( +[a-zA-Z]{2,})?$", message = "Nieprawidłowy nawyk")
    private String task_text;

    @NotNull
    private String color;

    @NotNull
    private Long user_id;

    @NotNull
    private String day;

    @NotNull
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTask_text() {
        return task_text;
    }

    public void setTask_text(String task_text) {
        this.task_text = task_text;
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
}
