package br.com.infomoviesapp.infomoviesapp.activitys;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import br.com.infomoviesapp.infomoviesapp.R;
import br.com.infomoviesapp.infomoviesapp.helpers.GoToActivity;
import br.com.infomoviesapp.infomoviesapp.helpers.InfoMoviesAppMenuItem;
import br.com.infomoviesapp.infomoviesapp.helpers.SetupHeader;
import br.com.infomoviesapp.infomoviesapp.helpers.WebServicePath;
import br.com.infomoviesapp.infomoviesapp.helpers.WebServicePathImage;

/** Classe responsável por mostrar as informações de um filme
 */
public class MovieInfoActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private int idMovie;
    private String titleMovie;
    private ImageView movieImageView;
    private TextView movieTitleTextView;
    private TextView movieOverviewTextView;
    private ProgressBar loadingProgressBar;
    private DrawerLayout drawerLayout;
    private Toolbar mainToolBar;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_info);

        /* Mostrar loading */
        loadingProgressBar = findViewById(R.id.loadingProgressBar);
        loadingProgressBar.setVisibility(View.VISIBLE);

        getExtras();

        movieImageView = findViewById(R.id.movieImageView);
        movieTitleTextView = findViewById(R.id.movieTitleTextView);
        movieOverviewTextView = findViewById(R.id.movieOverviewTextView);
        movieOverviewTextView.setMovementMethod(new ScrollingMovementMethod());

        /* Menu e Toolbar */
        mainToolBar = findViewById(R.id.mainToolBar);
        navigationView = findViewById(R.id.navView);
        drawerLayout = findViewById(R.id.drawerLayout);
        String title = titleMovie;
        new SetupHeader(this, mainToolBar, navigationView, drawerLayout, title);

        WebServicePath webServicePath = new WebServicePath();
        webServicePath.setPathMovieInfo(idMovie);
        String url = webServicePath.getUrl();
        new getMovieInfo().execute(url);
    }

    /* Get e Set */

    public int getIdMovie() {
        return idMovie;
    }

    public void setTitleMovie(String titleMovie) {
        this.titleMovie = titleMovie;
    }

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
        if (getIntent().hasExtra("titleMovie")){
            String title = getIntent().getStringExtra("titleMovie");
            setTitleMovie(title);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        InfoMoviesAppMenuItem infoMoviesAppMenuItem = new InfoMoviesAppMenuItem(this, item);
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
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
            if (jsonS != null) {
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
                    if (imageUrl != null ){
                        Picasso.with(MovieInfoActivity.this).load(imageUrl).into(movieImageView);
                    }else{
                        //Picasso.with(MovieInfoActivity.this).load(R.drawable.logov2).into(movieImageView);
                    }

                    /* Atribuir texto aos textviews */
                    movieTitleTextView.setText(title);
                    movieOverviewTextView.setText(overView);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }else {
                // Se não houver Json

            }
            loadingProgressBar.setVisibility(View.GONE);
        }
    }
}
