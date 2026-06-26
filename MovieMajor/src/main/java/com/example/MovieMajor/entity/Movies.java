package com.example.MovieMajor.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="Movies")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Movies {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="mov_id")
    private Long movId;

    @Column(name = "mov_title", nullable = false)
    private String movTitle;

    @Column(name = "mov_year")
    private int movYear;

    @Column(name="mov_lang")
    private String movLang;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dir_id", nullable = false, foreignKey = @ForeignKey(name = "fk_movies_director", foreignKeyDefinition = "FOREIGN KEY (dir_id) REFERENCES director(dir_id) ON DELETE RESTRICT"))
    private Director director;

    @OneToMany(mappedBy = "movies", cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<MovieCast> movieCasts = new HashSet<>();

    @OneToOne(mappedBy = "movies", cascade = CascadeType.ALL, orphanRemoval = true)
    private Rating rating;

}
