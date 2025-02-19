package com.babel.babelfy.controller;

import com.babel.babelfy.dto.CategoryDTO;
import com.babel.babelfy.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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


    @GetMapping("")
    public List<Category> getAll(){
        return categoryService.getAll();
    }
    @GetMapping("/{id}")
    public String findId(@PathVariable long id){
        categoryService.getById(id);
        return "Encontrado correctamente";
    }

    @PostMapping("/{name}")
    public String create(@PathVariable String name){
        categoryService.createCategory(name);
        return "Añadido corredtamente";
    }
    @PutMapping("/{id}/{name}")
    public String modify(@PathVariable long id, @PathVariable String name){
        categoryService.modify(id,name);
        return "Modificado correctamente";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable long id){
        categoryService.delete(id);
        return "Borrado correctamente";
    }
}

