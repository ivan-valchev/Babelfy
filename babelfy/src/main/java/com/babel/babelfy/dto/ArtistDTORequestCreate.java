package com.babel.babelfy.dto;

import com.babel.babelfy.model.Artist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class ArtistDTORequestCreate {

    private String name;

    public static Artist artistDTOCreateToArtist(ArtistDTORequestCreate artistDTO){
        Artist a;

        if(artistDTO!=null){
            a = Artist.builder()
                    .name(artistDTO.getName())
                    .build();

            return a;
        }

        return null;


    }
}
