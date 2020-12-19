package com.example.habit_tracker_api.payload;


public class HabitSummary {


    private long id;

    private String habit_text;

    private String icon;

    private String color;

    private long user_id;

    private boolean isDone;

    private long series;

    private long max_series;


    public HabitSummary(long id, String habit_text, String icon, String color, long user_id, boolean isDone, long series, long max_series) {
        this.id = id;
        this.habit_text = habit_text;
        this.icon = icon;
        this.color = color;
        this.user_id = user_id;
        this.isDone = isDone;
        this.series = series;
        this.max_series = max_series;
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

    public long getSeries() {
        return series;
    }

    public void setSeries(long series) {
        this.series = series;
    }

    public long getMax_series() {
        return max_series;
    }

    public void setMax_series(long max_series) {
        this.max_series = max_series;
    }
}
