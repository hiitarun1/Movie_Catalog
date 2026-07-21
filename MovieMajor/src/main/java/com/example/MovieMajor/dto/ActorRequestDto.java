package com.example.MovieMajor.dto;

import com.example.MovieMajor.entity.Gender;
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
public class ActorRequestDto {

    @NotBlank(message = "Actor name cannot be blank")
    private String actName;

    @NotNull(message = "Gender needs to be specified")
    private Gender actGender;

}
