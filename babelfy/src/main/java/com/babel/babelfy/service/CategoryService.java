package com.babel.babelfy.service;

import com.babel.babelfy.dto.CategoryDTO;
import com.babel.babelfy.dto.CategoryDTORequest;
import com.babel.babelfy.dto.CategoryDTOResponse;
import com.babel.babelfy.model.Category;
import com.babel.babelfy.repository.CategoryRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
public class CategoryService {
    @Autowired
    private CategoryRepository repo;


//    public Category getCategoryById(long idCategory){
//        Category category =  this.repo.findById(idCategory).get();
//        return category;
//    }
//
//    public CategoryDTOResponse modifyCategory(CategoryDTOResponse cDTO,long id){
//        Category c = repo.findById(id).get();
//        CategoryDTOResponse cDTOR = new CategoryDTOResponse();
//        cDTOR.categoryToResponse(c);
//        cDTOR.setName(cDTO.getName());
//        return cDTOR;
////        repo.save();
////        boolean modificado = false;
////        if(c!=null){
////            c.setName(cDTO.getName());
////            modificado= true;
////        }
////        return modificado;
//    }
//    public CategoryDTOResponse deleteCategory(long id){
//        Category c = repo.findById(id)
//                .orElse(null);
//        CategoryDTOResponse cDTOR = new CategoryDTOResponse();
//        cDTOR.categoryToResponse(c);
//        return cDTOR;
//    }



//    public Category buildTo(Category cNew){
//        Category c = new Category(cNew.getName(),cNew.getId());
//        return c;
//    }

    //Debe recibir categortia DTO
    public void createCategory(Category category){
        repo.save(category);
    }

    public List<Category> getAll(){
        return repo.findAll();
    }

    public void modifyCategory(String name,long id){
        Category c;
        c = repo.findById(id).orElse(null);
        if (c != null) {
            c.setName(name);
            repo.save(c);
        }
    }

    public void modify(CategoryDTORequest cDTOR){
        Category cOld;
        cOld = repo.findById(cDTOR.getId()).orElse(null);
        if(cOld!=null){
            cOld.setName(cDTOR.getName());
        }
        repo.save(cOld);

    }

    public void delete(CategoryDTORequest cDTOR){
        Category c;

        c = repo.findById(cDTOR.getId()).orElse(null);
        if(c!=null){
            repo.delete(c);
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
