package com.babel.babelfy.service;

import com.babel.babelfy.dto.SongDTORequest;
import com.babel.babelfy.dto.SongDTOResponse;
import com.babel.babelfy.model.Category;
import com.babel.babelfy.model.Song;
import com.babel.babelfy.repository.CategoryRepository;
import com.babel.babelfy.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SongService {

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public void createSong(SongDTORequest songDTORequest) {
        Category category = null;
        if (songDTORequest.getCategoryId() != null) {
            Optional<Category> optionalCategory = categoryRepository.findById(songDTORequest.getCategoryId());
            if (optionalCategory.isPresent()) {
                category = optionalCategory.get();
            }
        }
        Song song = new Song();
        song.setName(songDTORequest.getName());
        song.setDuration(songDTORequest.getDuration());
        song.setArtistName(songDTORequest.getArtistName());
        song.setAlbumName(songDTORequest.getAlbumName());
        song.setReleaseDate(songDTORequest.getReleaseDate());
        song.setCategory(category);
        songRepository.save(song);
    }

    public SongDTOResponse getById(long id) {
        Optional<Song> optionalSong = songRepository.findById(id);
        if (!optionalSong.isPresent()) {
            throw new RuntimeException("Song not found");
        }
        Song song = optionalSong.get();
        return new SongDTOResponse(song.getId(), song.getName(), song.getDuration(), song.getArtistName(), song.getAlbumName(), song.getReleaseDate(), song.getCategory());
    }

    public List<SongDTOResponse> getAll() {
        List<Song> songs = songRepository.findAll();
        List<SongDTOResponse> songDTOResponses = new ArrayList<>();
        for (Song song : songs) {
            songDTOResponses.add(new SongDTOResponse(song.getId(), song.getName(), song.getDuration(), song.getArtistName(), song.getAlbumName(), song.getReleaseDate(), song.getCategory()));
        }
        return songDTOResponses;
    }

    public void modify(long id, SongDTORequest songDTORequest) {
        Optional<Song> optionalSong = songRepository.findById(id);
        if (!optionalSong.isPresent()) {
            throw new RuntimeException("Song not found");
        }
        Song song = optionalSong.get();
        song.setName(songDTORequest.getName());
        song.setDuration(songDTORequest.getDuration());
        song.setArtistName(songDTORequest.getArtistName());
        song.setAlbumName(songDTORequest.getAlbumName());
        song.setReleaseDate(songDTORequest.getReleaseDate());
        if (songDTORequest.getCategoryId() != null) {
            Optional<Category> optionalCategory = categoryRepository.findById(songDTORequest.getCategoryId());
            if (optionalCategory.isPresent()) {
                song.setCategory(optionalCategory.get());
            }
        }
        songRepository.save(song);
    }

    public void delete(long id) {
        songRepository.deleteById(id);
    }

    public void modifyCategory(long id, long categoryId) {
        Optional<Song> optionalSong = songRepository.findById(id);
        if (!optionalSong.isPresent()) {
            throw new RuntimeException("Song not found");
        }
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
        if (!optionalCategory.isPresent()) {
            throw new RuntimeException("Category not found");
        }
        Song song = optionalSong.get();
        song.setCategory(optionalCategory.get());
        songRepository.save(song);
    }

    public void removeCategory(long id) {
        Song song = songRepository.findById(id).orElseThrow(() -> new RuntimeException("Song not found"));
        song.setCategory(null);
        songRepository.save(song);
    }

}
