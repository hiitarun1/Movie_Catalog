package com.example.MovieMajor.repository;

import com.example.MovieMajor.entity.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActorRepository extends JpaRepository<Actor,Long> {
    List<Actor> findByActGender(com.example.MovieMajor.entity.Gender actGender);

    boolean existsByActName(String actName);
}
