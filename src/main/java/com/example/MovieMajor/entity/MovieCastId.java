package com.example.MovieMajor.entity;


import java.io.Serializable;
import java.util.Objects;

public class MovieCastId implements Serializable {
    private Long actor;
    private Long movies;

    public MovieCastId(){}

    public MovieCastId(Long actor, Long movies){
        this.actor=actor;
        this.movies=movies;
    }

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
