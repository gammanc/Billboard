package com.gamma.billboard;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by emers on 23/4/2018.
 */

public class FragmentFav extends Fragment implements MovieAdapter.AdapterInterface{

    View v;
    RecyclerView rv;
    ArrayList<Movie> movies;
    MovieAdapter adapter;
    LinearLayoutManager lm;
    onMovieSetAsFavoriteListener mListener;
    Bundle args;

    boolean filter;

    public FragmentFav(){
    }

    //Se añade una interfaz para comunicarse con la lista
    //de películas que está en la actividad
    public interface onMovieSetAsFavoriteListener{
        void onMovieSetAsFavorite(int index, boolean fav);
    }

    //Se confirma que la actividad padre implemente el método
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            mListener = (onMovieSetAsFavoriteListener) context;
        }catch(ClassCastException e){
            throw new ClassCastException(context.toString() + "debe implementar onMovieSetAsFavoriteListener");
        }
    }


    @Override
    public void buttonPressed(int index, boolean active) {
        //Se notifica a la actividad que se ha marcado/desmarcado una pelicula
        //desde el adapter
        mListener.onMovieSetAsFavorite(index, active);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.all_fragment, container, false);

        args = getArguments();
        movies = args.getParcelableArrayList("LIST");
        filter = args.getBoolean("FILTER");

        rv = v.findViewById(R.id.main_recycler1);
        adapter = new MovieAdapter(getContext(),movies, this, filter);
        lm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(lm);
        rv.setAdapter(adapter);
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //prepareMovies();
    }

    public void filterFavorites(){
        Iterator<Movie> it = movies.iterator();
        Movie m;
        for( int i = 0 ; i < movies.size() ; i++ ){
            if (movies.get(i).isFavorite()){
                adapter.notifyItemRemoved(i);
            }
        }
    }


}
