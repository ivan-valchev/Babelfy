package com.babel.babelfy.service;

import com.babel.babelfy.dto.CategoryDTO;
import com.babel.babelfy.model.Category;
import lombok.Data;

import java.util.List;

@Data

public class CategoryService {

    List<Category> categories;
    public boolean modifyCategory(CategoryDTO cDTO, int id){
        Category c = findByID(id);
        boolean modificado = false;
        if(c!=null){
            c.setName(cDTO.getName());
            modificado= true;
        }else{
            modificado = false;

        }
        return modificado;
    }
    public boolean deleteCategory(CategoryDTO cDTO,int id){
        Category c = findByID(id);
        boolean eliminado = false;
        if(c!=null) {
            categories.remove(c);
            eliminado = true;
        }
        return eliminado;
    }

    public Category findByID(int id){

        for (Category c:categories){
            if(c.getId() == id){
                return c;
            }
        }
        return null;
    }
}
