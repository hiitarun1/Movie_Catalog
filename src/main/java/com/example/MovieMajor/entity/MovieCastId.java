package com.example.MovieMajor.entity;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;


@AllArgsConstructor
@NoArgsConstructor
public class MovieCastId implements Serializable {
    private Long actor;
    private Long movies;

    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(!(obj instanceof MovieCastId)) return false;
        MovieCastId that = (MovieCastId) obj;
        return Objects.equals(actor,that.actor) && Objects.equals(movies,that.movies);
    }

    @Override
    public int hashCode(){
        return Objects.hash(actor,movies);
    }
}
