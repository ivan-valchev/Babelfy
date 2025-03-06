package com.babel.babelfy.dto;

import com.babel.babelfy.model.Artist;
import lombok.Data;

@Data

public class ArtistDTORequestEdit {

    private long id;
    private String name;

    public static Artist artistDTOEditToArtist(ArtistDTORequestEdit artistDTO){
        Artist a;
        if(artistDTO!=null){
            a = Artist.builder()
                    .id(artistDTO.getId())
                    .name(artistDTO.getName())
                    .build();
            return a;
        }
        return null;
    }
}
