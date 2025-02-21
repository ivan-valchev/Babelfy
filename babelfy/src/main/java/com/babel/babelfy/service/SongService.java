package com.babel.babelfy.service;

import com.babel.babelfy.dto.SongDTO;
import com.babel.babelfy.dto.SongDTORequestCreate;
import com.babel.babelfy.model.Category;
import com.babel.babelfy.model.Song;
import com.babel.babelfy.repository.SongRepository;
import com.babel.babelfy.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class SongService {

    @Autowired
    private SongRepository repository;


    public void addSong(SongDTORequestCreate songDTO) {

        Song s;


    }
}
