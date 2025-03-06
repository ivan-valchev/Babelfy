package com.babel.babelfy.dto;

import com.babel.babelfy.model.Artist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ArtistDTOResponseDetails {
    private long id;
    private String name;

    public static ArtistDTOResponseDetails artistDTODetailsToArtist(Artist a){
        ArtistDTOResponseDetails aDTO;
        if(a!=null){
            aDTO = ArtistDTOResponseDetails.builder()
                    .id(a.getId())
                    .name(a.getName())
                    .build();
            return aDTO;
        }
        return null;
    }

}
