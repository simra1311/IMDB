package com.example.android.imdb.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.imdb.Movie;
import com.example.android.imdb.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Simra Afreen on 25-10-2017.
 */

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.MovieViewHolder> {

    ArrayList<Movie> movieArrayList;
    Context mContext;
    ExpenseClickListener mListener;

    public SearchAdapter(ArrayList<Movie> movieArrayList, Context mContext, ExpenseClickListener mListener) {
        this.movieArrayList = movieArrayList;
        this.mContext = mContext;
        this.mListener = mListener;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from(mContext).inflate(R.layout.row_layout_search,parent,false);
        return new MovieViewHolder(rowView);
    }

    @Override
    public void onBindViewHolder(final MovieViewHolder holder, int position) {

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onItemClick(holder.getAdapterPosition());
            }
        });
        Movie movie = movieArrayList.get(position);
        holder.title.setText(movie.getTitle());
      //holder.overview.setText(movie.getOverview());
        holder.release.setText(movie.getRelease_date());
        String movieUrl = movie.getPoster_path();
        //TODO
        Picasso.with(mContext).load("https://image.tmdb.org/t/p/w185" + movieUrl).placeholder(R.color.colorAccent).into(holder.poster);



    }

    @Override
    public int getItemCount() {
        return movieArrayList.size();
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        TextView release;
        ImageView poster;
        View itemView;

        public MovieViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            title = itemView.findViewById(R.id.title);
            release = itemView.findViewById(R.id.release_date);
            poster = itemView.findViewById(R.id.poster);
//            itemView.setOnClickListener(this);
//            Picasso.with().load(Movie).into(poster);
        }

    }

    public interface ExpenseClickListener{
        void onItemClick(int position);
    }
}

