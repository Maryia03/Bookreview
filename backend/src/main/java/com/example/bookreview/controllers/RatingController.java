package com.example.bookreview.controllers;

import com.example.bookreview.models.Book;
import com.example.bookreview.models.DTO.RatingDTO;
import com.example.bookreview.models.DTO.RatingRequest;
import com.example.bookreview.models.User;
import com.example.bookreview.repositories.BookRepository;
import com.example.bookreview.repositories.UserRepository;
import com.example.bookreview.security.SecurityUtils;
import com.example.bookreview.services.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books/{bookId}/ratings")
@RequiredArgsConstructor
public class RatingController{
    private final RatingService ratingService;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    @PostMapping
    public ResponseEntity<RatingDTO> rateBook(@PathVariable Long bookId, @RequestBody RatingRequest request){
        String email = SecurityUtils.getCurrentUserEmail();
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));
        RatingDTO response = ratingService.rateBook(user, book, request.getScore());
        return ResponseEntity.ok(response);
    }
}