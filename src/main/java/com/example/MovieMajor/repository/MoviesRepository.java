package com.example.MovieMajor.repository;

import com.example.MovieMajor.entity.Movies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MoviesRepository extends JpaRepository<Movies,Long> {

    List<Movies> findByMovYear(int movYear);

    List<Movies> findByMovLangIgnoreCase(String movLang);

    List<Movies> findByDirectorId(Long dirId);

    List<Movies> findByMovTitleContainingIgnoreCase(String title);

    List<Movies> findByMovYearAndMovLangIgnoreCase(int movYear, String movLang);

    @Query("Select m FROM Movie m JOIN m.rating r ORDER BY r.revStars DESC")
    List<Movies> findTopRatedMovies();

    @Query("SELECT AVG(r.revStars) FROM Movies m JOIN m.rating r WHERE m.director.id= :dirId")
    Double findAvgRatingByDirector(@Param("dirId") Long dirId);



}
