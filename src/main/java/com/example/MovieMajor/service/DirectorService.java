package com.example.MovieMajor.service;

import com.example.MovieMajor.dto.DirectorRequestDto;
import com.example.MovieMajor.dto.DirectorResponseDto;
import com.example.MovieMajor.entity.Director;
import com.example.MovieMajor.exception.ResourceNotFoundException;
import com.example.MovieMajor.repository.DirectorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class DirectorService implements DirectorServiceImpl {

    private final DirectorRepository directorRepository;

    @Override
    public DirectorResponseDto createDirector(DirectorRequestDto dto){
        log.info("Creating director with name: {}",dto.getDirName());
        Director director = mapToEntity(dto);
        Director saved = directorRepository.save(director);
        log.info("Director crated with id: {}",saved.getDirId());
        return mapToResponseDto(saved);
    }

    @Override
    public DirectorResponseDto getDirectorById(Long id){
        log.info("fetching director wirth id: {}", id);
        Director director = directorRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Director not found with id: "+ id));
        return mapToResponseDto(director);
    }

    
}
