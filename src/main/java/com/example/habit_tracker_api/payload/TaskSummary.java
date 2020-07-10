package com.example.habit_tracker_api.payload;

public class TaskSummary {

    private long id;

    private String task_text;

    private String color;

    private long user_id;

    private String day;

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public TaskSummary(long id, String task_text, String color, long user_id, String day) {
        this.id = id;
        this.task_text = task_text;
        this.color = color;
        this.user_id = user_id;
        this.day = day;
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
