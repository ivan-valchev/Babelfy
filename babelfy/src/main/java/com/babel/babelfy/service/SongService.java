package com.babel.babelfy.service;

import com.babel.babelfy.dto.SongDTORequest;
import com.babel.babelfy.dto.SongDTORequestCreate;
import com.babel.babelfy.dto.SongDTOResponse;
import com.babel.babelfy.model.Category;
import com.babel.babelfy.model.Song;
import com.babel.babelfy.repository.SongRepository;
import com.babel.babelfy.repository.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class SongService {

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Song> getAll() {
        return songRepository.findAll();
    }

    public Song addSong(SongDTORequestCreate songDTO) {
        if (songDTO == null) {
            return null;
        }

        Category category = null;
        if (songDTO.getCategoryId() != null) {
            category = categoryRepository.findById(songDTO.getCategoryId()).orElse(null);
        }

        Song song = SongDTORequestCreate.songDTOCreateToSong(songDTO, category);

        if (song != null) {
            songRepository.save(song);
        }

        return song;
    }

    public SongDTOResponse updateSong(SongDTORequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }

        // Buscar la canción en la base de datos
        Song song = songRepository.findById(request.getId()).orElse(null);
        if (song == null) {
            throw new EntityNotFoundException("Song not found");
        }

        // Actualizar los datos de la canción
        song.setName(request.getName());
        song.setDuration(request.getDuration());
        song.setArtistName(request.getArtistName());
        song.setAlbumName(request.getAlbumName());
        song.setReleaseDate(request.getReleaseDate());

        // Manejar correctamente la categoría:
        if (request.getCategoryId() != null) {
            Category category = categoryRepository.findById(request.getCategoryId()).orElse(null);
            if (category == null) {
                throw new EntityNotFoundException("Category not found");
            }
            song.setCategory(category);
        } else {
            song.setCategory(null); // Ahora sí elimina la categoría correctamente
        }

        // Guardar cambios en la base de datos
        songRepository.save(song);

        return new SongDTOResponse(song);
    }

    public void deleteSong(long id) {
        Song song = songRepository.findById(id).orElse(null);

        if (song == null) {
            throw new EntityNotFoundException("Song not found");
        }

        songRepository.delete(song);
    }

}
