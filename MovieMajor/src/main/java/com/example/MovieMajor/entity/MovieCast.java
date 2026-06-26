package com.example.MovieMajor.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.web.bind.annotation.ModelAttribute;

@Entity
@Table(name = "movie_cast")
@IdClass(MovieCastId.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovieCast {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "act_id")
    private Actor actor;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mov_id")
    private Movies movies;

    @Column(name = "role")
    private String role;
}
