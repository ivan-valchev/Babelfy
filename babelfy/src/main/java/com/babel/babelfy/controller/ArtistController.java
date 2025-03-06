package com.babel.babelfy.controller;

import com.babel.babelfy.dto.ArtistDTORequestCreate;
import com.babel.babelfy.dto.ArtistDTORequestEdit;
import com.babel.babelfy.dto.ArtistDTOResponseList;
import com.babel.babelfy.service.ArtistService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("")
    public String create(@RequestBody ArtistDTORequestCreate artistDTO){
        return artistService.create(artistDTO);
    }
    @PutMapping("")
    public String edit (@RequestBody ArtistDTORequestEdit artistDTOEdit){
        return artistService.edit(artistDTOEdit);
    }

}
