package com.example.MovieMajor.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovieCastRequestDto {

    @NotNull(message = "Actor id must not be null")
    private Long actId;

    @NotNull(message = "Movie id must not be blank")
    private Long movId;

    @NotBlank(message = "Role must not be blank")
    private String role;
}
