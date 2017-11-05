package com.example.android.imdb;

/**
 * Created by Simra Afreen on 02-11-2017.
 */

public class MovieActivityResponse {

    private String title;
    private String poster_path;
    private int id;
    private double vote_average;
    private String overview;
    private String release_date;
    private String status;
    private String tagline;

    public MovieActivityResponse(String title, String poster_path, int id, double vote_average, String overview, String release_date, String status, String tagline) {
        this.title = title;
        this.poster_path = poster_path;
        this.id = id;
        this.vote_average = vote_average;
        this.overview = overview;
        this.release_date = release_date;
        this.status = status;
        this.tagline = tagline;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }
}
