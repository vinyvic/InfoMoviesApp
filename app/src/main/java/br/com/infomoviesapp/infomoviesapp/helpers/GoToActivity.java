package br.com.infomoviesapp.infomoviesapp.helpers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;

import br.com.infomoviesapp.infomoviesapp.activitys.AboutUsActivity;
import br.com.infomoviesapp.infomoviesapp.activitys.MainActivity;
import br.com.infomoviesapp.infomoviesapp.activitys.MovieByGenreActivity;

public class GoToActivity {

    private Context mContext;

    public GoToActivity(Context context) {
        this.mContext = context;
    }

    public void goTo(Class pageClass){
        if (mContext.getClass() != pageClass) {
            Intent intent = new Intent(mContext, pageClass);
            mContext.startActivity(intent);
        }
    }

    public void goToMainActivity(){
        goTo(MainActivity.class);
    }

    public void goToAboutUsActivity(){
        goTo(AboutUsActivity.class);
    }
}
