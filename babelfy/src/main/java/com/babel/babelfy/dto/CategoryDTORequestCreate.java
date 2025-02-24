package com.babel.babelfy.dto;

import com.babel.babelfy.model.Category;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryDTORequestCreate {

    private String name;

    public static Category categoryDTOCreateToCategory(CategoryDTORequestCreate category){
        Category c;
        if(category !=null){
            c =  Category.builder()
                    .name(category.getName())
                    .build();
            return c;
        }else{
            return null;
        }


    }

}
