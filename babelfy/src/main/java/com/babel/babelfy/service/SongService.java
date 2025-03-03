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
    private CategoryRepository categoryRepo;

    public List<SongDTOResponseDetail> getAll(){
        List<SongDTOResponseDetail> list = new ArrayList<>();
        for(Song s : Srepository.findAll()){
            list.add(SongDTOResponseDetail.songToSongDTOResponseDetail(s));
        }
        return list;
    }

    public SongDTOResponseDetail getById(long id){
        Song s = Srepository.findById(id).orElse(null);
        return SongDTOResponseDetail.songToSongDTOResponseDetail(s);
    }

    public String addSong(SongDTORequestCreate songDTO) {
        if (songDTO == null) {
            return null;
        }

        // Buscar la categoría solo si categoryId no es nulo
        Category category = null;
        if (songDTO.getCategoryId() != null) {
            category = categoryRepo.findById(songDTO.getCategoryId()).orElse(null);
        }

        // Convertir DTO en Song
        Song s;
        s = songDTOCreateToSong(songDTO);

        Srepository.save(s);
        s.getCategory().getSongs().add(s);
        categoryRepo.save(s.getCategory());

        return "Funcionoooo";
    }

    public String updateSong(SongDTORequest request) {
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
        song.setCategory(categoryRepo.findById(request.getCategoryId()).orElse(null));

        // Manejar correctamente la categoría:
        if (request.getCategoryId() != null) {
            Category category = categoryRepo.findById(request.getCategoryId()).orElse(null);
            if (category == null) {
                throw new EntityNotFoundException("Category not found");
            }
            song.setCategory(category);
        } else {
            song.setCategory(null); // ✅ Ahora sí elimina la categoría correctamente
        }
        Srepository.save(song);
        // Guardar cambios en la base de datos
        return "Editoo" ; // Devolver el objeto Song actualizado
    }

    public void deleteSong(long id) {
        Song song = Srepository.findById(id).orElse(null);
        if (song == null) {
            throw new EntityNotFoundException("Song not found");
        }
        Srepository.delete(song);
    }
    public Song songDTOCreateToSong(SongDTORequestCreate song){
        Song s;
        if(song!=null){
            s = Song.builder()
                    .name(song.getName())
                    .duration(song.getDuration())
                    .artistName(song.getArtistName())
                    .albumName(song.getAlbumName())
                    .releaseDate(song.getReleaseDate())
                    .category(categoryRepo.findById(song.getCategoryId()).orElse(null))
                    .build();
            return s;
        }else{
            return null;
        }
    }

}

