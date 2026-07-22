package com.example.MovieMajor.controller;


import com.example.MovieMajor.dto.MovieRequestDto;
import com.example.MovieMajor.dto.MovieResponseDto;
import com.example.MovieMajor.service.MoviesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.PreparedStatement;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/Movies")
public class MovieController {

    private final MoviesService moviesService;

    @PostMapping
    public ResponseEntity<MovieResponseDto> createMovie(@Valid @RequestBody MovieRequestDto dto){
        return new ResponseEntity<>(moviesService.createMovie(dto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieResponseDto> getMovieById(@PathVariable Long id){
        return ResponseEntity.ok(moviesService.getMovieById(id));
    }

    @GetMapping
    public ResponseEntity<List<MovieResponseDto>> getAllMovies(
            @RequestParam(required = false) Integer year,
            @RequestParam(required = false) String lang,
            @RequestParam(required = false) Long dirId,
            @RequestParam(required = false) String title){
        if(year!=null) return ResponseEntity.ok(moviesService.getMoviesByYear((year));
        if(lang!=null) return ResponseEntity.ok(moviesService.getMoviesByLanguage(lang));
        if(dirId!=null) return ResponseEntity.ok(moviesService.getMoviesByDirector(dirId));
        if(title!=null) return ResponseEntity.ok(moviesService.searchMoviesByTitle(title));
        return ResponseEntity.ok(moviesService.getAllMovies());
    }

    @GetMapping("/top-rated")
    public ResponseEntity<List<MovieResponseDto>> getTopRatedMovies(){
        return ResponseEntity.ok(moviesService.getTopRatedMovies());
    }

    @GetMapping("/director/{dirId}/avg-rating")
    public ResponseEntity<Double> getAvgRatingByDirector(@PathVariable Long dirId){
        return ResponseEntity.ok(moviesService.getAvgRattingByDirector(dirId));
    }

    @PutMapping("/{id]")
    public ResponseEntity<MovieResponseDto> updateMovie(
            @PathVariable Long id,
            @Valid @RequestBody MovieRequestDto dto){
        return ResponseEntity.ok(moviesService.updateMovie(id,dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id){
        return ResponseEntity.noContent().build();
    }
}
