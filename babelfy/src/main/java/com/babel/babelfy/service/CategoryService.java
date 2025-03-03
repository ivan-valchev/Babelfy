package com.babel.babelfy.service;

import com.babel.babelfy.dto.*;
import com.babel.babelfy.model.Category;
import com.babel.babelfy.model.Song;
import com.babel.babelfy.repository.CategoryRepository;
import com.babel.babelfy.repository.SongRepository;
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
    @Autowired
    private SongRepository songRepo;

    public List<CategoryDTOResponseList> getAll() {
       List<Category> categories = repo.findAll();
       List<CategoryDTOResponseList> categoriesDTO = new ArrayList<>();

       for(Category c : categories){
           categoriesDTO.add(CategoryDTOResponseList.categoryToCategoryDTOResponseList(c));
       }
       return categoriesDTO;
    }

    public CategoryDTOResponseDetail getById(long id) {
        Category category = repo.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
        return categoryToCategoryDTOResponse(category);
//          CategoryDTO(category.getName(),category.getId());
    }

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


//        return new CategoryDTO(category.getId(), category.getName(), categoryDTO.getSongIds());
    }




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

    public List<SongDTOResponseDetail> findSongsCategory(long id){
        List<Song> songs = songRepo.findAll();
        List<SongDTOResponseDetail> songList =  new ArrayList<>();
        for (Song s : songs){
            if(s.getCategory().getId()== id){
                songList.add( SongDTOResponseDetail.songToSongDTOResponseDetail(s));
            }
        }
        return songList;
    }

    public  CategoryDTOResponseDetail categoryToCategoryDTOResponse(Category category){
        CategoryDTOResponseDetail cDTO;
        List<SongDTOResponseToCategoryDetails> list =  new ArrayList<>();
        for(Song s : category.getSongs()){
            list.add(SongDTOResponseToCategoryDetails.SongToSongDTO(s));
        }



        if(category !=null){
            cDTO =  CategoryDTOResponseDetail.builder()
                    .id(category.getId())
                    .name(category.getName())
                    .songs(list)
                    .build();
            return cDTO;
        }else{
            return null;
        }


    }
}
