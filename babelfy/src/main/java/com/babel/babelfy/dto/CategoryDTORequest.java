package com.babel.babelfy.dto;

import com.babel.babelfy.model.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor


public class CategoryDTORequest {
        private String name;
        private long id;

        public Category categoryDTORequestToCategory(CategoryDTORequest cDTO){

                Category c;
                if(cDTO !=null){
                        c =  Category.builder()
                                .id(cDTO.getId())
                                .name(cDTO.getName())
                                .build();
                        return c;
                }else{
                        return null;
                }

        }
}
