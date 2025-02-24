package com.babel.babelfy.dto;

import lombok.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SongDTORequest {
    private String name;
    private int duration;
    private String artistName;
    private String albumName;
    private String releaseDate;
    private Long categoryId; // Puede ser null si la canción no tiene categoría
}