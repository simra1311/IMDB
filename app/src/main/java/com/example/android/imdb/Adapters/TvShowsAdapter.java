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
 * Created by Simra Afreen on 02-11-2017.
 */

public class TvShowsAdapter extends RecyclerView.Adapter<TvShowsAdapter.MovieViewHolder> {

    ArrayList<TV> tvArrayList;
    Context mContext;
    TvShowsAdapter.ExpenseClickListener mListener;
    int type;

    public TvShowsAdapter(ArrayList<TV> tvArrayList, Context mContext, TvShowsAdapter.ExpenseClickListener mListener,int type) {
        this.tvArrayList = tvArrayList;
        this.mContext = mContext;
        this.mListener = mListener;
        this.type = type;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from(mContext).inflate(R.layout.row_layout_tv,parent,false);
        return new TvShowsAdapter.MovieViewHolder(rowView);
    }

    @Override
    public void onBindViewHolder(final MovieViewHolder holder, int position) {

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onItemClick(holder.getAdapterPosition(),type);
            }
        });
        TV tv = tvArrayList.get(position);
        holder.name.setText(tv.getName());
        holder.first_air_date.setText(tv.getFirst_air_date()+"");
        String movieUrl = tv.getPoster_path();
        Picasso.with(mContext).load("https://image.tmdb.org/t/p/w185" + movieUrl).placeholder(R.color.colorAccent).into(holder.poster);

    }

    @Override
    public int getItemCount() {
        return tvArrayList.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {


        TextView name;
        TextView first_air_date;
        ImageView poster;
        View itemView;

        public MovieViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            name = itemView.findViewById(R.id.name);
            first_air_date = itemView.findViewById(R.id.first_air_date);
            poster = itemView.findViewById(R.id.poster_path);
        }


    }

    public interface ExpenseClickListener{
        void onItemClick(int position,int type);
    }
}
