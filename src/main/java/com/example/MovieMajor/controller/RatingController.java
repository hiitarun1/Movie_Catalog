package com.example.MovieMajor.controller;

import com.example.MovieMajor.dto.RatingRequestDto;
import com.example.MovieMajor.dto.RatingResponseDto;
import com.example.MovieMajor.service.RatingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ratings")
@RequiredArgsConstructor
public class RatingController {

    private final RatingService ratingService;

    @PostMapping
    public ResponseEntity<RatingResponseDto> addRating(@Valid @RequestBody RatingRequestDto dto){
        return new ResponseEntity(ratingService.addRating(dto), HttpStatus.CREATED);
    }

    @GetMapping("/movie/{movId}")
    public ResponseEntity<RatingResponseDto> getRatingByMovieId(@PathVariable Long movId){
        return ResponseEntity.ok(ratingService.getRatingByMovieId(movId));
    }

    @GetMapping
    public ResponseEntity<List<RatingResponseDto>> getAllRatings(@RequestParam(required = false) Double minStars){
        if(minStars!=null){
            return new ResponseEntity(ratingService.getRatingsByMinStars(minStars), HttpStatus.OK);
        }
        return ResponseEntity.ok(ratingService.getAllRatings());
    }

    @GetMapping("/top-rated")
    public ResponseEntity<List<RatingResponseDto>> getTopRatedMovies(){
        return ResponseEntity.ok(ratingService.getTopRatedMovies());
    }

    @PutMapping("/movie/{movId}")
    public ResponseEntity<RatingResponseDto> updateRating(@PathVariable Long movId, @RequestParam Double revStars){
     return ResponseEntity.ok(ratingService.updateRating(movId,revStars));
    }

    @DeleteMapping("/movie/{movId}")
    public ResponseEntity<Void> deleteRating(@PathVariable Long movId){
        ratingService.deleteRating(movId);
        return ResponseEntity.noContent().build();
    }


}
