package com.example.habit_tracker_api.payload;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class AddHabitRequest {


    @NotNull
    @Pattern(regexp = "^[a-zA-Z]{3,}$", message = "Nieprawidłowy nawyk")
    private String habit_text;

    @NotNull
    private String icon;

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
