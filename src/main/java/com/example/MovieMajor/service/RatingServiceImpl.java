package com.example.MovieMajor.service;

import com.example.MovieMajor.dto.RatingRequestDto;
import com.example.MovieMajor.dto.RatingResponseDto;
import com.example.MovieMajor.entity.Movies;
import com.example.MovieMajor.entity.Rating;
import com.example.MovieMajor.exception.DuplicateResourceException;
import com.example.MovieMajor.exception.ResourceNotFoundException;
import com.example.MovieMajor.repository.MoviesRepository;
import com.example.MovieMajor.repository.RatingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService{

    private final RatingRepository ratingRepository;
    private final MoviesRepository moviesRepository;

    @Override
    public RatingResponseDto addRating(RatingRequestDto dto) {
        log.info("Adding rating for movie id: {}",dto.getMovId());

        if(ratingRepository.existsByMoviesMovId(dto.getMovId())){
            throw new DuplicateResourceException("Rating already exists for movie id: "+ dto.getMovId());
        }

        Movies movie = moviesRepository.findById(dto.getMovId()).orElseThrow(()->new ResourceNotFoundException("Movie not found with id: " + dto.getMovId()));

        Rating rating = new Rating();
        //rating.setMovies(movie);
        rating.setMovies(movie);
        rating.setRevStars(dto.getRevStars());

        Rating saved = ratingRepository.save(rating);
        log.info("Rating added for movie if: " + saved.getMovId());
        return mapToResponseDto(saved);
    }

    @Override
    public RatingResponseDto getRatingByMovieId(Long movId) {

        log.info("Fetching rating for movie id: {}", movId);
        Rating rating = ratingRepository.findByMoviesMovId(movId).orElseThrow(()->new ResourceNotFoundException("Rating not found for movie id: " + movId));
        return mapToResponseDto(rating);
    }

    @Override
    public List<RatingResponseDto> getAllRatings() {
        log.info("Fetching all ratings");
        return ratingRepository.findAll().stream().map(this::mapToResponseDto).collect(Collectors.toList());
    }

    @Override
    public List<RatingResponseDto> getRatingsByMinStars(Double minStars) {
        log.info("fetching ratings with minimum stars: {}",minStars);
        return ratingRepository.findByRevStarsGreaterThanEqual(minStars).stream().map(this::mapToResponseDto).collect(Collectors.toList());
    }

    @Override
    public List<RatingResponseDto> getTopRatedMovies() {
        log.info("Fetching all ratings ordered by stars descending");
        return ratingRepository.findAllOrderByRevStarsDesc().stream().map(this::mapToResponseDto).collect(Collectors.toList());
    }

    @Override
    public RatingResponseDto updateRating(Long movId, Double revStars) {
        log.info("Updating rating for movie id: {}", movId);
        Rating rating = ratingRepository.findByMoviesMovId(movId).orElseThrow(()->new ResourceNotFoundException("Rating not found for movie id: " + movId));
        rating.setRevStars(revStars);
        Rating updated = ratingRepository.save(rating);
        log.info("Rating updated for movie id: {}",movId);
        return mapToResponseDto(updated);
    }

    @Override
    public void deleteRating(Long movId) {
        log.info("Deleting rating for movie id: {}", movId);
        Rating rating = ratingRepository.findByMoviesMovId(movId).orElseThrow(()->new ResourceNotFoundException("Rating not found movie id: " + movId));
        ratingRepository.delete(rating);
        log.info("Rating deleted for movie id: {}", movId);
    }

    private RatingResponseDto mapToResponseDto(Rating rating){
        return new RatingResponseDto(
                rating.getMovId(),
                rating.getMovies().getMovTitle(),
                rating.getRevStars()
        );
    }
}
