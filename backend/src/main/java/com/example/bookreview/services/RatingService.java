package com.example.bookreview.services;

import com.example.bookreview.models.Book;
import com.example.bookreview.models.Rating;
import com.example.bookreview.models.User;
import com.example.bookreview.models.DTO.RatingDTO;
import com.example.bookreview.repositories.RatingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RatingService{
    private final RatingRepository ratingRepository;
    public RatingDTO rateBook(User user, Book book, BigDecimal score){
        if (user.isBlocked()){
            throw new RuntimeException("Blocked users cannot rate books");
        }
        validateScore(score);
        Rating rating = ratingRepository.findByUserAndBook(user, book).orElse(Rating.builder()
                        .user(user)
                        .book(book)
                        .build());

        rating.setScore(score);
        rating = ratingRepository.save(rating);
        return RatingDTO.builder()
                .id(rating.getId())
                .value(rating.getScore())
                .username(user.getUsername())
                .bookId(rating.getBook().getId())
                .bookTitle(rating.getBook().getTitle())
                .build();
    }

    private void validateScore(BigDecimal score){
        if (score == null){
            throw new IllegalArgumentException("Score cannot be null");
        }
        if (score.compareTo(BigDecimal.valueOf(0.5)) < 0 || score.compareTo(BigDecimal.valueOf(10.0)) > 0){
            throw new IllegalArgumentException("Score must be between 0.5 and 10");
        }
        if (score.remainder(BigDecimal.valueOf(0.5)).compareTo(BigDecimal.ZERO) != 0){
            throw new IllegalArgumentException("Score must be in steps of 0.5");
        }
    }
}