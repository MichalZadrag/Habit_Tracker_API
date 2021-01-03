package com.example.habit_tracker_api.controller;


import com.example.habit_tracker_api.exception.AppException;
import com.example.habit_tracker_api.model.Role;
import com.example.habit_tracker_api.model.RoleName;

import com.example.habit_tracker_api.payload.ApiResponse;
import com.example.habit_tracker_api.payload.SignUpRequest;
import com.example.habit_tracker_api.payload.UserIdentityAvailability;
import com.example.habit_tracker_api.payload.UserSummary;
import com.example.habit_tracker_api.repository.RoleRepository;
import com.example.habit_tracker_api.repository.UserRepository;
import com.example.habit_tracker_api.security.CurrentUser;
import com.example.habit_tracker_api.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

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

        return new UserSummary(currentUser.getId(), currentUser.getUsername(),
                                                  currentUser.getFirst_name(),
                                                  currentUser.getLast_name(), currentUser.getEmail());
    }

    @DeleteMapping("/delete/{user_id}")
    public ResponseEntity<?> deleteUser(@PathVariable(name = "user_id") Long id) {

        userRepository.deleteById(id);
        return ResponseEntity.ok(new ApiResponse(true, "Pomyślnie usunięto"));
    }

    @PutMapping("/update/{user_id}")
    public ResponseEntity<?> updateUser(@PathVariable(name = "user_id") Long id, @Valid @RequestBody SignUpRequest newDataRequest) {

        if(!userRepository.findById(id).get().getUsername().equals(newDataRequest.getUsername())) {
            if (userRepository.existsByUsername(newDataRequest.getUsername())) {
                return new ResponseEntity(new ApiResponse(false, "Podany nick jest zajęty"),
                        HttpStatus.BAD_REQUEST);
            }
        }
        if(!userRepository.findById(id).get().getEmail().equals(newDataRequest.getEmail())) {
            if (userRepository.existsByEmail(newDataRequest.getEmail())) {
                return new ResponseEntity(new ApiResponse(false, "Podany email jest zajęty"),
                        HttpStatus.BAD_REQUEST);
            }
        }

        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new AppException("Rola użytkownika nie ustawiona"));



        userRepository.findById(id)
                .map(newUser -> {
                    newUser.setFirst_name(newDataRequest.getFirst_name());
                    newUser.setLast_name(newDataRequest.getLast_name());
                    newUser.setUsername(newDataRequest.getUsername());
                    newUser.setEmail(newDataRequest.getEmail());
                    newUser.setPassword(newDataRequest.getPassword());
                    newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
                    newUser.setRole(userRole);
                    return userRepository.save(newUser);
                });

        return ResponseEntity.ok(new ApiResponse(true, "Pomyślnie edytowano"));
    }

}
