package br.com.infomoviesapp.infomoviesapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

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

public class MainActivity extends AppCompatActivity {

    private RecyclerView genreRecyclerView;
    private GenreAdapter genreAdapter;
    private List <Genre> genres;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /* Default */
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Código Personalizado*/
        genres = new ArrayList<>();

        /* Setup Recycler */
        genreRecyclerView = findViewById(R.id.genreRecyclerView);
        setupRecycler();

        /* Chamada da classe para capturar generos*/
        String pathGenres = getString(R.string.path_genres);
        String key = getString(R.string.api_key);
        String lang = getString(R.string.lang);
        String url = getString(R.string.web_service_url, pathGenres, key, lang);
        new getGenreList().execute(url);
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
        genreAdapter = new GenreAdapter(genres);
        genreRecyclerView.setAdapter(genreAdapter);
    }

    /**
     * Classe privada chamada para obter generos do web service
     */
    private class getGenreList extends AsyncTask <String, Void, String>{

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
            try {
                JSONObject json = new JSONObject(jsonS);
                JSONArray list = json.getJSONArray("genres");

                for (int i = 0; i < list.length(); i++){
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
        }
    }
}
