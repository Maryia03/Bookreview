package com.example.bookreview.models.DTO;

import lombok.*;

@Getter
public class RegisterRequest {
    private String email;
    private String username;
    private String password;
}
