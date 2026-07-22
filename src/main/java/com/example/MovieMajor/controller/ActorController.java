package com.example.MovieMajor.controller;

import com.example.MovieMajor.dto.ActorRequestDto;
import com.example.MovieMajor.dto.ActorResponseDto;
import com.example.MovieMajor.service.ActorServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/actors")
@RequiredArgsConstructor
public class ActorController {

    private final ActorServiceImpl actorService;

    @PostMapping
    public ResponseEntity<ActorResponseDto> createActor(@Valid @RequestBody ActorRequestDto dto){
        return new ResponseEntity<>(actorService.createActor(dto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ActorResponseDto> getActorById(@PathVariable long id){
        return ResponseEntity.ok(actorService.getActorById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ActorResponseDto>> getAllActors(){
        return ResponseEntity.ok(actorService.getAllActors());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ActorResponseDto> updateActor(@PathVariable Long id, @Valid @RequestBody ActorRequestDto dto){
        return ResponseEntity.ok(actorService.updateActor(id,dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteActor(@PathVariable Long id){
        actorService.deleteActor(id);
        return ResponseEntity.noContent().build();
    }
}
