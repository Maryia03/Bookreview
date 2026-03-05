package com.example.bookreview.repositories;

import com.example.bookreview.models.Rating;
import com.example.bookreview.models.Book;
import com.example.bookreview.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface RatingRepository extends JpaRepository<Rating, Long>{
    Optional<Rating> findByUserAndBook(User user, Book book);
    List<Rating> findByUser(User user);
    List<Rating> findByBook(Book book);
    @Query("SELECT AVG(r.score) FROM Rating r WHERE r.book = :book")
    BigDecimal findAverageScoreByBook(@Param("book") Book book);
    @Query("SELECT COUNT(r) FROM Rating r WHERE r.book = :book")
    Long countByBook(@Param("book") Book book);
    @Modifying @Transactional void deleteAllByUser(User user);
}