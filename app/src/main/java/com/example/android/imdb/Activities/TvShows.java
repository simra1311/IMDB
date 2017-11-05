package com.example.android.imdb.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.android.imdb.Adapters.TvShowsAdapter;
import com.example.android.imdb.Contract;
import com.example.android.imdb.GithubService;
import com.example.android.imdb.NetworkConnection;
import com.example.android.imdb.R;
import com.example.android.imdb.TV;
import com.example.android.imdb.TvResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TvShows extends AppCompatActivity implements TvShowsAdapter.ExpenseClickListener {

    RecyclerView recyclerViewPopular,recyclerViewTopRated,recyclerViewAiringToday,recyclerViewOnTheAir;
    ArrayList<TV> popularList,topRatedList,airingTodayList,onTheAirList;
    TvShowsAdapter adapterPopular,adapterTopRated,adapterAiringToday,adapterAired;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_shows);

        recyclerViewPopular = (RecyclerView)findViewById(R.id.recyclerView2);
        recyclerViewAiringToday = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerViewOnTheAir = (RecyclerView)findViewById(R.id.recyclerView1);
        recyclerViewTopRated = (RecyclerView)findViewById(R.id.recyclerView3);

        popularList = new ArrayList<>();
        topRatedList = new ArrayList<>();
        airingTodayList = new ArrayList<>();
        onTheAirList = new ArrayList<>();

        adapterPopular = new TvShowsAdapter(popularList, this,this, Contract.POPOULAR_LIST);
        recyclerViewPopular.setAdapter(adapterPopular);
        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerViewPopular.setLayoutManager(layoutManager1);

        adapterAired = new TvShowsAdapter(onTheAirList, this,this,Contract.ON_THE_AIR_LIST);
        recyclerViewOnTheAir.setAdapter(adapterAired);
        RecyclerView.LayoutManager layoutManager2 = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerViewOnTheAir.setLayoutManager(layoutManager2);

        adapterAiringToday = new TvShowsAdapter(airingTodayList, this,this,Contract.AIRING_TODAY_LIST);
        recyclerViewAiringToday.setAdapter(adapterAiringToday);
        RecyclerView.LayoutManager layoutManager3 = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerViewAiringToday.setLayoutManager(layoutManager3);

        adapterTopRated = new TvShowsAdapter(topRatedList, this,this,Contract.TOP_RATED_LIST);
        recyclerViewTopRated.setAdapter(adapterTopRated);
        RecyclerView.LayoutManager layoutManager4 = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerViewTopRated.setLayoutManager(layoutManager4);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.themoviedb.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final GithubService service = retrofit.create(GithubService.class);

        Call<TvResponse> popular = service.getPopularTvShows();
        popular.enqueue(new Callback<TvResponse>() {
            @Override
            public void onResponse(Call<TvResponse> call, Response<TvResponse> response) {
                TvResponse tvResponse = response.body();
                popularList.clear();
                popularList.addAll(tvResponse.results);
                adapterPopular.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<TvResponse> call, Throwable t) {
                Toast.makeText(TvShows.this,"Error",Toast.LENGTH_SHORT).show();
            }
        });

        Call<TvResponse> airingToday = service.getAiringToday();
        airingToday.enqueue(new Callback<TvResponse>() {
            @Override
            public void onResponse(Call<TvResponse> call, Response<TvResponse> response) {
                TvResponse tvResponse = response.body();
                airingTodayList.clear();
                airingTodayList.addAll(tvResponse.results);
                adapterAiringToday.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<TvResponse> call, Throwable t) {
                Toast.makeText(TvShows.this,"Error",Toast.LENGTH_SHORT).show();
            }
        });

        Call<TvResponse> topRated = service.getTopRatedShows();
        topRated.enqueue(new Callback<TvResponse>() {
            @Override
            public void onResponse(Call<TvResponse> call, Response<TvResponse> response) {
                TvResponse tvResponse = response.body();
                topRatedList.clear();
                topRatedList.addAll(tvResponse.results);
                adapterTopRated.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<TvResponse> call, Throwable t) {
                Toast.makeText(TvShows.this,"Error",Toast.LENGTH_SHORT).show();
            }
        });

        Call<TvResponse> onTheAir = service.getOnTheAirShows();
        onTheAir.enqueue(new Callback<TvResponse>() {
            @Override
            public void onResponse(Call<TvResponse> call, Response<TvResponse> response) {
                TvResponse tvResponse = response.body();
                onTheAirList.clear();
                onTheAirList.addAll(tvResponse.results);
                adapterAired.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<TvResponse> call, Throwable t) {
                Toast.makeText(TvShows.this,"Error",Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public void onItemClick(int position,int type) {
        if (type == Contract.POPOULAR_LIST){
            TV tv = popularList.get(position);
            int id = tv.getId();
            Intent intent = new Intent(TvShows.this,TvShowsActivity.class);
            intent.putExtra("id",id);
            startActivity(intent);
        }

        else if (type == Contract.TOP_RATED_LIST){
            TV tv = topRatedList.get(position);
            int id = tv.getId();
            Intent intent = new Intent(TvShows.this,TvShowsActivity.class);
            intent.putExtra("id",id);
            startActivity(intent);
        }
        else  if (type == Contract.AIRING_TODAY_LIST){
            TV tv = airingTodayList.get(position);
            int id = tv.getId();
            Intent intent = new Intent(TvShows.this,TvShowsActivity.class);
            intent.putExtra("id",id);
            startActivity(intent);
        }
        else  if (type == Contract.ON_THE_AIR_LIST){
            TV tv = onTheAirList.get(position);
            int id = tv.getId();
            Intent intent = new Intent(TvShows.this,TvShowsActivity.class);
            intent.putExtra("id",id);
            startActivity(intent);
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
                if (!NetworkConnection.isConnected(TvShows.this)) {
                    Toast.makeText(TvShows.this, "No internet connection", Toast.LENGTH_SHORT).show();
                    return true;
                }
                Intent intent = new Intent(TvShows.this, SearchTvActivity.class);
                intent.putExtra("query", query);
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
}
