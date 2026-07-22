package com.example.MovieMajor.repository;

import com.example.MovieMajor.entity.Director;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DirectorRepository extends JpaRepository<Director,Long> {

    boolean existsByDirName(String dirName);

    List<Director> findByDirNameContainingIgnoreCase(String name);
}
