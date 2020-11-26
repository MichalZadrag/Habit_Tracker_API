package com.example.habit_tracker_api.model;


import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_ID")
    private long id;

    @NotBlank
    @Size(max = 40)
    private String task_text;

    @NotBlank
    @Size(max = 15)
    private String color;

    @NotBlank
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @NotBlank
    private boolean isDone;

    public Task() {}

    public Task(String task_text, String color, Date date, boolean isDone) {
        this.task_text = task_text;
        this.color = color;
        this.date = date;
        this.isDone = isDone;
    }

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
