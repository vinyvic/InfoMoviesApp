package br.com.infomoviesapp.infomoviesapp.movie;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.infomoviesapp.infomoviesapp.R;

/** Classe Holder da RecyclerView de Filmes por Gênero
 */
public class MovieHolder extends RecyclerView.ViewHolder {

    public TextView movieTextView;
    public ImageView movieImageView;

    public MovieHolder(View itemView) {
        super(itemView);
        movieTextView = itemView.findViewById(R.id.movieTextView);
        movieImageView = itemView.findViewById(R.id.movieImageView);
    }
}
