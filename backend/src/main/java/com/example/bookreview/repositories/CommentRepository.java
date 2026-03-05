package com.example.bookreview.repositories;

import com.example.bookreview.models.Comment;
import com.example.bookreview.models.Book;
import com.example.bookreview.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long>{
    Page<Comment> findByBook(Book book, Pageable pageable);
    List<Comment> findByUser(User user);
    @Modifying @Transactional void deleteAllByUser(User user);
}