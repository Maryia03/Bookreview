package com.example.bookreview.models.DTO;

import lombok.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDetailsDTO{
    private Long id;
    private String title;
    private String author;
    private String description;
    private String coverUrl;
    //rating
    private Double averageRating;
    private Long ratingsCount;
    private Double userRating;
    //comments
    private List<CommentDTO> comments;
    private int currentPage;
    private int totalPages;
    private long totalComments;
}