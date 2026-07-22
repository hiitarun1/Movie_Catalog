package com.example.MovieMajor.repository;

import com.example.MovieMajor.entity.Movies;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MoviesRepository extends JpaRepository<Movies,Long> {

    List<Movies> findByMovYear(int movYear);

    List<Movies> findByMovLangIgnoreCase(String movLang);

}
