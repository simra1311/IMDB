package com.example.android.imdb;

import java.io.Serializable;

/**
 * Created by Simra Afreen on 02-11-2017.
 */

public class TV implements Serializable {

    private String name;
    private String poster_path;
    private int id;
    private double vote_average;
    private String overview;
    private String first_air_date;

    public TV(String name, String poster_path, int id, String first_air_date) {
        this.name = name;
        this.poster_path = poster_path;
        this.id = id;
        this.first_air_date = first_air_date;
    }

    public TV(String name, String poster_path, int id, double vote_average, String overview, String first_air_date) {
        this.name = name;

        this.poster_path = poster_path;
        this.id = id;
        this.vote_average = vote_average;
        this.overview = overview;
        this.first_air_date = first_air_date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getFirst_air_date() {
        return first_air_date;
    }

    public void setFirst_air_date(String first_air_date) {
        this.first_air_date = first_air_date;
    }


}
