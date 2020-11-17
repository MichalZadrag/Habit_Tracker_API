package com.example.habit_tracker_api.controller;

import com.example.habit_tracker_api.exception.AppException;
import com.example.habit_tracker_api.model.Task;
import com.example.habit_tracker_api.model.User;
import com.example.habit_tracker_api.payload.AddTaskRequest;
import com.example.habit_tracker_api.payload.ApiResponse;
import com.example.habit_tracker_api.payload.TaskSummary;
import com.example.habit_tracker_api.repository.TaskRepository;
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
@RequestMapping("/api/task")
public class TaskController {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/add")
    public ResponseEntity<?> addTask(@Valid @RequestBody AddTaskRequest addTaskRequest) {

        Task task = new Task(addTaskRequest.getTask_text(), addTaskRequest.getColor(), addTaskRequest.getDate());

        User user = userRepository.findById(addTaskRequest.getUser_id())
                .orElseThrow(() -> new AppException("Nie znaleziono uzytkownika "));

        task.setUser(user);

        taskRepository.save(task);

        return ResponseEntity.ok(new ApiResponse(true, "Dodano nowe zadanie"));
    }

    @GetMapping("/all/{user_id}")
    public List<?> getAllTasks(@PathVariable(name = "user_id") Long id) {

        List<Task> tasks = taskRepository.findAll();
        List<TaskSummary> taskSummaries = new ArrayList<>();

        tasks.forEach(task -> {
            taskSummaries.add(new TaskSummary(task.getId(), task.getTask_text(),
                    task.getColor(), task.getUser().getId(), task.getDate()));
        });

        Predicate<TaskSummary> byUserId = taskSummary -> taskSummary.getUser_id() == id;


        return taskSummaries.stream().filter(byUserId).collect(Collectors.toList());
    }

    @DeleteMapping("/delete/{task_id}")
    public ResponseEntity<?> deleteTask(@PathVariable(name = "task_id") Long id) {
        taskRepository.deleteById(id);
        return ResponseEntity.ok(new ApiResponse(true, "Pomyślnie usunięto"));
    }
}
