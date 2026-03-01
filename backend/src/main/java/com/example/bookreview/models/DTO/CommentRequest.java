package com.example.bookreview.models.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentRequest{
    @NotBlank(message = "Comment cannot be empty")
    private String content;
}