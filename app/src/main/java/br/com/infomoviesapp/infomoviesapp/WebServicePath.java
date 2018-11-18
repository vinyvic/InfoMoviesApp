package br.com.infomoviesapp.infomoviesapp;

import android.support.v7.app.AppCompatActivity;

public class WebServicePath extends AppCompatActivity {
    private String urlBase = App.getResourses().getString(R.string.web_service_url);
    private String path;
    private String key = App.getResourses().getString(R.string.api_key);
    private String language = App.getResourses().getString(R.string.lang);
    private String others;

    /* Construtores */
    public WebServicePath(String path, String others) {
        this.path = path;
        this.others = others;
    }

    public WebServicePath(String path) {
        this (path, "");
    }

    public WebServicePath() {
        this ("", "");
    }

    /* Get e Set Defaults*/

    public void setPath(String path) {
        this.path = path;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
    }

    public String getUrlBase() {
        return urlBase;
    }

    public void setUrlBase(String urlBase) {
        this.urlBase = urlBase;
    }

    /* Metodos Personalizados */
    public void setPathGenre() {
        String path = App.getResourses().getString(R.string.path_genres);
        setPath(path);
    }

    public void setPathMovieByGenre() {
        String path = App.getResourses().getString(R.string.path_movie_by_genre);
        setPath(path);
    }

    public void setOthersWithGenres(int id){
        String others = App.getResourses().getString(R.string.with_genres, id);
        setOthers(String.format("%s&%s", this.others, others));
    }

    public String getUrl(){
        String url = String.format("%s%s?api_key=%s&language=%s%s", urlBase, path, key, language, others);
        return url;
    }
}
