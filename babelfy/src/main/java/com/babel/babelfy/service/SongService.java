//package com.babel.babelfy.service;
//
//import com.babel.babelfy.dto.SongDTO;
//import com.babel.babelfy.model.Song;
//import com.babel.babelfy.repository.SongRepository;
//import com.babel.babelfy.repository.CategoryRepository;
//import jakarta.transaction.Transactional;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//@Transactional
//public class SongService {
//
//<<<<<<< HEAD
//    @Autowired
//    private SongRepository songRepository;
//
//    @Autowired
//    private CategoryRepository categoryRepository;
//
//    // Método para agregar una nueva canción
//    public SongDTO addSong(SongDTO songDTO) {
//        Song song = new Song();
//        song.setName(songDTO.getName());
//        song.setDuration(songDTO.getDuration());
//        song.setArtistName(songDTO.getArtistName());
//        song.setAlbumName(songDTO.getAlbumName());
//        song.setReleaseDate(songDTO.getReleaseDate());
//
//        // Si el Category ID está presente en el DTO, asignamos la categoría
//        if (songDTO.getCategoryId() != null) {
//            categoryRepository.findById(songDTO.getCategoryId())
//                    .ifPresent(song::setCategory);
//        }
//
//        // Guardar la canción
//        song = songRepository.save(song);
//
//        // Devolver el DTO de la canción guardada
//        return new SongDTO(song.getId(), song.getName(), song.getDuration(),
//                song.getArtistName(), song.getAlbumName(), song.getReleaseDate(),
//                song.getCategory() != null ? song.getCategory().getId() : null);
//    }
//
//    // Obtener todas las canciones
//    public List<SongDTO> getAllSongs() {
//        List<Song> songs = songRepository.findAll();
//        List<SongDTO> songDTOs = new ArrayList<>();
//        for (Song song : songs) {
//            songDTOs.add(new SongDTO(
//                    song.getId(),
//                    song.getName(),
//                    song.getDuration(),
//                    song.getArtistName(),
//                    song.getAlbumName(),
//                    song.getReleaseDate(),
//                    song.getCategory() != null ? song.getCategory().getId() : null
//            ));
//        }
//        return songDTOs;
//    }
//
//    // Obtener canción por ID
//    public SongDTO getSongById(long id) {
//        Song song = songRepository.findById(id).orElseThrow(() -> new RuntimeException("Song not found"));
//        return new SongDTO(
//                song.getId(),
//                song.getName(),
//                song.getDuration(),
//                song.getArtistName(),
//                song.getAlbumName(),
//                song.getReleaseDate(),
//                song.getCategory() != null ? song.getCategory().getId() : null
//        );
//    }
//
//    // Actualizar canción (Cambiar categoría)
//    public SongDTO updateSongCategory(long songId, SongDTO songDTO) {
//        Song song = songRepository.findById(songId).orElseThrow(() -> new RuntimeException("Song not found"));
//        if (songDTO.getCategoryId() != null) {
//            categoryRepository.findById(songDTO.getCategoryId()).ifPresent(song::setCategory);
//        }
//        song = songRepository.save(song);
//        return new SongDTO(
//                song.getId(),
//                song.getName(),
//                song.getDuration(),
//                song.getArtistName(),
//                song.getAlbumName(),
//                song.getReleaseDate(),
//                song.getCategory() != null ? song.getCategory().getId() : null
//        );
//    }
//
//    // Eliminar canción
//    public void deleteSong(long id) {
//        songRepository.deleteById(id);
//    }
//=======
//>>>>>>> origin/dev
//}
