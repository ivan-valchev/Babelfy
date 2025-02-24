package com.babel.babelfy.service;

import com.babel.babelfy.dto.CategoryDTO;
import com.babel.babelfy.dto.CategoryDTORequestCreate;
import com.babel.babelfy.dto.CategoryDTORequestDelete;
import com.babel.babelfy.dto.CategoryDTORequestEdit;
import com.babel.babelfy.model.Category;
import com.babel.babelfy.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import java.util.List;

import java.util.ArrayList;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repo;
    @Transactional
    public List<CategoryDTO> getAll() {
        List<Category> categories = repo.findAll();
        List<CategoryDTO> categoryDTOs = new ArrayList<>();
        for (int i = 0; i < categories.size(); i++) {
            Category category = categories.get(i);
//            List<Long> songIds = new ArrayList<>();
//            for (int j = 0; j < category.getSongs().size(); j++) {
//                songIds.add(category.getSongs().get(j).getId());
//            }
            categoryDTOs.add(new CategoryDTO(category.getName(),category.getId()));
        }
        return categoryDTOs;
    }

    public CategoryDTO getById(long id) {
        Category category = repo.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
        return new CategoryDTO(category.getName(),category.getId());
    }

<<<<<<< HEAD
    @Transactional
    public void createCategory(String name) {
        Category category = new Category();
        category.setName(name);
        category = repo.save(category);
=======
    public String createCategory(CategoryDTORequestCreate categoryCreate) {
        Category category;

        List<Category> categoryNames = repo.findByName(categoryCreate.getName());

        if(categoryNames.isEmpty()){
            category = CategoryDTORequestCreate.categoryDTOCreateToCategory(categoryCreate);
            repo.save(category);
            return"";
        }else{
            return "Found";
        }


>>>>>>> origin/dev
//        return new CategoryDTO(category.getId(), category.getName(), categoryDTO.getSongIds());
    }






//    public void modifyCategory(String name,long id){
//        Category c;
//        c = repo.findById(id).orElse(null);
//        if (c != null) {
//            c.setName(name);
//            repo.save(c);
//        }
//    }

    public String modify(CategoryDTORequestEdit cDTO){
        Category cOld;
        List<Category> category = repo.findByName(cDTO.getName());
        String text ="Found";
        cOld = repo.findById(cDTO.getId()).orElse(null);
        if(cOld!=null){
            if(category.isEmpty()){
                cOld.setName(cDTO.getName());
                repo.save(cOld);
                text = "";
            }

        }

        return text;
    }

    public void delete(CategoryDTORequestDelete cDTO){
        Category c;
        c = repo.findById(cDTO.getId()).orElse(null);
        if(c!=null){
            repo.delete(c);
        }

    }
}
