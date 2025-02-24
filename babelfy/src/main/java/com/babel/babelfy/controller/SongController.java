package com.babel.babelfy.controller;

<<<<<<< HEAD
import com.babel.babelfy.dto.SongDTORequest;
import com.babel.babelfy.dto.SongDTOResponse;
import com.babel.babelfy.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
=======
import comx.babel.babelfy.dto.SongDTO;
import com.babel.babelfy.dto.SongDTORequestCreate;
import com.babel.babelfy.model.Category;
import com.babel.babelfy.model.Song;
import com.babel.babelfy.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
>>>>>>> origin/dev
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/songs")
public class SongController {

    @Autowired
    private SongService songService;
<<<<<<< HEAD

    @PostMapping("")
    public String create(@RequestBody SongDTORequest songDTORequest) {
        songService.createSong(songDTORequest);
        return "Canción añadida correctamente";
    }

    @GetMapping("/{id}")
    public SongDTOResponse getById(@PathVariable long id) {
        return songService.getById(id);
    }

    @GetMapping("")
    public List<SongDTOResponse> getAll() {
        return songService.getAll();
    }

    @PutMapping("/{id}")
    public String modify(@PathVariable long id, @RequestBody SongDTORequest songDTORequest) {
        songService.modify(id, songDTORequest);
        return "Canción modificada correctamente";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable long id) {
        songService.delete(id);
        return "Canción eliminada correctamente";
    }

    @PutMapping("/{id}/category/{categoryId}")
    public String modifyCategory(@PathVariable long id, @PathVariable long categoryId) {
        songService.modifyCategory(id, categoryId);
        return "Categoría de la canción modificada correctamente";
    }

    @PutMapping("/{id}/remove-category")
    public String removeCategory(@PathVariable long id) {
        songService.removeCategory(id);
        return "Categoría eliminada de la canción correctamente";
    }
}
=======
    @GetMapping("")
    public List<Song> getAll(){
        return songService.getAll();
    }
  @PostMapping("")
  public Song create (@RequestBody SongDTORequestCreate songDTO){
    return songService.addSong(songDTO);
  }
}
>>>>>>> origin/dev
