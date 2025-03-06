package com.babel.babelfy.dto;

import com.babel.babelfy.model.Artist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArtistDTORequestDelete {
    private long id;

    public static Artist ArtistDTODeleteToArtist(ArtistDTORequestDelete aDTO){
        Artist a;
        if(aDTO!=null){
            a = Artist.builder()
                    .id(aDTO.getId())
                    .build();
            return a;
        }
        return null;
    }
}
