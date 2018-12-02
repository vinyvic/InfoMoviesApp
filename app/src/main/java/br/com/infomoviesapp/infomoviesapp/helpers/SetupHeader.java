package br.com.infomoviesapp.infomoviesapp.helpers;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import br.com.infomoviesapp.infomoviesapp.R;

public class SetupHeader {
    private Activity activity;
    private AppCompatActivity appCompatActivity;
    private Toolbar mainToolBar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private NavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener;
    private String title;

    public SetupHeader(Context context , Toolbar mainToolBar, NavigationView navigationView, DrawerLayout drawerLayout, String title) {

        /* Context */
        this.activity = (Activity) context;
        this.appCompatActivity = (AppCompatActivity) context;
        this.navigationItemSelectedListener = (NavigationView.OnNavigationItemSelectedListener) context;

        /* Itens Laoyout */
        this.mainToolBar = mainToolBar;
        this.navigationView = navigationView;
        this.drawerLayout = drawerLayout;

        /* Title of toolbar */
        this.title = title;

        setupTopbar();
        setupDrawer();
    }

    /* Configurar Toolbar*/
    private void setupTopbar(){
        mainToolBar.setTitle(title);
        appCompatActivity.setSupportActionBar(mainToolBar);
    }

    private void setupDrawer(){
        /* Configurar Menu lateral */
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(activity, drawerLayout, mainToolBar, R.string.open_drawer, R.string.close_drawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(navigationItemSelectedListener);
        navigationView.bringToFront();
    }

}
