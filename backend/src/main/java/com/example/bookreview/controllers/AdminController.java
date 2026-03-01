package com.example.bookreview.controllers;

import com.example.bookreview.models.Book;
import com.example.bookreview.repositories.BookRepository;
import com.example.bookreview.services.CommentService;
import com.example.bookreview.models.DTO.BookDTO;
import com.example.bookreview.models.DTO.BookDetailsDTO;
import com.example.bookreview.services.BookService;
import com.example.bookreview.services.UserService;
import com.example.bookreview.models.DTO.UserDTO;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminController{
    private final UserService userService;
    private final CommentService commentService;
    private final BookService bookService;

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping("/books")
    public ResponseEntity<BookDTO> createBook(@RequestBody BookDTO dto){
        return ResponseEntity.ok(bookService.createBook(dto));
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id){
        bookService.deleteBook(id);
        return ResponseEntity.ok("Book deleted successfully");
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable Long id, @RequestBody BookDTO dto){
        return ResponseEntity.ok(bookService.updateBook(id, dto));
    }

    @DeleteMapping("/comments/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id){
        commentService.deleteComment(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/users/{id}/block")
    public ResponseEntity<?> blockUser(@PathVariable Long id){
        userService.blockUser(id);
        return ResponseEntity.ok("User blocked successfully");
    }

    @PutMapping("/users/{id}/unblock")
    public ResponseEntity<?> unblockUser(@PathVariable Long id){
        userService.unblockUser(id);
        return ResponseEntity.ok("User unblocked successfully");
    }
}