package com.babel.babelfy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTOResponse {

    private int id;
    private String name;
    private List<SongDTO>songs;


}
