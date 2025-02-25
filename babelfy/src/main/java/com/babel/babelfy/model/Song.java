package com.babel.babelfy.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private int duration;
    private String artistName;
    private String albumName;
    private LocalDate releaseDate;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = true)
    private Category category;
}

//    @ManyToOne
//    @JoinColumn(name = "category_id")  // Asegúrate de que el nombre de la columna esté configurado correctamente
//    private Category category;
//    @ManyToOne
//    private Category category;
