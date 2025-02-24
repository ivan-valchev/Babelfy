package com.babel.babelfy.repository;

import com.babel.babelfy.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SongRepository extends JpaRepository<Song, Long> {

}
