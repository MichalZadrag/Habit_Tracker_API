package com.example.habit_tracker_api.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_ID")
    private long id;

    @NotBlank
    @Size(max = 40)
    private String event_text;

    @NotBlank
    @Size(max = 15)
    private String color;

    @NotBlank
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date date;

    @NotBlank
    @Size(max = 40)
    private String location;

    @NotBlank
    private String startTime;

    @NotBlank
    private String endTime;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Event(@NotBlank @Size(max = 40) String event_text,
                 @NotBlank @Size(max = 15) String color,
                 @NotBlank Date date,
                 @NotBlank @Size(max = 40) String location,
                 @NotBlank String startTime,
                 @NotBlank String endTime) {
        this.event_text = event_text;
        this.color = color;
        this.date = date;
        this.location = location;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Event() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEvent_text() {
        return event_text;
    }

    public void setEvent_text(String event_text) {
        this.event_text = event_text;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
