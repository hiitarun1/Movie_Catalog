package com.example.MovieMajor.service;

import com.example.MovieMajor.dto.MovieRequestDto;
import com.example.MovieMajor.dto.MovieResponseDto;
import com.example.MovieMajor.entity.Director;
import com.example.MovieMajor.entity.Movies;
import com.example.MovieMajor.exception.ResourceNotFoundException;
import com.example.MovieMajor.repository.DirectorRepository;
import com.example.MovieMajor.repository.MoviesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MovieServiceImpl implements MoviesService{

    private final MoviesRepository moviesRepository;
    priavte final DirectorRepository directorRepository;

    @Override
    public MovieResponseDto createMovie(MovieRequestDto dto){
        log.info("Creating Movie with title: {}",dto.getMovTitle());
        Director director = directorRepository.findById(dto.getDirId()).orElseThrow(()->new ResourceNotFoundException("Director not found with id: "+dto.getDirId()));
        Movies movie = mapToEntity(dto,director);
        Movies saved = moviesRepository.save(movie);
        log.info("Movie created with id: {}", saved.getMovId());
        return mapToResponseDto(saved);
    }


}
