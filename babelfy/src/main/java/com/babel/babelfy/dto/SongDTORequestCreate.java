package com.babel.babelfy.dto;


import com.babel.babelfy.model.Category;
import com.babel.babelfy.model.Song;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
@Data
@Builder
public class SongDTORequestCreate {


    private String name;
    private int duration;
    private String artistName;
    private String albumName;
    private LocalDate releaseDate;
    private Long categoryId;


    public static Song songDTOCreateToSong(SongDTORequestCreate song, Category category) {
        Song s;

        if (song != null) {
            s = Song.builder()
                    .name(song.getName())
                    .duration(song.getDuration())
                    .artistName(song.getArtistName())
                    .albumName(song.getAlbumName())
                    .releaseDate(song.getReleaseDate())
                    .category(category)  // Asignar categoría
                    .build();
            return s;
        } else {
            return null;
        }
    }


}
