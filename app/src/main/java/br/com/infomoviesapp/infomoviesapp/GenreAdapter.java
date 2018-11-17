package br.com.infomoviesapp.infomoviesapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class GenreAdapter extends RecyclerView.Adapter <GenreHolder>{
    private final List <Genre> genresList;

    /* Construtor */
    public GenreAdapter(List Genres) {
        genresList = Genres;
    }

    @Override
    public GenreHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GenreHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.genre_line_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(GenreHolder holder, int position) {
        holder.genreTextView.setText(String.format(Locale.getDefault(), "%s",
                        genresList.get(position).getName()
        ));
    }

    @Override
    public int getItemCount() {
        return genresList != null ? genresList.size() : 0;
    }

    /**
     * Método publico chamado para atualziar a lista.
     * @param genre Novo objeto que será incluido na lista.
     */
    public void updateList(Genre genre) {
        insertItem(genre);
    }

    /**
     * Método privado chamado para inserir novos itens na lista.
     * @param genre Novo objeto que será incluido na lista.
     */
    private void insertItem(Genre genre) {
        genresList.add(genre);
    }
}
