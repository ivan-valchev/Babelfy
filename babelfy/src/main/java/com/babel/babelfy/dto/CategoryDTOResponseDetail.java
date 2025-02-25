package com.babel.babelfy.dto;

import com.babel.babelfy.model.Category;
//import com.babel.babelfy.model.Song;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class CategoryDTOResponseDetail {

    private long id;
    private String name;
    //private List<Song>songs;


    public static CategoryDTOResponseDetail categoryToCategoryDTOResponse(Category category){
        CategoryDTOResponseDetail cDTO;
        if(category !=null){
             cDTO =  CategoryDTOResponseDetail.builder()
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
