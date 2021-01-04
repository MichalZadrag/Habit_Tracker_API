package com.example.habit_tracker_api.controller;

import com.example.habit_tracker_api.exception.AppException;
import com.example.habit_tracker_api.model.Role;
import com.example.habit_tracker_api.model.RoleName;
import com.example.habit_tracker_api.model.User;
import com.example.habit_tracker_api.payload.ApiResponse;
import com.example.habit_tracker_api.payload.JwtAuthenticationResponse;
import com.example.habit_tracker_api.payload.LoginRequest;
import com.example.habit_tracker_api.payload.SignUpRequest;
import com.example.habit_tracker_api.repository.RoleRepository;
import com.example.habit_tracker_api.repository.UserRepository;
import com.example.habit_tracker_api.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;


    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail(),
                        loginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity(new ApiResponse(false, "Podany nick jest zajęty"),
                    HttpStatus.BAD_REQUEST);
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity(new ApiResponse(false, "Podany email jest zajęty"),
                    HttpStatus.BAD_REQUEST);
        }

        User user = new User(signUpRequest.getFirst_name(),
                signUpRequest.getLast_name(),
                signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                passwordEncoder.encode(signUpRequest.getPassword()));


        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new AppException("Rola użytkownika nie ustawiona"));

        user.setRole(userRole);

        userRepository.save(user);

        return ResponseEntity.ok(new ApiResponse(true, "Rejestracja przebiegła pomyślnie"));
    }


}