package com.babel.babelfy.controller;

import com.babel.babelfy.dto.SongDTO;
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

    // Crear canción
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SongDTO addSong(@RequestBody SongDTO songDTO) {
        return songService.addSong(songDTO);
    }

    // Obtener todas las canciones
    @GetMapping
    public List<SongDTO> getAllSongs() {
        return songService.getAllSongs();
    }

    // Obtener canción por ID
    @GetMapping("/{id}")
    public SongDTO getSongById(@PathVariable long id) {
        return songService.getSongById(id);
    }

    // Actualizar canción (Cambiar categoría)
    @PutMapping("/{songId}")
    @ResponseStatus(HttpStatus.OK)
    public SongDTO updateSongCategory(@PathVariable long songId, @RequestBody SongDTO songDTO) {
        return songService.updateSongCategory(songId, songDTO);
    }

    // Eliminar canción
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSong(@PathVariable long id) {
        songService.deleteSong(id);
    }
}
