package com.example.android.imdb;

import java.io.Serializable;

/**
 * Created by Simra Afreen on 13-10-2017.
 */

public class Movie implements Serializable{

    private String title;
    private String poster_path;
    private int id;
    private double vote_average;
    private String overview;
    private String release_date;


    public Movie(String title, String poster_path, int id, double vote_average) {
        this.title = title;
        this.poster_path = poster_path;
        this.id = id;
        this.vote_average = vote_average;
    }

    public Movie(String title, String poster_path, String overview, String release_date) {
        this.title = title;
        this.poster_path = poster_path;
        this.overview = overview;
        this.release_date = release_date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getVote_average() {
        return vote_average;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }
}
