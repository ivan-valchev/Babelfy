package com.babel.babelfy.controller;

import com.babel.babelfy.dto.CategoryDTO;
import com.babel.babelfy.model.Category;
import com.babel.babelfy.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {
       private final CategoryService categoryService;


    public CategoryDTO getCategory(@PathVariable long id){
        Category cat = categoryService.getCategoryById(id);
        return categoryService.buildTO(cat);
    }

    @PutMapping()
    public String modify(@RequestBody CategoryDTO cDTO){
        String text;
        if(categoryService.modifyCategory(cDTO)!=null){
            text = "Cambio realizado con éxito";
        }else{
            text = "El cambio no se pudo realizar";
        }

        return text;
    }
    @DeleteMapping("/{id}")
    public String delete(@RequestBody CategoryDTO cDTO){
        String text;
        if(categoryService.deleteCategory(cDTO)!=null){
            text = "Borrado realizado con éxito";
        }else{
            text = "No se pudo realizar el borrado";
        }

        return text;
    }
}
