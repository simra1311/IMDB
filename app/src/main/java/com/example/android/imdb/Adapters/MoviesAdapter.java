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
 * Created by Simra Afreen on 08-10-2017.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {

    ArrayList<Movie> movieArrayList;
    Context mContext;
    ExpenseClickListener mListener;
    int type;

    public MoviesAdapter(ArrayList<Movie> movieArrayList, Context mContext, ExpenseClickListener mListener,int type) {
        this.movieArrayList = movieArrayList;
        this.mContext = mContext;
        this.mListener = mListener;
        this.type = type;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from(mContext).inflate(R.layout.row_layout,parent,false);
        return new MovieViewHolder(rowView);
    }

    @Override
    public void onBindViewHolder(final MovieViewHolder holder, int position) {

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    mListener.onItemClick(holder.getAdapterPosition(),type);
            }
        });
        Movie movie = movieArrayList.get(position);
        holder.title.setText(movie.getTitle());
        holder.popularity.setText(movie.getVote_average()+"");
        String movieUrl = movie.getPoster_path();
        Picasso.with(mContext).load("https://image.tmdb.org/t/p/w185" + movieUrl).placeholder(R.color.colorAccent).into(holder.poster);

    }

    @Override
    public int getItemCount() {
        return movieArrayList.size();
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        TextView popularity;
        ImageView poster;
        View itemView;

         MovieViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            title = itemView.findViewById(R.id.title);
            popularity = itemView.findViewById(R.id.vote_average);
            poster = itemView.findViewById(R.id.poster);
        }
    }

    public interface ExpenseClickListener{
        void onItemClick(int position,int type);
    }
}
