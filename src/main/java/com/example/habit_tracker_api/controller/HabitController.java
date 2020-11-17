package com.example.habit_tracker_api.controller;

import com.example.habit_tracker_api.exception.AppException;
import com.example.habit_tracker_api.model.Habit;
import com.example.habit_tracker_api.model.User;
import com.example.habit_tracker_api.payload.AddHabitRequest;
import com.example.habit_tracker_api.payload.ApiResponse;
import com.example.habit_tracker_api.payload.HabitSummary;
import com.example.habit_tracker_api.repository.HabitRepository;
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
@RequestMapping("/api/habit")
public class HabitController {

    @Autowired
    HabitRepository habitRepository;

    @Autowired
    UserRepository userRepository;


    @PostMapping("/add")
    public ResponseEntity<?> addHabit(@Valid @RequestBody AddHabitRequest addHabitRequest) {

        Habit habit = new Habit(addHabitRequest.getHabit_text(), addHabitRequest.getIcon(), addHabitRequest.getColor());

        User user = userRepository.findById(addHabitRequest.getUser_id())
                .orElseThrow(() -> new AppException("Nie znaleziono uzytkownika "));

        habit.setUser(user);
        habit.setSeries(0);

        habitRepository.save(habit);

        return ResponseEntity.ok(new ApiResponse(true, "Dodano nowy nawyk"));
    }

    @GetMapping("/all/{user_id}")
    public List<?> getAllHabits(@PathVariable(name = "user_id") Long id) {

        List<Habit> habits = habitRepository.findAll();
        List<HabitSummary> habitSummaries = new ArrayList<>();

        habits.forEach(habit -> {
            habitSummaries.add(new HabitSummary(habit.getId(),
                    habit.getHabit_text(), habit.getIcon(),
                    habit.getColor(), habit.getUser().getId()));
        });

        Predicate<HabitSummary> byUserId = habitSummary -> habitSummary.getUser_id() == id;

        return habitSummaries.stream().filter(byUserId).collect(Collectors.toList());
    }


    @DeleteMapping("/delete/{habit_id}")
    public ResponseEntity<?> deleteHabit(@PathVariable(name = "habit_id") Long id) {
        habitRepository.deleteById(id);
        return ResponseEntity.ok(new ApiResponse(true, "Pomyślnie usunięto"));
    }

    @PatchMapping("/increment/{habit_id}")
    public ResponseEntity<?> incrementSeries(@PathVariable(name = "habit_id") Long id) {
        Habit habit = habitRepository.findById(id).get();
        long series = habit.getSeries();
        habit.setSeries(++series);
        habitRepository.save(habit);
        return ResponseEntity.ok(new ApiResponse(true, "Pomyślnie zmieniono"));
    }

}
