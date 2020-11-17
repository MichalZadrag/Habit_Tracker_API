package com.example.habit_tracker_api.model;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "habits")
public class Habit {

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Habit() {
    }

    public Habit(String habit_text, String icon, String color) {
        this.habit_text = habit_text;
        this.icon = icon;
        this.color = color;
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
}
