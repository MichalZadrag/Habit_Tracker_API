package com.example.habit_tracker_api.controller;


import com.example.habit_tracker_api.payload.ApiResponse;
import com.example.habit_tracker_api.payload.UserIdentityAvailability;
import com.example.habit_tracker_api.payload.UserSummary;
import com.example.habit_tracker_api.repository.EventRepository;
import com.example.habit_tracker_api.repository.HabitRepository;
import com.example.habit_tracker_api.repository.UserRepository;
import com.example.habit_tracker_api.security.CurrentUser;
import com.example.habit_tracker_api.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private HabitRepository habitRepository;


    @GetMapping("/checkUsernameAvailability")
    public UserIdentityAvailability checkUsernameAvailability(@RequestParam(value = "username") String username) {
        Boolean isAvailable = !userRepository.existsByUsername(username);
        return new UserIdentityAvailability(isAvailable);
    }

    @GetMapping("/checkEmailAvailability")
    public UserIdentityAvailability checkEmailAvailability(@RequestParam(value = "email") String email) {
        Boolean isAvailable = !userRepository.existsByEmail(email);
        return new UserIdentityAvailability(isAvailable);
    }

    @GetMapping("/me")
    public UserSummary getCurrentUser(@CurrentUser UserPrincipal currentUser) {
        UserSummary userSummary = new UserSummary(currentUser.getId(), currentUser.getUsername(), currentUser.getFirst_name(), currentUser.getLast_name(), currentUser.getEmail());
        return userSummary;
    }

    @DeleteMapping("/delete/{user_id}")
    public ResponseEntity<?> deleteUser(@PathVariable(name = "user_id") Long id) {

        userRepository.deleteById(id);
        return ResponseEntity.ok(new ApiResponse(true, "Pomyślnie usunięto"));
    }


}
