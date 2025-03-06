package com.babel.babelfy.dto;

import com.babel.babelfy.model.Category;
import com.babel.babelfy.model.Song;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SongDTOResponseList {

    private long id;
    private String name;


    public static SongDTOResponseList songToSongResponseList(Song s){
        SongDTOResponseList sDTO;
        if(s!=null){
            sDTO = SongDTOResponseList.builder()
                    .id(s.getId())
                    .name(s.getName())
                    .build();
            return sDTO;

        }else{
            return null;
        }

    }


}
