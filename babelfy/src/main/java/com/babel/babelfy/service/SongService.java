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
    private SongRepository repository;

    public List<Song> getAll(){
        return repository.findAll();
    }

    public Song getById(SongDTORequestFind sDTO){
        Song s;

        s = repository.findById(sDTO.getId()).orElse(null);

        return s;
    }

    public Song addSong(SongDTORequestCreate songDTO) {

        Song s;
        s = SongDTORequestCreate.songDTOCreateToSong(songDTO);

        if(s!= null) {
            repository.save(s);
            return s;
        }else{
            return null;
        }
    }


    public SongDTOResponse updateSong(SongDTORequest request) {
        Song song = repository.findById(request.getId()).orElse(null);

        if (song == null) {
            throw new EntityNotFoundException("Song not found");
        }

        song.setName(request.getName());
        song.setDuration(request.getDuration());
        song.setArtistName(request.getArtistName());
        song.setAlbumName(request.getAlbumName());
        song.setReleaseDate(request.getReleaseDate());

        repository.save(song);
        return new SongDTOResponse(song);
    }

    public void deleteSong(long id) {
        Song song = repository.findById(id).orElse(null);

        if (song == null) {
            throw new EntityNotFoundException("Song not found");
        }

        repository.delete(song);
    }

}
