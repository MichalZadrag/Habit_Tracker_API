package com.example.habit_tracker_api.model;


import com.example.habit_tracker_api.model.audit.DateAudit;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "habits")
public class Habit extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "habit_ID")
    private long id;

    @NotBlank
    @Size(max = 40)
    private String habit_text;

    @NotBlank
    @Size(max = 15)
    private String icon;

    @NotBlank
    @Size(max = 15)
    private String color;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @NotBlank
    private long series;

    @NotBlank
    private long max_series;

    @NotBlank
    private boolean isDone;


    public Habit() {
    }

    public Habit(String habit_text, String icon, String color, boolean isDone) {
        this.habit_text = habit_text;
        this.icon = icon;
        this.color = color;
        this.isDone = isDone;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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
