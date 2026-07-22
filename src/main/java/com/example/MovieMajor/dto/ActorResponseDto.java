package com.example.MovieMajor.dto;

import com.example.MovieMajor.entity.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ActorResponseDto {

    private Long actId;
    private String actName;
    private Gender actGender;
}
