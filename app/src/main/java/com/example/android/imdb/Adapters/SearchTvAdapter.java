package com.example.android.imdb.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.imdb.R;
import com.example.android.imdb.TV;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Simra Afreen on 04-11-2017.
 */

public class SearchTvAdapter extends RecyclerView.Adapter<SearchAdapter.MovieViewHolder> {

    ArrayList<TV> tvArrayList;
    Context mContext;
    SearchAdapter.ExpenseClickListener mListener;

    public SearchTvAdapter(ArrayList<TV> movieArrayList, Context mContext, SearchAdapter.ExpenseClickListener mListener) {
        this.tvArrayList = movieArrayList;
        this.mContext = mContext;
        this.mListener = mListener;
    }

    @Override
    public SearchAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from(mContext).inflate(R.layout.row_layout_search_tv,parent,false);
        return new SearchAdapter.MovieViewHolder(rowView);
    }

    @Override
    public void onBindViewHolder(final SearchAdapter.MovieViewHolder holder, int position) {

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onItemClick(holder.getAdapterPosition());
            }
        });
        TV tv = tvArrayList.get(position);
        holder.title.setText(tv.getName());
        //holder.overview.setText(movie.getOverview());
        holder.release.setText(tv.getFirst_air_date());
        String movieUrl = tv.getPoster_path();
        //TODO
        Picasso.with(mContext).load("https://image.tmdb.org/t/p/w185" + movieUrl).placeholder(R.color.colorAccent).into(holder.poster);



    }

    @Override
    public int getItemCount() {
        return tvArrayList.size();
    }

    public static class TvViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        TextView release;
        ImageView poster;
        View itemView;

        public TvViewHolder(View itemView) {
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

