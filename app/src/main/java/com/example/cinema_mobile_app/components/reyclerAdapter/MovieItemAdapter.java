package com.example.cinema_mobile_app.components.reyclerAdapter;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinema_mobile_app.R;
import com.example.cinema_mobile_app.components.MovieItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieItemAdapter extends RecyclerView.Adapter<MovieItemAdapter.MyViewHolder> {

    private ArrayList<MovieItem> movieItemArrayList;

    public MovieItemAdapter(ArrayList<MovieItem> movieItemArrayList) {
        this.movieItemArrayList = movieItemArrayList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView title_date;
        private ImageView movie_poster;

        public MyViewHolder(final View view){
            super(view);
            title_date = view.findViewById(R.id.title_time);
            movie_poster = view.findViewById(R.id.movie_poster);
        }
    }

    @NonNull
    @Override
    public MovieItemAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MovieItemAdapter.MyViewHolder holder, int position) {
        MovieItem movieItem = movieItemArrayList.get(position);
        String title = movieItem.getTitle();
        String date = movieItem.getDate();
        String posterUrl = movieItem.getImageUrl();
        holder.title_date.setText(title + " - " + date);
        Picasso.with(holder.itemView.getContext()).load(posterUrl).into(holder.movie_poster);
    }

    @Override
    public int getItemCount() {
        return movieItemArrayList.size();
    }
}
