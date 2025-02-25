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
    private SongRepository repository;

    public List<SongDTOResponseList> getAll(){

        List<SongDTOResponseList>list = new ArrayList<>();
        for(Song s : repository.findAll()){
            list.add(SongDTOResponseList.songToSongResponseList(s));
        }
        return list;
    }

    public SongDTOResponseDetail getById(long id){
       Song s;
        s = repository.findById(id).orElse(null);
        return SongDTOResponseDetail.songToSongDTOResponseDetail(s);
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

    public Song updateSong(SongDTORequest request) {
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
        return song;
    }

    public void deleteSong(long id) {
        Song song = repository.findById(id).orElse(null);

        if (song == null) {
            throw new EntityNotFoundException("Song not found");
        }

        repository.delete(song);
    }

}
