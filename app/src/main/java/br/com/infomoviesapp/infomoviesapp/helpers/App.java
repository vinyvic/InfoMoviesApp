package br.com.infomoviesapp.infomoviesapp.helpers;

import android.app.Application;
import android.content.res.Resources;

/** Classe PUBLICA responsável por facilitar a busca de recursos na aplicação em nível global
 *  @version 1.0
 *  @since 1.0
 */
public class App extends Application {
    private static App mInstance;
    private static Resources res;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        res = getResources();
    }

    public static App getInstance() {
        return mInstance;
    }

    public static Resources getResourses() {
        return res;
    }

}