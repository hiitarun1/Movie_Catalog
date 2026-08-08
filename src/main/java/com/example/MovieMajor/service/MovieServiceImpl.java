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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class MovieServiceImpl implements MoviesService{

    private final MoviesRepository moviesRepository;
    private final DirectorRepository directorRepository;

    @Override
    public MovieResponseDto createMovie(MovieRequestDto dto){
        log.info("Creating Movie with title: {}",dto.getMovTitle());
        Director director = directorRepository.findById(dto.getDirId()).orElseThrow(()->new ResourceNotFoundException("Director not found with id: "+dto.getDirId()));
        Movies movie = mapToEntity(dto,director);
        Movies saved = moviesRepository.save(movie);
        log.info("Movie created with id: {}", saved.getMovId());
        return mapToResponseDto(saved);
    }

    @Override
    public MovieResponseDto getMovieById(Long id){
        log.info("Fetching moovie with id: {}", id);
        Movies movie = moviesRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Movie not found with id: "+ id));
        return mapToResponseDto(movie);
    }

    @Override
    public List<MovieResponseDto> getAllMovies(){
        log.info("Fetching all movies");
        return moviesRepository.findAll().stream().map(this::mapToResponseDto).collect(Collectors.toList());
    }

    @Override
    public List<MovieResponseDto> getMoviesByYear(int year) {
        log.info("Fetching movies by year: {}", year);
        return moviesRepository.findByMovYear(year).stream().map(this::mapToResponseDto).collect(Collectors.toList());
    }

    @Override
    public List<MovieResponseDto> getMoviesByLanguage(String lang){
        log.info("Fetching movies by language: {}", lang);
        return moviesRepository.findByMovLangIgnoreCase(lang).stream().map(this::mapToResponseDto).collect(Collectors.toList());
    }

    @Override
    public List<MovieResponseDto> getMoviesByDirector(Long dirId){
        log.info("Fetching movies by director id: {}", dirId);

        directorRepository.findById(dirId).orElseThrow(()->new ResourceNotFoundException("Director not found with id: "+ dirId));
        return moviesRepository.findByDirectorDirId(dirId).stream().map(this::mapToResponseDto).collect(Collectors.toList());
    }

    @Override
    public List<MovieResponseDto> searchMoviesByTitle(String title){
        log.info("Searching movie with title containing: {}",title);
                return moviesRepository.findByMovTitleContainingIgnoreCase(title).stream().map(this::mapToResponseDto).collect(Collectors.toList());
    }

    @Override
    public List<MovieResponseDto> getTopRatedMovies(){
        log.info("Fetching top rated movies");
        return moviesRepository.findTopRatedMovies().stream().map(this::mapToResponseDto).collect(Collectors.toList());
    }

    @Override
    public double getAvgRatingByDirector(Long dirId){
        log.info("Fetching average rating for director id: {}", dirId);
        directorRepository.findById(dirId).orElseThrow(()->new ResourceNotFoundException("Director not found with id: "+ dirId));
        return moviesRepository.findAvgRatingByDirector(dirId);
    }

    @Override
    public MovieResponseDto updateMovie(Long id, MovieRequestDto dto){
        log.info("updating movie with id: {}", id);
        Movies movie = moviesRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Movie not found with id: "+ id));
        Director director = moviesRepository.findById(dto.getDirId()).orElseThrow(()->new ResourceNotFoundException("Director not found with id: "+ dto.getDirId())).getDirector();
        movie.setMovTitle(dto.getMovTitle());
        movie.setMovYear(dto.getMovYear());
        movie.setMovLang(dto.getMovLang());
        movie.setDirector(director);
        Movies updated = moviesRepository.save(movie);
        log.info("Movie updated with id: {}", updated.getMovId());
        return mapToResponseDto(updated);
    }

    @Override
    public void deleteMovie(Long id){
        log.info("Deleting movie with id: {}", id);
        Movies movie = moviesRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Movie not found with id: "+ id));
        moviesRepository.delete(movie);
        log.info("Movie deleted with id: {}", id);
    }

    private Movies mapToEntity(MovieRequestDto dto, Director director){
        Movies movie = new Movies();
        movie.setMovTitle(dto.getMovTitle());
        movie.setMovYear(dto.getMovYear());
        movie.setMovLang(dto.getMovLang());
        movie.setDirector(director);
        return movie;
    }

    private MovieResponseDto mapToResponseDto(Movies movie){
        return new MovieResponseDto(
                movie.getMovId(),
                movie.getMovTitle(),
                movie.getMovYear(),
                movie.getMovLang(),
                movie.getDirector().getDirId(),
                movie.getDirector().getDirName()
        );
    }
}
