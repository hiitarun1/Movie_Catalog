package com.example.MovieMajor.controller;

import com.example.MovieMajor.dto.MovieCastRequestDto;
import com.example.MovieMajor.dto.MovieCastResponseDto;
import com.example.MovieMajor.service.MovieCastService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/movie-cast")
@RequiredArgsConstructor
public class MovieCastController {

    private final MovieCastService movieCastService;

    @PostMapping
    public ResponseEntity<MovieCastResponseDto> addActorToMovie(@Valid @RequestBody MovieCastRequestDto dto){
        return new ResponseEntity<>(movieCastService.addActorToMovie(dto), HttpStatus.CREATED);
    }

    @GetMapping("/movie/{movId}")
    public ResponseEntity<List<MovieCastResponseDto>> getCastByMovie(@PathVariable Long movId){
        return ResponseEntity.ok(movieCastService.getCastByMovie(movId));
    }

    @GetMapping("/actors/{actId}")
    public ResponseEntity<List<MovieCastResponseDto>> getMoviesByActor(@PathVariable Long actId){
        return ResponseEntity.ok(movieCastService.getMoviesByActor(actId));
    }

    @GetMapping("actor/{actId}/co-actors")
    public ResponseEntity<List<MovieCastResponseDto>> getCoActors(@PathVariable Long actId){
        return ResponseEntity.ok(movieCastService.getCoActors(actId));
    }

    @PutMapping("{actId}/{movId}")
        public ResponseEntity<MovieCastResponseDto> updateRole(@PathVariable Long actId, @PathVariable Long movId, @RequestParam String role){
            return ResponseEntity.ok(movieCastService.updateRole(actId,movId,role));
        }

    @DeleteMapping("/{actId}/{movId}")
    public ResponseEntity<Void> removeActorFromMovie(@PathVariable Long actId, @PathVariable Long movId){
        movieCastService.removeActorFromMovie(actId,movId);
        return ResponseEntity.noContent().build();
    }
}
