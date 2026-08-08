package com.example.MovieMajor.service;

import com.example.MovieMajor.dto.MovieCastRequestDto;
import com.example.MovieMajor.dto.MovieCastResponseDto;

import com.example.MovieMajor.entity.Actor;
import com.example.MovieMajor.entity.MovieCast;
import com.example.MovieMajor.entity.MovieCastId;
import com.example.MovieMajor.entity.Movies;
import com.example.MovieMajor.exception.DuplicateResourceException;
import com.example.MovieMajor.exception.ResourceNotFoundException;
import com.example.MovieMajor.repository.ActorRepository;
import com.example.MovieMajor.repository.MovieCastRepository;
import com.example.MovieMajor.repository.MoviesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class MovieCastServiceImpl implements MovieCastService{

        private final MovieCastRepository movieCastRepository;
        private final ActorRepository actorRepository;
        private final MoviesRepository moviesRepository;

        @Override
        public MovieCastResponseDto addActorToMovie(MovieCastRequestDto dto){
            log.info("Adding actor {} to movie {} with role: {}", dto.getActId(), dto.getMovId(), dto.getRole());

            if(movieCastRepository.existsByActorActIdAndMoviesMovId(dto.getActId(),dto.getMovId())){
                throw new DuplicateResourceException("Actor"+ dto.getActId()+" is already cast in movie "+ dto.getMovId());
            }
            Actor actor = actorRepository.findById(dto.getActId()).orElseThrow(()->new ResourceNotFoundException("Actor not found with id: "+dto.getActId()));
            Movies movies = moviesRepository.findById(dto.getMovId()).orElseThrow(()->new ResourceNotFoundException("Movie not found with id: "+ dto.getMovId()));
            MovieCast movieCast = new MovieCast(actor,movies,dto.getRole());
            MovieCast saved = movieCastRepository.save(movieCast);
            log.info("Actor {} added to movie {} successfully", dto.getActId(), dto.getMovId());
            return mapToResponseDto(saved);
        }

    @Override
    public List<MovieCastResponseDto> getCastByMovie(Long movId) {
        log.info("FEtching cast for movie id: {}", movId);
        moviesRepository.findById(movId).orElseThrow(()->new ResourceNotFoundException("Movie not found with if: " + movId));
        return movieCastRepository.findByMoviesMovId(movId).stream().map(this::mapToResponseDto).collect(Collectors.toList());
    }

    @Override
    public List<MovieCastResponseDto> getMoviesByActor(Long actId) {
        log.info("Fetching movies for actor id: {}", actId);
        actorRepository.findById(actId).orElseThrow(()->new ResourceNotFoundException("Actor not found with id: "+ actId));
        return movieCastRepository.findByActorActId(actId).stream().map(this::mapToResponseDto).collect(Collectors.toList());
    }

    @Override
    public MovieCastResponseDto updateRole(Long actId, Long movId, String role) {
        log.info("Updating role for actor {} in movie {}", actId, movId);
        MovieCast movieCast = movieCastRepository.findByActorIdAndMovieId(actId,movId).orElseThrow(()->new ResourceNotFoundException("Cast entry not found for actor "+ actId + "in movie "+ movId));
        movieCast.setRole(role);
        MovieCast updated = movieCastRepository.save(movieCast);
        log.info("Role updated for actor {} in movie {}", actId, movId);
        return mapToResponseDto(updated);
    }

    @Override
    public List<MovieCastResponseDto> getCoActors(Long actId) {
        log.info("Fetching co-actors for actor id: {}", actId);
        actorRepository.findById(actId).orElseThrow(()->new ResourceNotFoundException("Actor not found with id: "+ actId));
        return movieCastRepository.findCoActors(actId)
                .stream().map(this::mapToResponseDto).collect(Collectors.toList());
    }

    @Override
    public void removeActorFromMovie(Long actId, Long movId) {
            log.info("Removing actor {} from movie {}", actId, movId);
            MovieCast movieCast = movieCastRepository.findByActorIdAndMovieId(actId, movId).orElseThrow(()->new ResourceNotFoundException("Cast entry not found for actor "+ actId+ "in movie "+ movId ));
            movieCastRepository.delete(movieCast);
            log.info("Actor {} removed from movie {}", actId, movId);
    }

    private MovieCastResponseDto mapToResponseDto(MovieCast mc){
            return new MovieCastResponseDto(
                    mc.getActor().getActId(),
                    mc.getActor().getActName(),
                    mc.getMovies().getMovId(),
                    mc.getMovies().getMovTitle(),
                    mc.getRole()
            );
    }
}
