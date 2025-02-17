package com.babel.babelfy.controller;

import com.babel.babelfy.dto.CategoryDTO;
import com.babel.babelfy.model.Category;
import com.babel.babelfy.service.CategoryService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class CategoryController {
        CategoryService cs;

    @PutMapping()
    public String modify(@RequestBody CategoryDTO cDTO){
        String text;
        if(cs.modifyCategory(cDTO)!=null){
            text = "Cambio realizado con éxito";
        }else{
            text = "El cambio no se pudo realizar";
        }

        return text;
    }
    @DeleteMapping()
    public String delete(@RequestBody CategoryDTO cDTO){
        String text;
        if(cs.deleteCategory(cDTO)!=null){
            text = "Borrado realizado con éxito";
        }else{
            text = "No se pudo realizar el borrado";
        }

        return text;
    }
}
