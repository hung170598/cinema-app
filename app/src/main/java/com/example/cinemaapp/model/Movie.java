package com.example.cinemaapp.model;

public class Movie {
    private long id;
    private String url;
    private String name;
    private long time;
    private long maxSlot;
    private long currentSlot;

    public Movie() {
    }

    public Movie(long id, String url, String name, long time, long maxSlot, long currentSlot) {
        this.id = id;
        this.url = url;
        this.name = name;
        this.time = time;
        this.maxSlot = maxSlot;
        this.currentSlot = currentSlot;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getMaxSlot() {
        return maxSlot;
    }

    public void setMaxSlot(long maxSlot) {
        this.maxSlot = maxSlot;
    }

    public long getCurrentSlot() {
        return currentSlot;
    }

    public void setCurrentSlot(long currentSlot) {
        this.currentSlot = currentSlot;
    }
}
