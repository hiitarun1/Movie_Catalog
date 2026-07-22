package com.example.MovieMajor.controller;

import com.example.MovieMajor.dto.DirectorRequestDto;
import com.example.MovieMajor.dto.DirectorResponseDto;
import com.example.MovieMajor.service.DirectorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/directors")
@RequiredArgsConstructor
public class DirectorController {

    private final DirectorService directorService;

    @PostMapping
    public ResponseEntity<DirectorResponseDto> createDirector(@Valid @RequestBody DirectorRequestDto dto){
        return new ResponseEntity<>(directorService.createDirector(dto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DirectorResponseDto> getDirectorById(@PathVariable Long id){
        return ResponseEntity.ok(directorService.getDirectorById(id));
    }

    @GetMapping
    public ResponseEntity<List<DirectorResponseDto>> getAllDirectors(){
        return ResponseEntity.ok(directorService.getAllDirectors());
    }

    @PutMapping("/{id}")
    public ResponseEntity<DirectorResponseDto> updateDirector(@PathVariable Long id, @Valid @RequestBody DirectorRequestDto dto){
        return ResponseEntity.ok(directorService.updateDirector(id,dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDirector(@PathVariable Long id){
        directorService.deleteDirector(id);
        return ResponseEntity.noContent().build();
    }

}
