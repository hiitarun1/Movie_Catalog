package com.example.MovieMajor.service;

import com.example.MovieMajor.dto.MovieRequestDto;
import com.example.MovieMajor.dto.MovieResponseDto;

import java.util.List;

public interface MoviesService {

    MovieResponseDto createMovie(MovieRequestDto dto);

    MovieResponseDto getMovieById(Long id);

    List<MovieResponseDto> getAllMovies();

    List<MovieResponseDto> getMoviesByYear(int year);

    List<MovieResponseDto> getMoviesByLanguage(String lang);

    List<MovieResponseDto> getMoviesByDirector(Long dirId);

    List<MovieResponseDto> searchMoviesByTitle(String title);

    double getAvgRattingByDirector(Long dirId);

    MovieResponseDto updateMovie(Long id, MovieRequestDto dto);

    void deleteMovie(Long id);
}
