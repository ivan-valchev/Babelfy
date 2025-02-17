package com.babel.babelfy.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.*;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    private String name;
    private List<Song>songs;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
}
