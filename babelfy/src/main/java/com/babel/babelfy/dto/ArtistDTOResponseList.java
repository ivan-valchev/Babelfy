package com.babel.babelfy.dto;

import com.babel.babelfy.model.Artist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArtistDTOResponseList {

    private long id;
    private String name;


    public static ArtistDTOResponseList ArtistToArtistDTOList(Artist a){
        ArtistDTOResponseList artistDTO;
        if(a!=null){
            artistDTO = ArtistDTOResponseList.builder()
                    .id(a.getId())
                    .name(a.getName())
                    .build();
            return artistDTO;
        }

        return null;

    }

}
