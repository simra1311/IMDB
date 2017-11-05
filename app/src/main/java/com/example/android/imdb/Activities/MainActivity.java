package com.example.android.imdb.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.android.imdb.Adapters.MoviesAdapter;
import com.example.android.imdb.Contract;
import com.example.android.imdb.GithubService;
import com.example.android.imdb.Movie;
import com.example.android.imdb.MovieResponse;
import com.example.android.imdb.NetworkConnection;
import com.example.android.imdb.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, MoviesAdapter.ExpenseClickListener {

    RecyclerView recyclerView;
    RecyclerView recyclerView_now;
    RecyclerView recyclerView_top;
    RecyclerView recyclerView_upcoming;
    ArrayList<Movie> popular_moviesList;
    ArrayList<Movie> now_showing_list;
    ArrayList<Movie> top_rated_list;
    ArrayList<Movie> upcoming_list;
    MoviesAdapter adapter;
    MoviesAdapter adapter_now;
    MoviesAdapter adapter_top;
    MoviesAdapter adapter_upcoming;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("IMDB");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView_now = (RecyclerView)findViewById(R.id.recyclerView1);
        recyclerView_top = (RecyclerView)findViewById(R.id.recyclerView2);
        recyclerView_upcoming = (RecyclerView)findViewById(R.id.recyclerView3);

        popular_moviesList =new ArrayList<>();
        now_showing_list = new ArrayList<>();
        top_rated_list = new ArrayList<>();
        upcoming_list = new ArrayList<>();
        adapter = new MoviesAdapter(popular_moviesList, this,this, Contract.POPOULAR_LIST);
        adapter_now = new MoviesAdapter(now_showing_list,this,this,Contract.NOW_SHOWING_LIST);
        adapter_top = new MoviesAdapter(top_rated_list,this,this,Contract.TOP_RATED);
        adapter_upcoming = new MoviesAdapter(upcoming_list,this,this,Contract.UPCOMING);

        recyclerView.setAdapter(adapter);
        recyclerView_now.setAdapter(adapter_now);
        recyclerView_top.setAdapter(adapter_top);
        recyclerView_upcoming.setAdapter(adapter_upcoming);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);

        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView_now.setLayoutManager(layoutManager1);

        RecyclerView.LayoutManager layoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
        recyclerView_top.setLayoutManager(layoutManager2);

        RecyclerView.LayoutManager layoutManager3 = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView_upcoming.setLayoutManager(layoutManager3);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.themoviedb.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final GithubService service = retrofit.create(GithubService.class);

        Call<MovieResponse> movieResponseCall = service.getMovieResponse();
        Call<MovieResponse> now_playing = service.getNowPlaying();
        Call<MovieResponse> top_rated = service.getTopRatedMovie();
        final Call<MovieResponse> upcoming = service.getUpcoming();

        movieResponseCall.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                MovieResponse movieResponse = response.body();
                popular_moviesList.clear();
                popular_moviesList.addAll(movieResponse.results);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Error",Toast.LENGTH_SHORT).show();
            }
        });

        now_playing.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                MovieResponse movieResponse = response.body();
                now_showing_list.clear();
                now_showing_list.addAll(movieResponse.results);
                adapter_now.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Error",Toast.LENGTH_SHORT).show();
            }
        });

        top_rated.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                MovieResponse movieResponse = response.body();
                top_rated_list.clear();
                top_rated_list.addAll(movieResponse.results);
                adapter_top.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Error",Toast.LENGTH_SHORT).show();
            }
        });

        upcoming.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                MovieResponse movieResponse = response.body();
                upcoming_list.clear();
                upcoming_list.addAll(movieResponse.results);
                adapter_upcoming.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Error",Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onItemClick(int position,int type) {
        if (type == Contract.POPOULAR_LIST) {
            Movie movie = popular_moviesList.get(position);
            int id = movie.getId();
            Intent intent = new Intent(MainActivity.this, MoviesActivity.class);
            intent.putExtra("id", id);
            startActivity(intent);
        }
        else if (type == Contract.NOW_SHOWING_LIST){
            Movie movie = now_showing_list.get(position);
            int id = movie.getId();
            Intent intent = new Intent(MainActivity.this, MoviesActivity.class);
            intent.putExtra("id", id);
            startActivity(intent);
        }
        else if (type == Contract.TOP_RATED){
            Movie movie = top_rated_list.get(position);
            int id = movie.getId();
            Intent intent = new Intent(MainActivity.this, MoviesActivity.class);
            intent.putExtra("id", id);
            startActivity(intent);
        }
        else if (type == Contract.UPCOMING){
            Movie movie = upcoming_list.get(position);
            int id = movie.getId();
            Intent intent = new Intent(MainActivity.this, MoviesActivity.class);
            intent.putExtra("id", id);
            startActivity(intent);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.movies, menu);
        final MenuItem searchMenuItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) searchMenuItem.getActionView();
        searchView.setQueryHint("Search IMDB");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (!NetworkConnection.isConnected(MainActivity.this)) {
                    Toast.makeText(MainActivity.this, "No internet connection", Toast.LENGTH_SHORT).show();
                    return true;
                }
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                intent.putExtra("query", query);
                intent.putExtra(Contract.SEARCH_TYPE, Contract.MOVIES);
                startActivity(intent);
                searchMenuItem.collapseActionView();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {


            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if(id == R.id.nav_tv_shows){
            Intent intent = new Intent(MainActivity.this,TvShows.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
