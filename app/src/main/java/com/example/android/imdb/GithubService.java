package com.example.android.imdb;

/**
 * Created by Simra Afreen on 07-10-2017.
 */
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GithubService {


    @GET("3/movie/popular?api_key=64134360ff2ea9539bf2b460512e0a91")
    Call<MovieResponse> getMovieResponse();

    @GET("3/movie/now_playing?api_key=64134360ff2ea9539bf2b460512e0a91&language=en-US&page=1")
    Call<MovieResponse> getNowPlaying();

    @GET("3/search/movie?api_key=64134360ff2ea9539bf2b460512e0a91")
    Call<MovieResponse> getSearchedMovie(@Query("query") String query);
    @GET("3/movie/{movie_id}?api_key=64134360ff2ea9539bf2b460512e0a91")
    Call<MovieActivityResponse> getMovieInfo(@Path("movie_id") int id);
    @GET("3/movie/top_rated?api_key=64134360ff2ea9539bf2b460512e0a91")
    Call<MovieResponse> getTopRatedMovie();
    @GET("3/movie/upcoming?api_key=64134360ff2ea9539bf2b460512e0a91")
    Call<MovieResponse> getUpcoming();

    @GET("3/tv/popular?api_key=64134360ff2ea9539bf2b460512e0a91")
    Call<TvResponse> getPopularTvShows();
    @GET("3/tv/airing_today?api_key=64134360ff2ea9539bf2b460512e0a91")
    Call<TvResponse> getAiringToday();
    @GET("3/tv/top_rated?api_key=64134360ff2ea9539bf2b460512e0a91")
    Call<TvResponse> getTopRatedShows();
    @GET("3/tv/on_the_air?api_key=64134360ff2ea9539bf2b460512e0a91")
    Call<TvResponse> getOnTheAirShows();
    @GET("3/tv/{id}?api_key=64134360ff2ea9539bf2b460512e0a91")
    Call<TvResponse> getShowInfo(@Path("id") int id);

    Call<TvResponse> getSearchedShow(String query);



//    @POST(" ")
//    Call<User> hello();
}
