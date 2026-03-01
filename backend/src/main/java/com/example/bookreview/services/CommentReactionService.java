package com.example.bookreview.services;

import com.example.bookreview.models.Comment;
import com.example.bookreview.models.CommentReaction;
import com.example.bookreview.models.User;
import com.example.bookreview.repositories.CommentReactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentReactionService{
    private final CommentReactionRepository reactionRepository;
    public CommentReaction reactToComment(User user, Comment comment, int value){
        if (value != 1 && value != -1){
            throw new IllegalArgumentException("Reaction must be 1 (like) or -1 (dislike)");
        }

        CommentReaction reaction = reactionRepository.findByUserAndComment(user, comment).orElse(CommentReaction.builder()
                        .user(user)
                        .comment(comment)
                        .build());
        reaction.setValue(value);
        return reactionRepository.save(reaction);
    }
}