package br.com.infomoviesapp.infomoviesapp;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class GenreHolder extends RecyclerView.ViewHolder {
    public TextView genreTextView;

    public GenreHolder(View itemView) {
        super(itemView);
        genreTextView = itemView.findViewById(R.id.genreTextView);
    }
}
