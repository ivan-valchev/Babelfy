package com.babel.babelfy.controller;

import com.babel.babelfy.dto.SongDTO;
import com.babel.babelfy.dto.SongDTORequestCreate;
import com.babel.babelfy.dto.SongDTORequestFind;
import com.babel.babelfy.model.Category;
import com.babel.babelfy.model.Song;
import com.babel.babelfy.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/songs")
public class SongController {

@Autowired
private SongService songService;

@GetMapping("")
    public List<Song> getAll(){
        return songService.getAll();
    }
@GetMapping("/{id}")
public Song getById(@RequestBody SongDTORequestFind sDTO){
    return songService.getById(sDTO);
}
  @PostMapping("")
  public Song create (@RequestBody SongDTORequestCreate songDTO){
    return songService.addSong(songDTO);
  }
}

