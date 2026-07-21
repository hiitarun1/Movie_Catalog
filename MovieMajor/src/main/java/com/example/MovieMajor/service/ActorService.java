package com.example.MovieMajor.service;

import com.example.MovieMajor.dto.ActorRequestDto;
import com.example.MovieMajor.dto.ActorResponseDto;

import java.util.List;

public interface ActorService {

    ActorResponseDto createActor(ActorRequestDto dto);

    ActorResponseDto getActorById(Long id);

    List<ActorResponseDto> getAllActors();

    ActorResponseDto updateActor(Long id, ActorRequestDto dto);

    void deleteActor(Long id);
}
