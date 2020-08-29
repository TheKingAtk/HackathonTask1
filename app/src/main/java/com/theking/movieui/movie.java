package com.theking.movieui;

import java.util.List;

public class movie {
    int imgResource;
    String name;
    String genre;

    public List<Integer> getImages() {
        return images;
    }

    public void setImages(List<Integer> images) {
        this.images = images;
    }

    List<Integer> images;
    public void setImgResource(int imgResource) {
        this.imgResource = imgResource;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    float rating;

    public int getImgResource() {
        return imgResource;
    }

    public String getName() {

        return name;
    }

    public String getGenre() {
        return genre;
    }

    public float getRating() {
        return rating;
    }

    public String getDescription() {
        return description;
    }

    String description;
}
