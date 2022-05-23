package com.example.cinemaapp.model;

import java.time.LocalDateTime;

public class Ticket {
    private String id;
    private String movieName;
    private String time;

    public Ticket() {
    }

    public Ticket(String id, String movieName, String time) {
        this.id = id;
        this.movieName = movieName;
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
