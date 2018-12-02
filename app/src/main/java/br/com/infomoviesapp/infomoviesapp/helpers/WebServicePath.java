package br.com.infomoviesapp.infomoviesapp.helpers;

import java.util.Locale;

import br.com.infomoviesapp.infomoviesapp.R;

/**
 * Classe responsável por montar URL's com os caminhos do webservice
 *  @version 1.0
 *  @since 1.0
 */
public class WebServicePath{
    private String urlBase = App.getResourses().getString(R.string.web_service_url);

    private String path;
    private String key = App.getResourses().getString(R.string.api_key);
    private String language = App.getResourses().getString(R.string.lang);
    private String others;
    private String page;

    /* Construtores */
    public WebServicePath(String path, String others, String page) {
        this.path = path;
        this.others = others;
        this.page = page;
    }

    public WebServicePath(String path) {
        this (path, "", "");
    }

    public WebServicePath() {
        this ("", "", "");
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

    public String getPage() {
        return page;
    }

    public void setPage(int page) {
        String page_url = App.getResourses().getString(R.string.page, page);
        this.page = String.format("&%s", page_url);
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

    public void setPathMovieInfo(int id){
        String path = String.format(Locale.getDefault(), "%s/%d", App.getResourses().getString(R.string.path_movie_info), id);
        setPath(path);
    }

    public void addOthers(String parameter){
        setOthers(String.format("%s&%s", this.others, parameter));
    }

    public String getUrl(){
        String url = String.format(Locale.getDefault(),"%s%s?api_key=%s&language=%s%s%s", urlBase, path, key, language, page, others);
        return url;
    }
}
