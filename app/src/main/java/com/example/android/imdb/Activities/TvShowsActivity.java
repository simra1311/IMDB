package com.example.android.imdb.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.imdb.GithubService;
import com.example.android.imdb.R;
import com.example.android.imdb.TvResponse;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TvShowsActivity extends AppCompatActivity {

    int id;
    ImageView poster1;
    TextView title1,id1,vote_average1,overview1,release_date1,status1,tagline1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_shows2);

        title1 = (TextView)findViewById(R.id.title);
        id1 = (TextView)findViewById(R.id.mov_id);
        vote_average1 = (TextView)findViewById(R.id.vote_average);
        overview1 = (TextView)findViewById(R.id.overview);
        release_date1 = (TextView)findViewById(R.id.release_date);
        status1 = (TextView)findViewById(R.id.status);
        tagline1 = (TextView)findViewById(R.id.tagline);
        poster1 = (ImageView)findViewById(R.id.poster);

        Intent intent = getIntent();
        //Movie movie = (Movie)intent.getSerializableExtra("MOVIE");
        id = intent.getIntExtra("id",-1);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.themoviedb.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final GithubService service = retrofit.create(GithubService.class);

        Call<TvResponse> tvActivityResponseCall = service.getShowInfo(id);
//        tvActivityResponseCall.enqueue(new Callback<TvActivityResponse>() {
//            @Override
//            public void onResponse(Call<TvActivityResponse> call, Response<TvActivityResponse> response) {
//                TvActivityResponse movie = response.body();
//
//                //TODO
//                String title = movie.getTitle();
//                String poster_path = movie.getPoster_path();
//                int id = movie.getId();
//                double vote_average = movie.getVote_average();
//                String overview = movie.getOverview();
//                String release_date = movie.getRelease_date();
//                String status = movie.getStatus();
//                String tagline = movie.getTagline();
//
//                Picasso.with(TvShowsActivity.this).load("https://image.tmdb.org/t/p/w185" + poster_path).placeholder(R.color.colorAccent).into(poster1);
//                title1.setText(title);
//                id1.setText(id+"");
//                vote_average1.setText(vote_average+"");
//                overview1.setText(overview);
//                release_date1.setText(release_date+"");
//                status1.setText(status);
//                tagline1.setText(tagline);
//            }
//
//            @Override
//            public void onFailure(Call<TvActivityResponse> call, Throwable t) {
//                Toast.makeText(TvShowsActivity.this,"Error",Toast.LENGTH_SHORT).show();
//            }
//        });

    }
}
