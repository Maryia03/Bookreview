package com.example.bookreview.models.DTO;

import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RatingDTO{
    private Long id;
    private BigDecimal value;
    private String username;
    private Long bookId;
    private String bookTitle;
}