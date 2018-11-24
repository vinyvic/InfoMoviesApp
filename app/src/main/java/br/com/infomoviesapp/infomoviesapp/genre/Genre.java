package br.com.infomoviesapp.infomoviesapp.genre;

/** Classe que representa um gênero de filme.
 * @version 1.0
 * @since 1.0
 */
public class Genre {
    private int id;
    private String name;

    public Genre(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
