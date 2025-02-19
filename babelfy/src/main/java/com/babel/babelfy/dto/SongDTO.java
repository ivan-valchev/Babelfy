//package com.babel.babelfy.dto;
//
//<<<<<<< HEAD
//import java.time.LocalDate;
//
//public class SongDTO {
//
//    private long id;
//=======
//import com.babel.babelfy.model.Category;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.ManyToOne;
//
//import java.util.Date;
//
//public class SongDTO {
//    private long id;
//    private Category category;
//    private String name;
//    private int duration;
//    private String artistName;
//    private String albumName;
//    private LocalDate releaseDate;
//    private Long categoryId;
//
//    public SongDTO(long id, String name, int duration, String artistName, String albumName, LocalDate releaseDate, Long categoryId) {
//        this.id = id;
//        this.name = name;
//        this.duration = duration;
//        this.artistName = artistName;
//        this.albumName = albumName;
//        this.releaseDate = releaseDate;
//        this.categoryId = categoryId;
//    }
//
//    // Getters and Setters
//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public int getDuration() {
//        return duration;
//    }
//
//    public void setDuration(int duration) {
//        this.duration = duration;
//    }
//
//    public String getArtistName() {
//        return artistName;
//    }
//
//    public void setArtistName(String artistName) {
//        this.artistName = artistName;
//    }
//
//    public String getAlbumName() {
//        return albumName;
//    }
//
//    public void setAlbumName(String albumName) {
//        this.albumName = albumName;
//    }
//
//    public LocalDate getReleaseDate() {
//        return releaseDate;
//    }
//
//    public void setReleaseDate(LocalDate releaseDate) {
//        this.releaseDate = releaseDate;
//    }
//
//    public Long getCategoryId() {
//        return categoryId;
//    }
//
//    public void setCategoryId(Long categoryId) {
//        this.categoryId = categoryId;
//    }
//
//    private Date releaseDate;
//
//}
