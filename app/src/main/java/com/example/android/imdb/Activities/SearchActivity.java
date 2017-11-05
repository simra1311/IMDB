package com.example.android.imdb.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.example.android.imdb.Adapters.SearchAdapter;
import com.example.android.imdb.Contract;
import com.example.android.imdb.GithubService;
import com.example.android.imdb.Movie;
import com.example.android.imdb.MovieResponse;
import com.example.android.imdb.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchActivity extends AppCompatActivity implements SearchAdapter.ExpenseClickListener{


    ArrayList<Movie> searchMovies;
    RecyclerView recyclerView;
    SearchAdapter adapter,adapterShows;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Log.i("TAG","SEarch");
        Intent intent = getIntent();
        String query = intent.getStringExtra("query");
        String type = intent.getStringExtra(Contract.SEARCH_TYPE);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        searchMovies = new ArrayList<>();
        adapter = new SearchAdapter(searchMovies, this, this);

        recyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.themoviedb.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final GithubService service = retrofit.create(GithubService.class);

        if (type.equals(Contract.MOVIES)) {
            Call<MovieResponse> movieResponseCall = service.getSearchedMovie(query);

            movieResponseCall.enqueue(new Callback<MovieResponse>() {
                @Override
                public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                    MovieResponse movieResponse = response.body();
                    if (movieResponse != null) {
                        searchMovies.clear();
                        searchMovies.addAll(movieResponse.results);
                        Log.i("TAG", "List found");
                        adapter.notifyDataSetChanged();
                    }
                }

                @Override
                public void onFailure(Call<MovieResponse> call, Throwable t) {
                    Toast.makeText(SearchActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            });
        }
//        else if (type.equals(Contract.SHOW)){
//            Call<TvResponse> movieResponseCall = service.getSearchedShow(query);
//
//            movieResponseCall.enqueue(new Callback<TvResponse>() {
//                @Override
//                public void onResponse(Call<TvResponse> call, Response<TvResponse> response) {
//                    TvResponse tvResponse = response.body();
//                    if (tvResponse != null) {
//                        searchMovies.clear();
//                        searchMovies.addAll(tvResponse.results);
//                        Log.i("TAG", "List found");
//                        adapter.notifyDataSetChanged();
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<TvResponse> call, Throwable t) {
//                    Toast.makeText(SearchActivity.this, "Error", Toast.LENGTH_SHORT).show();
//                }
//            });
//        }


    }

    @Override
    public void onItemClick(int position) {

        Movie movie = searchMovies.get(position);
        int id = movie.getId();
        Intent intent = new Intent(SearchActivity.this,MoviesActivity.class);
        intent.putExtra("id",id);
        startActivity(intent);

    }
}
