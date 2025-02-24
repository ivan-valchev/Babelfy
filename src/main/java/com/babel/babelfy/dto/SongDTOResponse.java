package com.babel.babelfy.dto;

import com.babel.babelfy.model.Category;
import lombok.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SongDTOResponse {
    private long id;
    private String name;
    private int duration;
    private String artistName;
    private String albumName;
    private String releaseDate;
    private Category category;
}