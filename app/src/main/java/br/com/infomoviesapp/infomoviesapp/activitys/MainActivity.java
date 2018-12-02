package br.com.infomoviesapp.infomoviesapp.activitys;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import br.com.infomoviesapp.infomoviesapp.helpers.Alerts;
import br.com.infomoviesapp.infomoviesapp.helpers.AppStatus;
import br.com.infomoviesapp.infomoviesapp.R;
import br.com.infomoviesapp.infomoviesapp.helpers.GoToActivity;
import br.com.infomoviesapp.infomoviesapp.helpers.InfoMoviesAppMenuItem;
import br.com.infomoviesapp.infomoviesapp.helpers.SetupHeader;
import br.com.infomoviesapp.infomoviesapp.helpers.WebServicePath;
import br.com.infomoviesapp.infomoviesapp.genre.Genre;
import br.com.infomoviesapp.infomoviesapp.genre.GenreAdapter;

/** Classe chamada ao iniciar o aplicativo que é responsável por mostrar a lista de gêneros.
 */
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView genreRecyclerView;
    private GenreAdapter genreAdapter;
    private List <Genre> genres;                // Lista de Generos
    private ProgressBar loadingProgressBar;
    private SwipeRefreshLayout swipeRefreshLayout;
    private String urlGenre;
    private DrawerLayout drawerLayout;
    private Toolbar mainToolBar;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /* Padrão do onCreate*/
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Mostrar loading */
        loadingProgressBar = findViewById(R.id.loadingProgressBar);
        loadingProgressBar.setVisibility(View.VISIBLE);

        /* Menu e Toolbar */
        mainToolBar = findViewById(R.id.mainToolBar);
        navigationView = findViewById(R.id.navView);
        drawerLayout = findViewById(R.id.drawerLayout);
        String title = getString(R.string.choose_a_genre);
        new SetupHeader(this, mainToolBar, navigationView, drawerLayout, title);

        /* Personalizado */
        genres = new ArrayList<>();

        /* Swipe */
        swipeRefreshLayout = findViewById(R.id.genresSwipeRefreshLayout);

        /* Inicializar a RecyclerView */
        genreRecyclerView = findViewById(R.id.genreRecyclerView);
        setupRecycler();

        /* Montar Url para solicitar os generos no onResume*/
        WebServicePath webServicePath = new WebServicePath();
        webServicePath.setPathGenre();
        urlGenre = webServicePath.getUrl();
        Log.d("DEBUG/URL_GENRE", urlGenre);

    }


    @Override
    protected void onResume() {
        super.onResume();

        getGenres();
    }

    @Override
    protected void onStop() {
        super.onStop();

        clearGenres();
    }

    /**
     * Método privado chamado para inicializar o Recycler
     */
    private void setupRecycler() {

        // Configurando o gerenciador de layout para ser uma lista.
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        genreRecyclerView.setLayoutManager(layoutManager);

        // Adiciona o adapter que irá anexar os objetos à lista.
        // Está sendo criado com lista vazia, pois será preenchida posteriormente.
        genreAdapter = new GenreAdapter(MainActivity.this, genres);
        genreRecyclerView.setAdapter(genreAdapter);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshContent();
            }

            private void refreshContent() {
                getGenres();
                swipeRefreshLayout.setRefreshing(false);

            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        InfoMoviesAppMenuItem infoMoviesAppMenuItem = new InfoMoviesAppMenuItem(this, item);
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


    /**
     * Classe privada chamada para obter generos do web service
     *  @version 1.0
     *  @since 1.0
     */
    private class GetGenreList extends AsyncTask <String, Void, String>{

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
                try (BufferedReader reader = new BufferedReader(inputStreamReader)){
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
                    JSONArray list = json.getJSONArray("genres");

                    for (int i = 0; i < list.length(); i++) {
                        JSONObject genre = list.getJSONObject(i);
                        int id = genre.getInt("id");
                        String name = genre.getString("name");

                        Genre novo = new Genre(id, name);
                        genres.add(novo);
                        genreAdapter.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }else{
                // Se não houver Json

            }
            loadingProgressBar.setVisibility(View.GONE);
        }
    }

    private void clearGenres(){
        genres.clear();
        genreAdapter.notifyDataSetChanged();
    }

    private void getGenres(){
        clearGenres();
        if (AppStatus.getInstance(MainActivity.this).isOnline()){
            new GetGenreList().execute(urlGenre);
        }else{
            Alerts alerts = new Alerts(MainActivity.this);
            alerts.noConnection();
        }
    }
}
