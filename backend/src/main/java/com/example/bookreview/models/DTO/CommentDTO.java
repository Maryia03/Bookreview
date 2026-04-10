package com.example.bookreview.models.DTO;

import lombok.*;
import java.time.LocalDate;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentDTO{
    private Long id;
    private String content;
    private Long userId;
    private LocalDate createdDate;
    private String username;
    private String avatarUrl;
    private long likesCount;
    private long dislikesCount;
    private String userReaction;
    private Long bookId;
    private String bookTitle;
    private BigDecimal rating;
}