package com.example.MovieMajor.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RatingRequestDto {

    @NotNull(message = "Movie Id cannot be blank")
    private Long movId;

    @NotNull(message = "Rating must not be null")
    @DecimalMin(value = "0.0", message = "Rating must be atleast 0.0")
    @DecimalMax(value = "5.0", message = "Rating cannot exceed 5.0")
    private Double revStars;
}
