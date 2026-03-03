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
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class AdminController{
    private final UserService userService;
    private final CommentService commentService;
    private final BookService bookService;

    @GetMapping("/books")
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping("/books")
    public ResponseEntity<BookDTO> createBook(@RequestBody BookDTO dto){
        return ResponseEntity.ok(bookService.createBook(dto));
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<Map<String, String>> deleteBook(@PathVariable Long id){
        bookService.deleteBook(id);
        return ResponseEntity.ok(Map.of("message", "Book deleted successfully"));
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
    public ResponseEntity<Map<String, Boolean>> blockUser(@PathVariable Long id){
        userService.blockUser(id);
        Map<String, Boolean> response = Map.of("blocked", true);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/users/{id}/unblock")
    public ResponseEntity<Map<String, Boolean>> unblockUser(@PathVariable Long id){
        userService.unblockUser(id);
        Map<String, Boolean> response = Map.of("blocked", false);
        return ResponseEntity.ok(response);
    }
}