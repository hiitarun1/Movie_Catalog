package com.example.MovieMajor.service;

import com.example.MovieMajor.dto.DirectorRequestDto;
import com.example.MovieMajor.dto.DirectorResponseDto;
import com.example.MovieMajor.entity.Director;
import com.example.MovieMajor.exception.ResourceNotFoundException;
import com.example.MovieMajor.repository.DirectorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class DirectorServiceImpl implements DirectorService {

    private final DirectorRepository directorRepository;
    private final TmdbService tmdbService;

    @Override
    public DirectorResponseDto createDirector(DirectorRequestDto dto){
        log.info("Creating director with name: {}",dto.getDirName());
        Director director = mapToEntity(dto);
        String imageUrl = tmdbService.fetchPersonImageUrl(dto.getDirName());
        director.setDirImageUrl(imageUrl);
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

    @Override
    public List<DirectorResponseDto> getAllDirectors() {
        log.info("Fetching all directors");
        return directorRepository.findAll().stream().map(this::mapToResponseDto).collect(Collectors.toList());
    }

    @Override
    public DirectorResponseDto updateDirector(Long id, DirectorRequestDto dto){
        log.info("Updating director with id: {}", id);
        Director director = directorRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("director not found with id: "+  id));
        director.setDirName(dto.getDirName());
        director.setDirPhone(dto.getDirPhone());
        Director updated = directorRepository.save(director);
        log.info("Director updated with id {}", updated.getDirId());
        return mapToResponseDto(updated);
    }

    @Override
    public void deleteDirector(Long id){
        log.info("deleting director with id: {}", id);
        Director director = directorRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Director not found with id: "+id));
        directorRepository.delete(director);
        log.info("director deleted with id: {}",id);
    }

    private Director mapToEntity(DirectorRequestDto dto){
        Director director = new Director();
        director.setDirName(dto.getDirName());
        director.setDirPhone((dto.getDirPhone()));
        return director;
    }

    public DirectorResponseDto fetchAndUpdateImage(Long id) {
        Director director = directorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Director not found with id: " + id));
        String imageUrl = tmdbService.fetchPersonImageUrl(director.getDirName());
        director.setDirImageUrl(imageUrl);
        return mapToResponseDto(directorRepository.save(director));
    }

    private DirectorResponseDto mapToResponseDto(Director director){
        return new DirectorResponseDto(
                director.getDirId(),
                director.getDirName(),
                director.getDirImageUrl(),
                director.getDirPhone()
        );
    }
}
