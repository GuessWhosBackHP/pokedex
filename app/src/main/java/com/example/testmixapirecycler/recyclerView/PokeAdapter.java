package com.example.testmixapirecycler.recyclerView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.testmixapirecycler.R;
import com.example.testmixapirecycler.activityPokemonDetails.Activity_Pokemon_Details;

import java.util.ArrayList;

public class PokeAdapter extends RecyclerView.Adapter<PokeAdapter.PokeViewHolder> {
   // private ArrayList<ItemPokemon> mPokeList;
    private ArrayList<Integer>mPokeList ;
    private onItemClickListener mListener ;
    private Context mContext ;



    public interface onItemClickListener
    {
        void onItemClick(int position) ;

    }
    public void setOnItemClickListener(onItemClickListener listener)
    {
        mListener = listener ;
    }
    public static class PokeViewHolder extends RecyclerView.ViewHolder
    {
        public ImageView mImageView ;
        public PokeViewHolder(View itemView, onItemClickListener listener)
        {
            super(itemView);
            mImageView = itemView.findViewById(R.id.ImageViewPokeChargement);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null){
                        int position = getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION)
                        {
                            listener.onItemClick(position);

                        }
                    }
                }
            });

        }
    }
   /* public PokeAdapter(ArrayList<ItemPokemon> pokeList, Context context)
    {
        mPokeList = pokeList ;
        mContext = context;
    }*/
    public PokeAdapter(ArrayList<Integer> pokeList, Context context)
    {
        mPokeList = pokeList ;
        mContext = context;
    }
    @NonNull
    @Override
    public PokeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pokemon,parent,false);
        PokeViewHolder pvh = new PokeViewHolder(v,mListener);
        return pvh;
    }

    @Override
    public void onBindViewHolder(@NonNull PokeViewHolder holder, int position) {
        //ici que l'on passe la valeur au ImageView
        int currentItem =mPokeList.get(position);
      //  ItemPokemon currentItem =mPokeList.get(position);


        Glide.with(mContext).load("https://pokeres.bastionbot.org/images/pokemon/"+(currentItem)+".png").placeholder(R.drawable.ic_pika).into(holder.mImageView);


    }

    @Override
    public int getItemCount() {
        return mPokeList.size();
    }
}
