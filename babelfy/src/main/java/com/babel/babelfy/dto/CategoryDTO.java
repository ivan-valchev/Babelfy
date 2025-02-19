package com.babel.babelfy.dto;

import java.util.List;

public class CategoryDTO {

    private long id;
    private String name;
    private List<Long> songIds;

    public CategoryDTO(long id, String name, List<Long> songIds) {
        this.id = id;
        this.name = name;
        this.songIds = songIds;
    }

    // Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Long> getSongIds() {
        return songIds;
    }

    public void setSongIds(List<Long> songIds) {
        this.songIds = songIds;
    }
}
