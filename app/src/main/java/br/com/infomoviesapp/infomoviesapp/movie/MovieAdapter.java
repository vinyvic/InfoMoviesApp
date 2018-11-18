package br.com.infomoviesapp.infomoviesapp.movie;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;
import java.util.Locale;

import br.com.infomoviesapp.infomoviesapp.R;

public class MovieAdapter extends RecyclerView.Adapter <MovieHolder> {
    private final List<Movie> movieList;
    private Context mContext;

    /* Construtor */
    public MovieAdapter(Context mContext, List<Movie> movieList) {
        this.mContext = mContext;
        this.movieList = movieList;
    }

    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        return new MovieHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_line_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(MovieHolder holder, final int position) {

        String movieName = movieList.get(position).getTitle();
        holder.movieTextView.setText(String.format(Locale.getDefault(),"%s", movieName));
    }

    @Override
    public int getItemCount() {
        return movieList != null ? movieList.size() : 0;
    }


}
