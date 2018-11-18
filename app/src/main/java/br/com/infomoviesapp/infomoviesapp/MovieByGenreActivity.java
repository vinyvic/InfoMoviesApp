package br.com.infomoviesapp.infomoviesapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import br.com.infomoviesapp.infomoviesapp.movie.Movie;
import br.com.infomoviesapp.infomoviesapp.movie.MovieAdapter;

public class MovieByGenreActivity extends AppCompatActivity {

    private int idGenre;
    private RecyclerView movieRecyclerView;
    private MovieAdapter movieAdapter;
    private List <Movie> movies;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_by_genre);

        /* Pegar id do Genero */
        getExtras();

        /* Construir ReciclerView */
        movieRecyclerView = findViewById(R.id.genreRecyclerView);
        setupRecycler();
    }

    private void getExtras(){
        if (getIntent().hasExtra("idGenre")){
            int id = getIntent().getIntExtra("idGenre", -1);
            setIdGenre(id);
        }
    }

    /**
     * Método privado chamado para inicializar o Recycler
     */
    private void setupRecycler() {

        // Configurando o gerenciador de layout para ser uma lista.
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        movieRecyclerView.setLayoutManager(layoutManager);

        // Adiciona o adapter que irá anexar os objetos à lista.
        // Está sendo criado com lista vazia, pois será preenchida posteriormente.
        movieAdapter = new MovieAdapter(MovieByGenreActivity.this, movies);
        movieRecyclerView.setAdapter(movieAdapter);
    }

    /* Get e Set */
    public int getIdGenre() {
        return idGenre;
    }

    public void setIdGenre(int idGenre) {
        this.idGenre = idGenre;
    }
}
