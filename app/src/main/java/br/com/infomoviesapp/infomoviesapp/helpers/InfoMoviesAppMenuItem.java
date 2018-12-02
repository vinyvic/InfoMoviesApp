package br.com.infomoviesapp.infomoviesapp.helpers;

import android.app.Activity;
import android.content.Context;

import br.com.infomoviesapp.infomoviesapp.R;

public class InfoMoviesAppMenuItem {
    public InfoMoviesAppMenuItem(Context context, android.view.MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_item_genre_list: {
                GoToActivity goToActivity = new GoToActivity(context);
                goToActivity.goToMainActivity();
                break;
            }
            case R.id.nav_item_about_us: {
                GoToActivity goToActivity = new GoToActivity(context);
                goToActivity.goToAboutUsActivity();
                break;
            }
            default: {
                break;
            }
        }
    }
}
