package br.com.infomoviesapp.infomoviesapp.genre;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
import java.util.Locale;

import br.com.infomoviesapp.infomoviesapp.MovieByGenreActivity;
import br.com.infomoviesapp.infomoviesapp.R;

/** Classe Adapter para a RecyclerView de Gêneros
 * @version 1.0
 * @since 1.0
 */
public class GenreAdapter extends RecyclerView.Adapter <GenreHolder>{
    private final List <Genre> genresList;
    private Context mContext;

    /* Construtor */
    public GenreAdapter(Context context, List Genres) {
        genresList = Genres;
        mContext = context;
    }

    @NonNull
    @Override
    public GenreHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new GenreHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.genre_line_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(GenreHolder holder, final int position) {

        String Genrename = genresList.get(position).getName();

        // Setar texto nome do Genero
        holder.genreTextView.setText(String.format(Locale.getDefault(), "%s", Genrename));

        // Definir Link
        holder.genreTextView.setOnClickListener(new View.OnClickListener() {
            int Genreid = genresList.get(position).getId();

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, MovieByGenreActivity.class);
                intent.putExtra("idGenre", Genreid);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return genresList != null ? genresList.size() : 0;
    }
}
