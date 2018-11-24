package br.com.infomoviesapp.infomoviesapp.movie;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;
import java.util.List;
import java.util.Locale;

import br.com.infomoviesapp.infomoviesapp.MovieInfo;
import br.com.infomoviesapp.infomoviesapp.R;
import br.com.infomoviesapp.infomoviesapp.WebServicePathImage;

/** Classe Adapter para a RecyclerView de Filmes por Gêneros
 * @version 1.0
 * @since 1.0
 */
public class MovieAdapter extends RecyclerView.Adapter <MovieHolder> {
    private final List<Movie> movieList;
    private Context mContext;

    /** Método Construtor
     */
    public MovieAdapter(Context context, List<Movie> movieList) {
        this.mContext = context;
        this.movieList = movieList;
    }

    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        return new MovieHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_line_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(MovieHolder holder, final int position) {

        // Criar variáveis com os atributos do filme
        String movieName = movieList.get(position).getTitle();
        String moviePosterPath = movieList.get(position).getPosterPath();

        // Setar título do filme na textView
        holder.movieTextView.setText(String.format(Locale.getDefault(),"%s", movieName));

        // Chamar classe para montar url da imagem
        WebServicePathImage webServicePathImage = new WebServicePathImage(moviePosterPath);
        String urlImage = webServicePathImage.getUrl();
        //Log.d("DEBUG/IMAGE", urlImage);

        // Utilizar Biblioteca Picasso para o download de imagens, fazer cache das imagens e atribuir a imageView
        Picasso.with(mContext).load(urlImage).into(holder.movieImageView);

        // Atribuir Link para informações do filme
        // Definir Link
        holder.movieTextView.setOnClickListener(new View.OnClickListener() {
            int idMovie = movieList.get(position).getId();

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MovieInfo.class);
                intent.putExtra("idMovie", idMovie);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieList != null ? movieList.size() : 0;
    }

}
