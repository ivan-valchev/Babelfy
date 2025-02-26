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

    // Convierte DTO a Song sin depender de la base de datos
    public static Song songDTOCreateToSong(SongDTORequestCreate songDTO, Category category) {
        if (songDTO == null) {
            return null;
        }

        return Song.builder()
                .name(songDTO.getName())
                .duration(songDTO.getDuration())
                .artistName(songDTO.getArtistName())
                .albumName(songDTO.getAlbumName())
                .releaseDate(songDTO.getReleaseDate())
                .category(category)  // Puede ser null sin problemas
                .build();
    }
}
