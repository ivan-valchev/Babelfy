package com.babel.babelfy.dto;

import com.babel.babelfy.model.Song;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class SongDTOResponseToCategoryDetails {

    private String name;
    private int duration;
    private String artistName;
    private String albumName;
    private LocalDate releaseDate;


    public static SongDTOResponseToCategoryDetails SongToSongDTO(Song s){
        SongDTOResponseToCategoryDetails songDTO;

        if (s != null) {
            songDTO = SongDTOResponseToCategoryDetails.builder()
                    .name(s.getName())
                    .duration(s.getDuration())
                    .artistName(s.getArtistName())
                    .albumName(s.getAlbumName())
                    .releaseDate(s.getReleaseDate())
                    .build();
            return songDTO;
        }else{
            return null;
        }

    }


}
