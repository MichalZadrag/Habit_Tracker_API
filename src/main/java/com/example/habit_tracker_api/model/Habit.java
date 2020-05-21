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

    public Habit() {

    }

    public Habit(String habit_text, String icon) {
        this.habit_text = habit_text;
        this.icon = icon;
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
}
