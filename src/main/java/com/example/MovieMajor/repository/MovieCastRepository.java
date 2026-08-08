package com.example.MovieMajor.repository;

import com.example.MovieMajor.entity.MovieCast;
import com.example.MovieMajor.entity.MovieCastId;
import org.antlr.v4.runtime.atn.SemanticContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieCastRepository extends JpaRepository<MovieCast, MovieCastId> {


    List<MovieCast> findByMoviesMovId(Long movId);

    List<MovieCast> findByActorActId(Long actId);

    boolean existsByActorActIdAndMoviesMovId(Long actId, Long MovId);

    @Query("SELECT DISTINCT mc2 FROM MovieCast mc1 JOIN MovieCast mc2 ON mc1.movies.movId = mc2.movies.movId WHERE mc1.actor.actId = :actId AND mc2.actor.actId <> :actId")
    List<MovieCast> findCoActors(@Param("actId") Long actId);

    @Query("SELECT mc FROM MovieCast mc WHERE mc.actor.actId = :actId AND mc.movies.movId = :movId")
    java.util.Optional<MovieCast> findByActorIdAndMovieId(@Param("actId")Long actId, @Param("movId")Long movId);
}


