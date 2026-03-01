package com.example.bookreview.controllers;

import com.example.bookreview.models.User;
import com.example.bookreview.models.DTO.UserDTO;
import com.example.bookreview.models.DTO.CommentDTO;
import com.example.bookreview.models.DTO.RatingDTO;
import com.example.bookreview.security.SecurityUtils;
import com.example.bookreview.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController{
    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<UserDTO> getCurrentUser(){
        String email = SecurityUtils.getCurrentUserEmail();
        if (email == null){
            return ResponseEntity.status(401).build();
        }
        UserDTO dto = userService.getCurrentUser(email);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/me")
    public ResponseEntity<UserDTO> updateProfile(@RequestBody UserDTO request){
        String email = SecurityUtils.getCurrentUserEmail();
        User updated = userService.updateProfile(email, request);
        UserDTO dto = UserDTO.builder()
                .id(updated.getId())
                .username(updated.getUsername())
                .email(updated.getEmail())
                .avatarUrl(updated.getAvatarUrl())
                .createdAt(updated.getCreatedAt())
                .build();

        return ResponseEntity.ok(dto);
    }

    @GetMapping("/ratings")
    public ResponseEntity<List<RatingDTO>> getUserRatings(){
        String email = SecurityUtils.getCurrentUserEmail();
        return ResponseEntity.ok(userService.getUserRatings(email));
    }

    @GetMapping("/comments")
    public ResponseEntity<List<CommentDTO>> getUserComments(){
        String email = SecurityUtils.getCurrentUserEmail();
        return ResponseEntity.ok(userService.getUserComments(email));
    }
}