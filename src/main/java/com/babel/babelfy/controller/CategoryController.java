package com.babel.babelfy.controller;

import com.babel.babelfy.dto.*;
import com.babel.babelfy.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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


    @GetMapping("")
    public List<CategoryDTO> getAll(){
        return categoryService.getAll();
    }
    @GetMapping("/{id}")
    public CategoryDTO findId(@PathVariable long id){
        return categoryService.getById(id);
    }

    @PostMapping("")
    public String create(@RequestBody CategoryDTORequestCreate cDTOR){

        return categoryService.createCategory(cDTOR);
    }
    @PutMapping("")
    public String modify(@RequestBody CategoryDTORequestEdit cDTO){
        return  categoryService.modify(cDTO);
    }
    @DeleteMapping("")
    public String delete(@RequestBody CategoryDTORequestDelete cDTO){
        categoryService.delete(cDTO);
        return "Borrado correctamente";
    }
}

