package com.babel.babelfy.service;

import com.babel.babelfy.dto.CategoryDTO;
import com.babel.babelfy.model.Category;
import com.babel.babelfy.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import java.util.List;

import java.util.ArrayList;

@Service
@Transactional
public class CategoryService {

    @Autowired
    private CategoryRepository repo;

    public List<Category> getAll() {
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
        return categories;
    }

    public CategoryDTO getById(long id) {
        Category category = repo.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
//        List<Long> songIds = new ArrayList<>();
//        for (int i = 0; i < category.getSongs().size(); i++) {
//            songIds.add(category.getSongs().get(i).getId());
//        }
        return new CategoryDTO(category.getName(),category.getId());
    }

    public void createCategory(String name) {
        Category category = new Category();
        category.setName(name);
        category = repo.save(category);
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

    public void modify(long id, String name){
        Category cOld;
        cOld = repo.findById(id).orElse(null);
        if(cOld!=null){
            cOld.setName(name);
            repo.save(cOld);
        }


    }

    public void delete(long id){
        Category c;
        c = repo.findById(id).orElse(null);
        if(c!=null){
            repo.delete(c);
        }

    }
}
