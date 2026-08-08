package com.example.MovieMajor.repository;

import com.example.MovieMajor.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RatingRepository extends JpaRepository<Rating, Long> {

    Optional<Rating> findByMoviesMovId(Long movId);

    boolean existsByMoviesMovId(Long movId);

    List<Rating> findByRevStarsGreaterThanEqual(Double minStars);

    @Query("SELECT r FROM Rating r ORDER BY r.revStars DESC")
    List<Rating> findAllOrderByRevStarsDesc();
}
