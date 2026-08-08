package com.example.MovieMajor.service;

import com.example.MovieMajor.dto.MovieCastRequestDto;
import com.example.MovieMajor.dto.MovieCastResponseDto;

import java.util.List;

public interface MovieCastService {

    MovieCastResponseDto addActorToMovie(MovieCastRequestDto dto);

    List<MovieCastResponseDto> getCastByMovie(Long movId);

    List<MovieCastResponseDto> getMoviesByActor(Long actId);

    MovieCastResponseDto updateRole(Long actId, Long movId, String role);

    List<MovieCastResponseDto> getCoActors(Long actId);

    void removeActorFromMovie(Long actId, Long movId);
}
