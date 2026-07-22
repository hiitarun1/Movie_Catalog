package com.example.MovieMajor.service;

import com.example.MovieMajor.dto.ActorRequestDto;
import com.example.MovieMajor.dto.ActorResponseDto;
import com.example.MovieMajor.entity.Actor;
import com.example.MovieMajor.exception.ResourceNotFoundException;
import com.example.MovieMajor.repository.ActorRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Service
@RequiredArgsConstructor
@Slf4j
public class ActorServiceImpl implements ActorService {

private final ActorRepository actorRepository;

@Override
public ActorResponseDto createActor(ActorRequestDto dto){
    log.info("Creating actor with name:{}",dto.getActName());
    Actor actor = mapToEntity(dto);
    Actor saved = actorRepository.save(actor);
    log.info("Actor created with id:{}", saved.getActId());
    return mapToResponseDto(saved);
}

@Override
    public ActorResponseDto getActorById(Long id){
    log.info("Fetching actor with id: {}",id);
    Actor actor = actorRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Actor not found with id: " + id));
    return mapToResponseDto(actor);
}

@Override
public List<ActorResponseDto> getAllActors(){
    log.info("Fetching all actors");
    return actorRepository.findAll().stream().map(this::mapToResponseDto).collect(Collectors.toList());
}

@Override
    public ActorResponseDto updateActor(Long id, ActorRequestDto dto){
    log.info("Updating actor with id: {}",id);
    Actor actor = actorRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Actor not found with id: "+ id));
    actor.setActName(dto.getActName());
    actor.setActGender(dto.getActGender());
    Actor updated = actorRepository.save(actor);
    log.info("Actor updated with id: {}", + updated.getActId());
    return mapToResponseDto(updated);
}

@Override
    public void deleteActor(Long id){
    log.info("Deleting Actor with id: {}",id);
    Actor actor = actorRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Actor not found: "+ id));
    actorRepository.delete(actor);
    log.info("Actor deleted with id: {}", id);
}

public Actor mapToEntity(ActorRequestDto dto){
    Actor actor = new Actor();
    actor.setActName(dto.getActName());
    actor.setActGender(dto.getActGender());
    return actor;
}

private ActorResponseDto mapToResponseDto(Actor actor){
    return new ActorResponseDto(
            actor.getActId(),
            actor.getActName(),
            actor.getActGender()
    );
}
}
