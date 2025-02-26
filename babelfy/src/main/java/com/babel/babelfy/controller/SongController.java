package com.babel.babelfy.controller;

import com.babel.babelfy.dto.*;
import com.babel.babelfy.model.Category;
import com.babel.babelfy.model.Song;
import com.babel.babelfy.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/songs")
public class SongController {

@Autowired
private SongService songService;

@GetMapping("")
    public List<SongDTOResponseDetail> getAll(){
        return songService.getAll();
    }
@GetMapping("/{id}")
public SongDTOResponseDetail getById(@PathVariable long id){
    return songService.getById(id);
}
  @PostMapping("")
  public Song create (@RequestBody SongDTORequestCreate songDTO) {
      return songService.addSong(songDTO);

  }
    @PutMapping("")
    public Song updateSong(@RequestBody SongDTORequest request) {
        return songService.updateSong(request);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteSong(@RequestBody SongDTORequest request) {
        songService.deleteSong(request.getId()); // Solo se usa el ID
        return ResponseEntity.noContent().build();
    }


}

