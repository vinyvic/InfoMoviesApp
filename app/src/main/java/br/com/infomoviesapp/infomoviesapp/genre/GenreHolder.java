package br.com.infomoviesapp.infomoviesapp.genre;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import br.com.infomoviesapp.infomoviesapp.R;

/** Classe Holder da RecyclerView de Gêneros
 * @version 1.0
 * @since 1.0
 */
public class GenreHolder extends RecyclerView.ViewHolder {
    public TextView genreTextView;

    public GenreHolder(View itemView) {
        super(itemView);
        genreTextView = itemView.findViewById(R.id.genreTextView);
    }
}
