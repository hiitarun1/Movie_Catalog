package com.example.MovieMajor.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DirectorRequestDto {

    @NotBlank(message = "Director name must not be blank")
    private String dirName;

    @Pattern(regexp = "[0-9]{10}$", message = "Phone number must be exactly 10 digits")
    private String dirPhone;
}
