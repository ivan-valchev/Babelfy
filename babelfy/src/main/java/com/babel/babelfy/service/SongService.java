package com.babel.babelfy.service;

import com.babel.babelfy.dto.*;
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
    @Autowired
    private CategoryRepository cRepository;


    public List<Song> getAll(){
        return Srepository.findAll();
    }

    public Song getById(long id){
        Song s;

        s = Srepository.findById(id).orElse(null);

        return s;
    public List<SongDTOResponseDetail> getAll(){

        List<SongDTOResponseDetail>list = new ArrayList<>();
        for(Song s : Srepository.findAll()){
            list.add(SongDTOResponseDetail.songToSongDTOResponseDetail(s));
        }
        return list;
    }

    public SongDTOResponseDetail getById(long id){
       Song s;
        s = Srepository.findById(id).orElse(null);
        return SongDTOResponseDetail.songToSongDTOResponseDetail(s);
    }

    public Song addSong(SongDTORequestCreate songDTO) {
        if (songDTO == null) {
            return null;
        }

        // Buscar la categoría solo si categoryId no es nulo
        Category category = null;
        if (songDTO.getCategoryId() != null) {
            category = cRepository.findById(songDTO.getCategoryId()).orElse(null);
        }

        // Convertir DTO en Song
        Song s = SongDTORequestCreate.songDTOCreateToSong(songDTO, category);

        if (s != null) {
            Srepository.save(s);
        }

        return s;
    }


    public Song updateSong(SongDTORequest request) {
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
        return Srepository.save(song); // Devolver el objeto Song actualizado
    }



    public void deleteSong(long id) {
        Song song = Srepository.findById(id).orElse(null);

        if (song == null) {
            throw new EntityNotFoundException("Song not found");
        }

        Srepository.delete(song);
    }

}
