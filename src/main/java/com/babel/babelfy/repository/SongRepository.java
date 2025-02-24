package com.babel.babelfy.repository;

import com.babel.babelfy.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;

<<<<<<< HEAD
public interface SongRepository extends JpaRepository<Song, Long> {
=======
import java.util.List;

public interface SongRepository extends JpaRepository<Song, Long> {
    List<Song>findByName(String name);
>>>>>>> origin/dev
}
