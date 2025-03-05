package com.babel.babelfy.controller;

import com.babel.babelfy.dto.ArtistDTOResponseList;
import com.babel.babelfy.service.ArtistService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/artists")
public class ArtistController {

    @Autowired
    ArtistService artistService;

    @GetMapping("")
    public List<ArtistDTOResponseList> getAll(){
        return artistService.getAll();
    }

}
