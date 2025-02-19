package com.babel.babelfy.service;

import com.babel.babelfy.dto.CategoryDTO;
import com.babel.babelfy.model.Category;
import com.babel.babelfy.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<CategoryDTO> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDTO> categoryDTOs = new ArrayList<>();
        for (int i = 0; i < categories.size(); i++) {
            Category category = categories.get(i);
            List<Long> songIds = new ArrayList<>();
            for (int j = 0; j < category.getSongs().size(); j++) {
                songIds.add(category.getSongs().get(j).getId());
            }
            categoryDTOs.add(new CategoryDTO(category.getId(), category.getName(), songIds));
        }
        return categoryDTOs;
    }

    public CategoryDTO getCategoryById(long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
        List<Long> songIds = new ArrayList<>();
        for (int i = 0; i < category.getSongs().size(); i++) {
            songIds.add(category.getSongs().get(i).getId());
        }
        return new CategoryDTO(category.getId(), category.getName(), songIds);
    }

    public CategoryDTO addCategory(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setName(categoryDTO.getName());
        category = categoryRepository.save(category);
        return new CategoryDTO(category.getId(), category.getName(), categoryDTO.getSongIds());
    }

    public void deleteCategory(long id) {
        categoryRepository.deleteById(id);
    }

    public CategoryDTO updateCategory(long id, CategoryDTO categoryDTO) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
        category.setName(categoryDTO.getName());
        category = categoryRepository.save(category);
        return new CategoryDTO(category.getId(), category.getName(), categoryDTO.getSongIds());
    }
}
