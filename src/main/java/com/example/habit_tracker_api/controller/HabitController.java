package com.example.habit_tracker_api.controller;

import com.example.habit_tracker_api.model.Habit;
import com.example.habit_tracker_api.payload.AddHabitRequest;
import com.example.habit_tracker_api.payload.ApiResponse;
import com.example.habit_tracker_api.repository.HabitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/habit")
public class HabitController {

    @Autowired
    HabitRepository habitRepository;


    @PostMapping("/add")
    public ResponseEntity<?> addHabit(@Valid @RequestBody AddHabitRequest addHabitRequest) {

        Habit habit = new Habit(addHabitRequest.getHabit_text(), addHabitRequest.getIcon());

        habitRepository.save(habit);

        return ResponseEntity.ok(new ApiResponse(true, "Dodano nowy nawyk"));
    }

    @GetMapping("/all")
    public Object getAllHabits() {
        return habitRepository.findAll();
    }

}
