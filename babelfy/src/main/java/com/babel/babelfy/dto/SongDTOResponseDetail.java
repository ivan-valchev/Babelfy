package com.babel.babelfy.dto;

import com.babel.babelfy.model.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.babel.babelfy.model.Song;

import java.time.LocalDate;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SongDTOResponseDetail {

    private String name;
    private int duration;
    private String artistName;
    private String albumName;
    private LocalDate releaseDate;
//    private String categoryName;

    public static SongDTOResponseDetail songToSongDTOResponseDetail(Song s){
        SongDTOResponseDetail sDTO;

        if(s!=null){
            sDTO = SongDTOResponseDetail.builder()
                    .name(s.getName())
                    .duration(s.getDuration())
                    .artistName(s.getArtistName())
                    .albumName(s.getAlbumName())
                    .releaseDate(s.getReleaseDate())
                    .build();
            return sDTO;
        }else{
            return null;
        }
    }

}
