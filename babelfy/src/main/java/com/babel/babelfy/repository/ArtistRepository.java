package com.babel.babelfy.repository;
import com.babel.babelfy.model.Artist;
import com.babel.babelfy.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArtistRepository extends JpaRepository<Artist, Long> {

}
