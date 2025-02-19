package com.babel.babelfy.controller;

import com.babel.babelfy.dto.CategoryDTO;
import com.babel.babelfy.dto.CategoryDTORequest;
import com.babel.babelfy.dto.CategoryDTOResponse;
import com.babel.babelfy.model.Category;
import com.babel.babelfy.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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


    @GetMapping("")
    public List<Category> getAll(){
        return categoryService.getAll();
    }

    @PostMapping("")
    public void create(){
        Category c  = new Category("Pop");
        categoryService.createCategory(c);
    }
    @PutMapping("/{id}/{name}")
    public String modify(@PathVariable long id, @PathVariable String name){
        categoryService.modify(id,name);
        return "Modificado correctamente";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable long id){
        System.out.println(id);
        categoryService.delete(id);
        return "Borrado correctamente";
    }
}

