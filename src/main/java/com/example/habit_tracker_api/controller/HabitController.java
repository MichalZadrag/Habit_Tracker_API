package com.example.habit_tracker_api.controller;

import com.example.habit_tracker_api.exception.AppException;
import com.example.habit_tracker_api.model.Habit;
import com.example.habit_tracker_api.model.User;
import com.example.habit_tracker_api.payload.AddHabitRequest;
import com.example.habit_tracker_api.payload.ApiResponse;
import com.example.habit_tracker_api.payload.HabitSummary;
import com.example.habit_tracker_api.payload.UserIdentityAvailability;
import com.example.habit_tracker_api.repository.HabitRepository;
import com.example.habit_tracker_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
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

        User user = userRepository.findById(addHabitRequest.getUser_id())
                .orElseThrow(() -> new AppException("Nie znaleziono uzytkownika "));

        if(habitRepository.existsByHabitTextAndUser(addHabitRequest.getHabit_text(), user)) {
            return new ResponseEntity(new ApiResponse(false, "Nawyk o takiej już nazwie istnieje"),
                    HttpStatus.BAD_REQUEST);
        }

        Habit habit = new Habit(addHabitRequest.getHabit_text(),
                addHabitRequest.getIcon(),
                addHabitRequest.getColor(),
                addHabitRequest.isDone());


        habit.setUser(user);
        habit.setSeries(0);
        habit.setMax_series(0);

        habitRepository.save(habit);

        return ResponseEntity.ok(new ApiResponse(true, "Dodano nowy nawyk"));
    }

    @GetMapping("/checkHabitAvailability")
    public UserIdentityAvailability checkUsernameAvailability(@RequestParam(value = "habit") String habit_text, @RequestParam("user_id") Long user_id) {

        User user = userRepository.findById(user_id)
                .orElseThrow(() -> new AppException("Nie znaleziono uzytkownika "));

        Boolean isAvailable = !habitRepository.existsByHabitTextAndUser(habit_text, user);
        return new UserIdentityAvailability(isAvailable);
    }

    @GetMapping("/all/{user_id}")
    public List<?> getAllHabits(@PathVariable(name = "user_id") Long id) {

        List<Habit> habits = habitRepository.findAll();
        List<HabitSummary> habitSummaries = new ArrayList<>();

        habits.forEach(habit -> {
            habitSummaries.add(new HabitSummary(habit.getId(),
                    habit.getHabitText(), habit.getIcon(),
                    habit.getColor(), habit.getUser().getId(),
                    habit.isDone(), habit.getSeries(), habit.getMax_series()));
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
        long incrementedSeries = series + 1;
        long maxSeries = habit.getMax_series();
        if (incrementedSeries > maxSeries) {
            habit.setMax_series(incrementedSeries);
        }
        habit.setSeries(incrementedSeries);
        habit.setDone(true);
        habitRepository.save(habit);
        return ResponseEntity.ok(new ApiResponse(true, "Pomyślnie zmieniono"));
    }

    @Scheduled(cron = "0 0 6 * * *")
    public void resetSeries() {
        List<Habit> habits = habitRepository.findAll();
        int oneDayInMs = 86400000;
        habits.forEach(habit -> {
            Date habitUpdatedAt = Date.from(habit.getUpdatedAt());
            Date now = new Date();
            if (now.getTime() - habitUpdatedAt.getTime() > oneDayInMs) {
                habit.setSeries(0);
            }
            habit.setDone(false);
        });
        habitRepository.saveAll(habits);
    }


}
