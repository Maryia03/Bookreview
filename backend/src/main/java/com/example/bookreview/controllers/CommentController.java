package com.example.bookreview.controllers;

import com.example.bookreview.models.Book;
import com.example.bookreview.models.DTO.CommentDTO;
import com.example.bookreview.models.DTO.CommentRequest;
import com.example.bookreview.models.User;
import com.example.bookreview.repositories.BookRepository;
import com.example.bookreview.repositories.UserRepository;
import com.example.bookreview.security.SecurityUtils;
import com.example.bookreview.services.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CommentController{
    private final CommentService commentService;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    @GetMapping("/books/{bookId}/comments")
    public ResponseEntity<Page<CommentDTO>> getCommentsPaged(
            @PathVariable Long bookId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "new") String sortBy){
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));
        String email = SecurityUtils.getCurrentUserEmail();
        User currentUser = null;
        if (email != null){
            currentUser = userRepository.findByEmail(email).orElse(null);
        }
        return ResponseEntity.ok(commentService.getCommentsForBookPaged(book, currentUser, page, size, sortBy)
        );
    }

    @PostMapping("/books/{bookId}/comments")
    public ResponseEntity<CommentDTO> addComment(@PathVariable Long bookId, @RequestBody CommentRequest request){
        String email = SecurityUtils.getCurrentUserEmail();
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));
        CommentDTO response = commentService.addComment(user, book, request.getContent());
        return ResponseEntity.ok(response);
    }
}