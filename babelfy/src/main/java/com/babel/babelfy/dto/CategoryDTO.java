package com.babel.babelfy.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.babel.babelfy.model.Category;
import lombok.*;

import java.util.List;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class CategoryDTO {
    private String name;
    private long id;

    public static CategoryDTO categoryToCategoryDTO(Category c){
        CategoryDTO cDTO;
        if(c!=null){
            cDTO=CategoryDTO.builder()
                    .id(c.getId())
                    .name(c.getName())
                    .build();
            return cDTO;
        }else{
            return null;
        }

    }
//    private List<SongDTO> songs;
    //Lista id canciones


}
