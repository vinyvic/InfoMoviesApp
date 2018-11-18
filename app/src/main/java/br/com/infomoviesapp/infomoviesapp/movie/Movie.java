package br.com.infomoviesapp.infomoviesapp.movie;

public class Movie {
    private String posterPath;
    private int id;
    private String title;

    public Movie(String posterPath, int id, String title) {
        this.posterPath = posterPath;
        this.id = id;
        this.title = title;
    }

    public String getPosterPath() {

        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
