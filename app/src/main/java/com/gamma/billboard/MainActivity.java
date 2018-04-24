package com.gamma.billboard;

import android.app.Fragment;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements FragmentAll.onMovieSetAsFavoriteListener{

    private TabLayout main_tab;
    private ViewPager main_viewpager;
    private ViewPagerAdapter adapter;

    //Lista de películas a mostrarse, que será compartida por
    //los dos fragments
    ArrayList<Movie> movies;


    @Override
    public void onMovieSetAsFavorite(int index, boolean fav) {
        //Se modifica si una pelicula ha sido marcada, desde el fragment
        movies.get(index).setFavorite(fav);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        prepareMovies();
        prepareTabs();

    }

    public void prepareTabs(){
        main_tab = (TabLayout) findViewById(R.id.main_tablayout);
        main_viewpager = (ViewPager) findViewById(R.id.main_viewpager);
        adapter = new ViewPagerAdapter(getSupportFragmentManager());

        //Eliminado la sombra del ActionBar para Android 5+
        //Para versiones anteriores, en el archivo styles agregar
        //<item name="android:windowContentOverlay">@null</item>
        getSupportActionBar().setElevation(0);

        //Se comparte con los fragment la lista de películas
        Bundle args = new Bundle();
        args.putParcelableArrayList("LIST",movies);


        //TODO: Filtrar peliculas por estrellas para el segunto fragment
        FragmentAll f = new FragmentAll();
        f.setArguments(args);

        //Agregando los fragmentos deseados
        adapter.addFragment(f, "");
        adapter.addFragment(new FragmentFav(),"");

        //Se vincula el TabLayout con el ViewPager
        main_viewpager.setAdapter(adapter);
        main_tab.setupWithViewPager(main_viewpager);

        main_tab.getTabAt(0).setIcon(R.drawable.ic_movie);
        main_tab.getTabAt(1).setIcon(R.drawable.ic_star);

        //Quitando la sombra del ActionBar
    }


    public void prepareMovies(){
        movies = new ArrayList<>();
        String TAG = "MSG";
        movies.add(new Movie("Avengers: Infinity War","2:30",R.drawable.img1,"Lastest Marvel MCU Movie", false));
        movies.add(new Movie("Jurassic World : Fallen Kingdom","2:00",R.drawable.img2,"Lastest Jurassic World Movie", false));
    }
}
