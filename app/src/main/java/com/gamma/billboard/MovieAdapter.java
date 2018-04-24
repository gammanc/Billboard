package com.gamma.billboard;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;

/**
 * Created by UCA on 23/04/2018.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder>{

    private Context mContext;
    private ArrayList<Movie> movies;

    //Se implementa una interfaz para informar al Fragment si se ha
    //marcado o desmarcado alguna película
    AdapterInterface buttonListener;
    public interface AdapterInterface{
        public void buttonPressed(int index, boolean active);

    }

    public MovieAdapter(Context mContext, ArrayList<Movie> movies, AdapterInterface buttonListener){
        this.mContext = mContext;
        this.movies = movies;
        this.buttonListener = buttonListener;
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder{
        CardView card;
        TextView name;
        ImageView img;

        ToggleButton btn;
        public MovieViewHolder(View itemView){
            super(itemView);
            card = itemView.findViewById(R.id.maincard);
            name = itemView.findViewById(R.id.title);
            img = itemView.findViewById(R.id.img);
            btn = itemView.findViewById(R.id.btn_fav);
        }

    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_cardview,parent,false);
        return (new MovieViewHolder(v));
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, final int position) {
        holder.name.setText(movies.get(position).getName());
        holder.img.setImageResource(movies.get(position).getImg());
        holder.btn.setChecked(movies.get(position).isFavorite());
        holder.btn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                //Se notfica al Fragment padre que una película ha sido
                //marcada o desmarcada
                buttonListener.buttonPressed(position, isChecked);

                if (isChecked) {
                    Toast.makeText(buttonView.getContext(),"Añadido a favoritos",Toast.LENGTH_LONG).show();
                }
                else Toast.makeText(buttonView.getContext(),"Eliminado de favoritos",Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }
}
