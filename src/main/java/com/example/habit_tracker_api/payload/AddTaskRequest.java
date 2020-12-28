package com.example.habit_tracker_api.payload;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;
import static com.example.habit_tracker_api.utils.RegularExpressions.*;

public class AddTaskRequest {

    @NotNull
    @Pattern(regexp = HABIT_AND_TASK_AND_EVENT_REGEXP, message = "Nieprawidłowy nawyk")
    private String task_text;

    @NotNull
    private String color;

    @NotNull
    private Long user_id;

    @NotNull
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date date;

    @NotNull
    private boolean isDone;

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
