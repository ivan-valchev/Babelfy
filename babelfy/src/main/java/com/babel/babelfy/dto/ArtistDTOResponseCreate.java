package com.babel.babelfy.dto;

import com.babel.babelfy.model.Artist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArtistDTOResponseCreate {
    private long id;
    private String name;

    public ArtistDTOResponseCreate artistToArtistDTOCreate(Artist artist){

        ArtistDTOResponseCreate artistDTO;

        if(artist!=null){
            artistDTO = ArtistDTOResponseCreate.builder()
                    .id(artist.getId())
                    .name(artist.getName())
                    .build();
            return artistDTO;
        }


        return null;
    }

}
