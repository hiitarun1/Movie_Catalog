package com.example.MovieMajor.service;

import com.example.MovieMajor.dto.RatingRequestDto;
import com.example.MovieMajor.dto.RatingResponseDto;
import java.util.List;

public interface RatingService {

    RatingResponseDto addRating(RatingRequestDto dto);

    RatingResponseDto getRatingByMovieId(Long movId);

    List<RatingResponseDto> getAllRatings();

    List<RatingResponseDto> getRatingsByMinStars(Double minStars);

    List<RatingResponseDto> getTopRatedMovies();

    RatingResponseDto updateRating(Long movId, Double revStars);

    void deleteRating(Long movId);
}
