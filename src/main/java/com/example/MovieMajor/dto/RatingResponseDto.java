package com.example.MovieMajor.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RatingResponseDto {
    private Long movId;
    private String movTitle;
    private Double revStars;
}
