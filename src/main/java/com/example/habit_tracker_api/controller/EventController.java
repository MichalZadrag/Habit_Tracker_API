package com.example.habit_tracker_api.controller;


import com.example.habit_tracker_api.exception.AppException;
import com.example.habit_tracker_api.model.Event;
import com.example.habit_tracker_api.model.User;
import com.example.habit_tracker_api.payload.AddEventRequest;
import com.example.habit_tracker_api.payload.ApiResponse;
import com.example.habit_tracker_api.payload.EventSummary;
import com.example.habit_tracker_api.repository.EventRepository;
import com.example.habit_tracker_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/event")
public class EventController {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/add")
    public ResponseEntity<?> addNewEvent(@Valid @RequestBody AddEventRequest addEventRequest) {

        Event event = new Event(
                addEventRequest.getEvent_text(),
                addEventRequest.getColor(),
                addEventRequest.getDate(),
                addEventRequest.getLocation(),
                addEventRequest.getStartTime(),
                addEventRequest.getEndTime());

        User user = userRepository.findById(addEventRequest.getUser_id())
                .orElseThrow(() -> new AppException("Nie znaleziono uzytkownika "));

        event.setUser(user);

        eventRepository.save(event);

        return ResponseEntity.ok(new ApiResponse(true, "Dodano nowe wydarzenie"));
    }

    @GetMapping("/all/{user_id}")
    public List<?> getAllEvents(@PathVariable(name = "user_id") Long id) {

        List<Event> events = eventRepository.findAll();
        List<EventSummary> eventSummaries = new ArrayList<>();

        events.forEach(event -> {
            eventSummaries.add(new EventSummary(event.getId(),
                    event.getEvent_text(), event.getColor(),
                    event.getUser().getId(), event.getDate(),
                    event.getLocation(),event.getStartTime(), event.getEndTime()));
        });

        Predicate<EventSummary> byUserId = eventSummary -> eventSummary.getUser_id() == id;

        return eventSummaries.stream().filter(byUserId).collect(Collectors.toList());
    }

    @DeleteMapping("/delete/{event_id}")
    public ResponseEntity<?> deleteEvent(@PathVariable(name = "event_id") Long id) {
        eventRepository.deleteById(id);
        return ResponseEntity.ok(new ApiResponse(true, "Pomyślnie usunięto"));
    }
}
