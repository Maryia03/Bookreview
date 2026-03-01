package com.example.bookreview.controllers;

import com.example.bookreview.models.DTO.LoginRequest;
import com.example.bookreview.models.DTO.RegisterRequest;
import com.example.bookreview.models.Role;
import com.example.bookreview.models.User;
import com.example.bookreview.repositories.UserRepository;
import com.example.bookreview.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request){
        if (userRepository.existsByEmail(request.getEmail())){
            throw new RuntimeException("Email already exists");
        }

        User user = User.builder()
                .email(request.getEmail())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ROLE_USER)
                .build();
        userRepository.save(user);
        return jwtService.generateToken(user.getEmail(), user.getRole().name());
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request){
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new RuntimeException("Invalid password");
        }
        return jwtService.generateToken(user.getEmail(), user.getRole().name());
    }
}