package com.babel.babelfy.dto;

import com.babel.babelfy.model.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class CategoryDTOResponseList {
    private String name;
    private long id;

    public static CategoryDTOResponseList categoryToCategoryDTOResponseList(Category c){
        CategoryDTOResponseList cDTO;
        if(c!=null){
            cDTO=CategoryDTOResponseList.builder()
                    .id(c.getId())
                    .name(c.getName())
                    .build();
            return cDTO;
        }else{
            return null;
        }

    }
}
