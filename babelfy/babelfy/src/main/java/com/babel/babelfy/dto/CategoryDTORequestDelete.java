package com.babel.babelfy.dto;

import com.babel.babelfy.model.Category;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryDTORequestDelete {

    private long id;

    public Category categoryDTODeleteToCategory(CategoryDTORequestDelete category){

        Category c;

        if(category!=null){
            c = Category.builder()
                    .id(category.getId())
                    .build();
            return c;
        }else{
            return null;
        }

    }


}
