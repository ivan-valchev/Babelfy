package com.babel.babelfy.dto;


import com.babel.babelfy.model.Category;
import com.babel.babelfy.model.Song;
import com.babel.babelfy.repository.CategoryRepository;
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


    private static CategoryRepository categoryRepository;
    public static Song songDTOCreateToSong(SongDTORequestCreate songDTO) {
        Song song = null;

        if (songDTO != null) {
            // Buscar la categoría usando el categoryId
            Category category = categoryRepository.findById(songDTO.getCategoryId()).orElse(null);

            // Crear el objeto Song con la categoría obtenida
            if (category != null) {
                song = Song.builder()
                        .name(songDTO.getName())
                        .duration(songDTO.getDuration())
                        .artistName(songDTO.getArtistName())
                        .albumName(songDTO.getAlbumName())
                        .releaseDate(songDTO.getReleaseDate())
                        .category(category)  // Asignar la categoría
                        .build();
            }
        }

        return song;
    }
}



