package com.babel.babelfy.model;

<<<<<<< HEAD
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

=======

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


>>>>>>> origin/dev
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Song {
<<<<<<< HEAD
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
=======

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

>>>>>>> origin/dev
    private String name;
    private int duration;
    private String artistName;
    private String albumName;
<<<<<<< HEAD
    private String releaseDate;

    @ManyToOne(optional = true)
    private Category category;
}
=======
    private LocalDate releaseDate;

//    @ManyToOne
//    private Category category;

}
>>>>>>> origin/dev
