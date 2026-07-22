package com.example.MovieMajor.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieRequestDto {

    @NotBlank(message = "Movie title must not be blank")
    private String movTitle;

    @Min(value = 1888, message = "Movie year must be valid(earliest film was 1888)")
    private int movYear;

    @NotBlank(message = "Movie language must not be blank")
    private String movLang;

    @NotNull(message = "Director Id must not be null")
    private Long dirId;
}
