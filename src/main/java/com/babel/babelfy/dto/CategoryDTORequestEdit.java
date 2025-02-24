package com.babel.babelfy.dto;

import com.babel.babelfy.model.Category;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryDTORequestEdit {

    private String name;
    private long id;

    public Category categoryDTOEditToCategory(CategoryDTORequestEdit cDTO){
        Category c;
        if(cDTO!=null){
            c = Category.builder()
                    .name(cDTO.getName())
                    .id(cDTO.getId())
                    .build();
            return c;
        }else{
            return null;
        }



    }

}
