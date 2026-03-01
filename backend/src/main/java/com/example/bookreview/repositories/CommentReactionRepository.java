package com.example.bookreview.repositories;

import com.example.bookreview.models.CommentReaction;
import com.example.bookreview.models.Comment;
import com.example.bookreview.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CommentReactionRepository extends JpaRepository<CommentReaction, Long>{
    Optional<CommentReaction> findByUserAndComment(User user, Comment comment);
}