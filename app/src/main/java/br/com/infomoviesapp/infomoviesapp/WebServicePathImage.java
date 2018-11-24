package br.com.infomoviesapp.infomoviesapp;

/**
 * Classe responsável por montar URL's com os caminhos de imagens do webservice
 *  @version 1.0
 *  @since 1.0
 */
public class WebServicePathImage {
    private String urlBase = App.getResourses().getString(R.string.web_service_url_images);
    private String widthPath = "w500";
    private String image;

    public WebServicePathImage(String image) {
        this.image = image;
    }

    public String getUrlBase() {
        return urlBase;
    }

    public void setUrlBase(String urlBase) {
        this.urlBase = urlBase;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUrl(){
        return String.format("%s%s%s", urlBase, widthPath, image);
    }
}
