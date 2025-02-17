package com.babel.babelfy.dto;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {
    private String name;
    private long id;
    private List<SongDTO> songs;
    //Lista id canciones
}
