package com.example.habit_tracker_api.payload;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import static com.example.habit_tracker_api.utils.RegularExpressions.*;

public class AddHabitRequest {


    @NotNull
    @Pattern(regexp = HABIT_AND_TASK_AND_EVENT_REGEXP, message = "Nieprawidłowy nawyk")
    private String habit_text;

    @NotNull
    private String icon;

    @NotNull
    private String color;

    @NotNull
    private Long user_id;

    @NotNull
    private boolean isDone;

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getHabit_text() {
        return habit_text;
    }

    public void setHabit_text(String habit_text) {
        this.habit_text = habit_text;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
