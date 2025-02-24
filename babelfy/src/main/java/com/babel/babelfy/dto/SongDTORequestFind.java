package com.babel.babelfy.dto;

import com.babel.babelfy.model.Song;
import lombok.Data;

@Data
public class SongDTORequestFind {

    private long id;



//    public static Song songDTOFindToSong(SongDTORequestFind sDTO){
//        Song s;
//        if(sDTO!=null){
//           s = Song.builder()
//                   .id(sDTO.getId())
//                   .build();
//           return s;
//        }else{
//            return null;
//        }
//
//    }

}
