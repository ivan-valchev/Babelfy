package com.babel.babelfy.service;

import com.babel.babelfy.dto.SongDTO;
import com.babel.babelfy.dto.SongDTORequest;
import com.babel.babelfy.dto.SongDTORequestCreate;
import com.babel.babelfy.dto.SongDTORequestFind;
import com.babel.babelfy.dto.SongDTOResponse;
import com.babel.babelfy.model.Category;
import com.babel.babelfy.model.Song;
import com.babel.babelfy.repository.SongRepository;
import com.babel.babelfy.repository.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class SongService {

    @Autowired
    private SongRepository Srepository;
    private CategoryRepository cRepository;

    public List<Song> getAll(){
        return Srepository.findAll();
    }

    public Song getById(long id){
        Song s;

        s = Srepository.findById(id).orElse(null);

        return s;
    }

    public Song addSong(SongDTORequestCreate songDTO) {

        Song s;
        s = SongDTORequestCreate.songDTOCreateToSong(songDTO);

        if(s!= null) {
            Srepository.save(s);
            return s;
        }else{
            return null;
        }
    }

    public SongDTOResponse updateSong(SongDTORequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }

        // Buscar la canción en la base de datos
        Song song = Srepository.findById(request.getId()).orElse(null);
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
            Category category = cRepository.findById(request.getCategoryId()).orElse(null);
            if (category == null) {
                throw new EntityNotFoundException("Category not found");
            }
            song.setCategory(category);
        } else {
            song.setCategory(null); // ✅ Ahora sí elimina la categoría correctamente
        }

        // Guardar cambios en la base de datos
        Srepository.save(song);

        return new SongDTOResponse(song);
    }


    public void deleteSong(long id) {
        Song song = Srepository.findById(id).orElse(null);

        if (song == null) {
            throw new EntityNotFoundException("Song not found");
        }

        Srepository.delete(song);
    }

}
