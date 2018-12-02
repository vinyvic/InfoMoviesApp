package br.com.infomoviesapp.infomoviesapp.activitys;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import br.com.infomoviesapp.infomoviesapp.R;
import br.com.infomoviesapp.infomoviesapp.helpers.GoToActivity;
import br.com.infomoviesapp.infomoviesapp.helpers.InfoMoviesAppMenuItem;
import br.com.infomoviesapp.infomoviesapp.helpers.SetupHeader;

/** Classe que é responsável por mostrar informações sobre os desenvoolvedores do aplicativo
 */
public class AboutUsActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener  {

    private DrawerLayout drawerLayout;
    private Toolbar mainToolBar;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        mainToolBar = findViewById(R.id.mainToolBar);
        navigationView = findViewById(R.id.navView);
        drawerLayout = findViewById(R.id.drawerLayout);
        String title = getString(R.string.about_us);
        new SetupHeader(this, mainToolBar, navigationView, drawerLayout, title);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        InfoMoviesAppMenuItem infoMoviesAppMenuItem = new InfoMoviesAppMenuItem(this, item);
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
