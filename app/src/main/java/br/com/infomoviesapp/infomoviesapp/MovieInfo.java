package br.com.infomoviesapp.infomoviesapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/** Classe responsável por mostrar as informações de um filme
 *  @version 1.0
 *  @since 1.0
 */
public class MovieInfo extends AppCompatActivity {

    public int getIdMovie() {
        return idMovie;
    }

    private int idMovie;
    private ImageView movieImageView;
    private TextView movieTitleTextView;
    private TextView movieOverviewTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_info);

        getExtras();

        movieImageView = findViewById(R.id.movieImageView);
        movieTitleTextView = findViewById(R.id.movieTitleTextView);
        movieOverviewTextView = findViewById(R.id.movieOverviewTextView);

        WebServicePath webServicePath = new WebServicePath();
        webServicePath.setPathMovieInfo(idMovie);
        String url = webServicePath.getUrl();
        new getMovieInfo().execute(url);
    }

    /* Get e Set */
    public void setIdMovie(int idMovie) {
        this.idMovie = idMovie;
    }

    public ImageView getMovieImageView() {
        return movieImageView;
    }

    public void setMovieImageView(ImageView movieImageView) {
        this.movieImageView = movieImageView;
    }

    public TextView getMovieTitleTextView() {
        return movieTitleTextView;
    }

    public void setMovieTitleTextView(TextView movieTitleTextView) {
        this.movieTitleTextView = movieTitleTextView;
    }

    public TextView getMovieOverviewTextView() {
        return movieOverviewTextView;
    }

    public void setMovieOverviewTextView(TextView movieOverviewTextView) {
        this.movieOverviewTextView = movieOverviewTextView;
    }

    // Pegar variáveis de passagem
    private void getExtras(){
        if (getIntent().hasExtra("idMovie")){
            int id = getIntent().getIntExtra("idMovie", -1);
            setIdMovie(id);
        }
    }


    private class getMovieInfo extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            try {
                URL url = new URL(urls[0]);
                HttpURLConnection connection =
                        (HttpURLConnection) url.openConnection();
                InputStream stream = connection.getInputStream();
                InputStreamReader inputStreamReader =
                        new InputStreamReader(stream);
                StringBuilder stringBuilder = new StringBuilder("");
                //try with resources
                try (BufferedReader reader = new BufferedReader(inputStreamReader)) {
                    String linha = null;
                    String json = "";
                    while ((linha = reader.readLine()) != null)
                        stringBuilder.append(linha);
                }
                connection.disconnect();
                return stringBuilder.toString();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String jsonS) {
            try {
                JSONObject json = new JSONObject(jsonS);

                /* Capturar dados do json */
                String imageUrl = json.getString("poster_path");
                String title = json.getString("title");
                String overView = json.getString("overview");

                /* Montar url da imagem */
                WebServicePathImage webServicePathImage = new WebServicePathImage(imageUrl);
                imageUrl = webServicePathImage.getUrl();

                Log.d("DEBUG", String.format("img: %s, title: %s, overview: %s", imageUrl, title, overView));

                /* Baixar imagem com o picasso e atribuir a imageview*/
                Picasso.with(MovieInfo.this).load(imageUrl).into(movieImageView);

                /* Atribuir texto aos textviews */
                movieTitleTextView.setText(title);
                movieOverviewTextView.setText(overView);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
