package com.example.habit_tracker_api.payload;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class TaskSummary {

    private long id;

    private String task_text;

    private String color;

    private long user_id;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date date;

    private boolean isDone;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public TaskSummary(long id, String task_text, String color, long user_id, Date date, boolean isDone) {
        this.id = id;
        this.task_text = task_text;
        this.color = color;
        this.user_id = user_id;
        this.date = date;
        this.isDone = isDone;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }
}
