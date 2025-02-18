package com.babel.babelfy.controller;

import com.babel.babelfy.dto.CategoryDTO;
import com.babel.babelfy.dto.CategoryDTORequest;
import com.babel.babelfy.dto.CategoryDTOResponse;
import com.babel.babelfy.model.Category;
import com.babel.babelfy.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {
       private final CategoryService categoryService;

//
//    public CategoryDTO getCategory(@PathVariable long id){
////        Category cat = categoryService.getCategoryById(id);
////        return categoryService.buildTO(cat);
//        return null;
//    }
//
//    @PutMapping("")
//    public String modify(@RequestBody CategoryDTOResponse cDTO,long id){
//        String text;
//        if(categoryService.modifyCategory(cDTO, id)!=null){
//            text = "Cambio realizado con éxito";
//        }else{
//            text = "El cambio no se pudo realizar";
//        }
//
//        return text;
//    }
//    @DeleteMapping("")
//    public String delete(@PathVariable long id){
//        String text;
//        if(categoryService.deleteCategory(id)!=null){
//            text = "Borrado realizado con éxito";
//        }else{
//            text = "No se pudo realizar el borrado";
//        }
//
//        return text;
//    }
    @PostMapping("")
    public void create(){
        Category c  = new Category("Pop");
        categoryService.createCategory(c);
    }
    @PutMapping("")
    public String modify(@RequestBody CategoryDTORequest cDTOR){
        categoryService.modify(cDTOR);
        return "Modificado correctamente";
    }
    @DeleteMapping("")
    public String delete(@RequestBody CategoryDTORequest cDTOR){
        categoryService.delete(cDTOR);
        return "Realizado correctamente";
    }
}

