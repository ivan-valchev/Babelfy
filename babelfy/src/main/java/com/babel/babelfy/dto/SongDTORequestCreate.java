package com.babel.babelfy.dto;

import com.babel.babelfy.model.Category;
import com.babel.babelfy.model.Song;
import com.babel.babelfy.repository.CategoryRepository;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

@Data
@Builder
public class SongDTORequestCreate {

    private String name;
    private int duration;
    private String artistName;
    private String albumName;
    private LocalDate releaseDate;
    private Long categoryId;
}

    // Convierte DTO a Song sin depender de la base de datos

