package com.example.MovieMajor.service;


import com.example.MovieMajor.dto.DirectorRequestDto;
import com.example.MovieMajor.dto.DirectorResponseDto;

import java.util.List;

public interface DirectorServiceImpl {

    public DirectorResponseDto createDirector(DirectorRequestDto dto);

    public DirectorResponseDto getDirectorById(Long id);

    public List<DirectorResponseDto> getAllDirectors();

    public DirectorResponseDto updateDirector(Long id, DirectorRequestDto dto);

    void deleteDirector(Long id);
}
