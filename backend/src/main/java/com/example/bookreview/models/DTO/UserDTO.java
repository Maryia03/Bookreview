package com.example.bookreview.models.DTO;

import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO{
    private Long id;
    private String username;
    private String email;
    private String avatarUrl;
    private LocalDate createdAt;
}