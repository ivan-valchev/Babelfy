package com.babel.babelfy.service;

import com.babel.babelfy.dto.CategoryDTO;
import com.babel.babelfy.dto.CategoryDTORequest;
import com.babel.babelfy.dto.CategoryDTOResponse;
import com.babel.babelfy.model.Category;
import com.babel.babelfy.repository.CategoryRepository;
import lombok.Data;

import java.util.List;

@Data

public class CategoryService {

    private CategoryRepository repo;


    public Category getCategoryById(long idCategory){
        Category category =  this.repo.findById(idCategory).get();
        return category;
    }

    public CategoryDTOResponse modifyCategory(CategoryDTORequest cDTO,long id){
        Category c = repo.findById(id).get();
        if(c!=null){
            repo.save(c);
        }
        return c;
//        boolean modificado = false;
//        if(c!=null){
//            c.setName(cDTO.getName());
//            modificado= true;
//        }
//        return modificado;
    }
    public Category deleteCategory(CategoryDTO cDTO){
        Category c = repo.findById(cDTO.getId())
                .orElse(null);
        if(c!=null){
            repo.delete(c);
        }
        return c;
    }

    public CategoryDTOResponse categoryToCategoryDTOResponse(Category category){

        if(category !=null){
            
        }

    }

//    public Category buildTo(Category cNew){
//        Category c = new Category(cNew.getName(),cNew.getId());
//        return c;
//    }
}
