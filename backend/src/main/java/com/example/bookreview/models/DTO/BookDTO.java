package com.example.bookreview.models.DTO;

import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDTO{
    private Long id;
    private String title;
    private String author;
    private String description;
    private String coverUrl;
    private Double averageRating;
    private BigDecimal stars;
    private Long ratingsCount;
}