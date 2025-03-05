package com.babel.babelfy.service;

import com.babel.babelfy.dto.ArtistDTOResponseList;
import com.babel.babelfy.model.Artist;
import com.babel.babelfy.repository.ArtistRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ArtistService {
    @Autowired
    ArtistRepository artistRepo;

    public List<ArtistDTOResponseList> getAll(){
        List<Artist> artistList;
        artistList = artistRepo.findAll();
        List<ArtistDTOResponseList> list = new ArrayList<>();

        for (Artist a: artistList){
            list.add(ArtistDTOResponseList.ArtistToArtistDTOList(a));
        }

        return list;
    }

}
