package com.example.MovieMajor.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovieResponseDto {

    private Long movId;
    private String movTitle;
    private int movYear;
    private String movLang;

    private Long dirId;
    private String dirName;
}
