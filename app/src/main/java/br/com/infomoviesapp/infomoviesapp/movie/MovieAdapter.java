package br.com.infomoviesapp.infomoviesapp.movie;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Locale;

import br.com.infomoviesapp.infomoviesapp.MovieInfo;
import br.com.infomoviesapp.infomoviesapp.R;
import br.com.infomoviesapp.infomoviesapp.WebServicePath;
import br.com.infomoviesapp.infomoviesapp.WebServicePathImage;

public class MovieAdapter extends RecyclerView.Adapter <MovieHolder> {
    private final List<Movie> movieList;
    private Context mContext;

    /* Construtor */
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
        //new downloadImage(holder.movieImageView).execute(urlImage);

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

    /* Download das imagens sem Picasso
    private class downloadImage extends AsyncTask<String, Void, Bitmap> {

        private ImageView movieImageView;
        public downloadImage (ImageView movieImageView){
            this.movieImageView = movieImageView;
    }

        @Override
        protected void onPostExecute(Bitmap figura) {
            movieImageView.setImageBitmap(figura);
        }

        @Override
        protected Bitmap doInBackground(String... urls) {
            try {
                URL url = new URL(urls[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = connection.getInputStream();
                Bitmap figura = BitmapFactory.decodeStream(inputStream);
                //Bitmap.createScaledBitmap(figura, 40, 60, false);
                return figura;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
    */
}
