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
public class MovieCastResponseDto {

    private Long actId;
    private String actName;
    private Long movId;
    private String movTitle;
    private String role;
}
