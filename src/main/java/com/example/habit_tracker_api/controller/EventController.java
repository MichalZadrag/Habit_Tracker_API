package com.example.habit_tracker_api.controller;


import com.example.habit_tracker_api.exception.AppException;
import com.example.habit_tracker_api.model.Event;
import com.example.habit_tracker_api.model.User;
import com.example.habit_tracker_api.payload.AddEventRequest;
import com.example.habit_tracker_api.payload.ApiResponse;
import com.example.habit_tracker_api.repository.EventRepository;
import com.example.habit_tracker_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/event")
public class EventController {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/add")
    public ResponseEntity<?> addNewEvent(@Valid @RequestBody AddEventRequest addEventRequest) {

        Event event = new Event(addEventRequest.getEvent_text(), addEventRequest.getColor(), addEventRequest.getDate());

        User user = userRepository.findById(addEventRequest.getUser_id())
                .orElseThrow(() -> new AppException("Nie znaleziono uzytkownika "));

        event.setUser(user);

        eventRepository.save(event);

        return ResponseEntity.ok(new ApiResponse(true, "Dodano nowe wydarzenie"));
    }
}
