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
    private long id;
    private String name;
    private int duration;
    private String artistName;
    private String albumName;
    private LocalDate releaseDate;
    private String categoryName;
    private long categoryId;

    public static SongDTOResponseDetail songToSongDTOResponseDetail(Song s){
        SongDTOResponseDetail sDTO;

        long idCat;
        String namCat;
        if (s.getCategory() != null) {
            idCat = s.getCategory().getId();
            namCat = s.getCategory().getName();
        } else {
            idCat = 0;
            namCat = "ninguna";
        }

        if(s!=null){
            sDTO = SongDTOResponseDetail.builder()
                    .id(s.getId())
                    .name(s.getName())
                    .duration(s.getDuration())
                    .artistName(s.getArtistName())
                    .albumName(s.getAlbumName())
                    .releaseDate(s.getReleaseDate())
                    .categoryName(namCat)
                    .categoryId(idCat)
                    .build();
            return sDTO;
        }else{
            return null;
        }
    }

}
