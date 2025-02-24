package com.babel.babelfy.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
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
    private String releaseDate;

    @ManyToOne(optional = true)
    private Category category;
}