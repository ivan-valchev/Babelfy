//package com.babel.babelfy.dto;
//
//import com.babel.babelfy.model.Category;
//
//import java.util.Date;
//
//public class SongDTOResponse {
//    private Category category;
//    private String name;
//    private int duration;
//    private String artistName;
//    private String albumName;
//    private Date releaseDate;
//}


package com.babel.babelfy.dto;

import com.babel.babelfy.model.Song;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SongDTOResponse {
    private long id;
    private String name;
    private int duration;
    private String artistName;
    private String albumName;
    private LocalDate releaseDate;

    // Constructor para convertir Song en SongDTOResponse
    public SongDTOResponse(Song song) {
        this.id = song.getId();
        this.name = song.getName();
        this.duration = song.getDuration();
        this.artistName = song.getArtistName();
        this.albumName = song.getAlbumName();
        this.releaseDate = song.getReleaseDate();
    }
}

