package com.itc.private_cloudstorage.controller;

import com.itc.private_cloudstorage.model.User;
import com.itc.private_cloudstorage.dto.RegisterRequest;
import com.itc.private_cloudstorage.repository.UserRepository;
import org.springframework.web.bind.annotation.*;
import com.itc.private_cloudstorage.dto.LoginRequest;
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository userRepository;

    private static final long MB = 1024 * 1024;
    private static final long DEFAULT_QUOTA = 50 * MB;

    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public User register(@RequestBody RegisterRequest request) {
        User user = User.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .displayName(request.getDisplayName())
                .quotaBytes(DEFAULT_QUOTA)
                .usedBytes(0)
                .build();

        return userRepository.save(user);
    }
    @GetMapping("/me")
    public User getProfile() {
    return userRepository.findAll().get(0);
    }

    @PutMapping("/me")
    public User updateProfile(@RequestBody RegisterRequest request) {
    User user = userRepository.findAll()
            .stream()
            .findFirst()
            .orElseThrow(() -> new RuntimeException("No user found"));

    user.setDisplayName(request.getDisplayName());

    return userRepository.save(user);
    }
    @DeleteMapping("/me")
    public String deleteProfile() {

    User user = userRepository.findAll()
            .stream()
            .findFirst()
            .orElseThrow(() -> new RuntimeException("No user found"));

    userRepository.delete(user);

    return "Account deleted successfully";
    }
    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
    User user = userRepository.findByEmail(request.getEmail())
            .orElseThrow(() -> new RuntimeException("User not found"));

    if (!user.getPassword().equals(request.getPassword())) {
        throw new RuntimeException("Invalid password");
    }

    return "Login successful";
    }
}