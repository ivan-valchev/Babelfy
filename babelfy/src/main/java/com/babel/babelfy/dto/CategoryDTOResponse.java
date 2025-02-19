package com.babel.babelfy.dto;

import com.babel.babelfy.model.Category;
//import com.babel.babelfy.model.Song;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class CategoryDTOResponse {

    private long id;
    private String name;
    //private List<Song>songs;


    public CategoryDTOResponse categoryToCategoryDTOResponse(Category category){
        CategoryDTOResponse cDTO;
        if(category !=null){
             cDTO =  CategoryDTOResponse.builder()
                     .id(category.getId())
                     .name(category.getName())
                     //.songs(category.getSongs())
                     .build();
            return cDTO;
        }else{
            return null;
        }


    }

}
