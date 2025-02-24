package com.babel.babelfy.dto;

import com.babel.babelfy.model.Category;
<<<<<<< HEAD
import lombok.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SongDTO {
    private long id;
=======
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;
@Data
public class SongDTO {
    private long id;
    private Category category;
>>>>>>> origin/dev
    private String name;
    private int duration;
    private String artistName;
    private String albumName;
<<<<<<< HEAD
    private String releaseDate;
    private Category category;
}
=======
    private LocalDate releaseDate;
    private Long categoryId;

}
>>>>>>> origin/dev
