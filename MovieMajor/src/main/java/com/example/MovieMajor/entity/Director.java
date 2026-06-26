package com.example.MovieMajor.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Director")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Director {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dir_id")
    private Long Id;

    @Column(name = "dir_name", nullable = false)
    private String dirName;

    @Column(name = "dir_phone")
    private String dirPhone;

    @OneToMany(mappedBy = "director")
    private Set<Movies> movies = new HashSet<>();
}
