//package com.babel.babelfy.dto;
//
//import com.babel.babelfy.model.Category;
//
//import java.util.Date;
//
//public class SongDTORequest {
//
//    private Category category;
//    private String name;
//    private int duration;
//    private String artistName;
//    private String albumName;
//    private Date releaseDate;
//
//}

package com.babel.babelfy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SongDTORequest {
    private long id;  // Se necesita para identificar la canción
    private String name;
    private int duration;
    private String artistName;
    private String albumName;
    private LocalDate releaseDate;
}
