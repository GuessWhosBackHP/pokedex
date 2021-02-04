package com.example.testmixapirecycler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.testmixapirecycler.activityPokemonDetails.Activity_Pokemon_Details;
import com.example.testmixapirecycler.pokemonChargement.IPokeGeneration;
import com.example.testmixapirecycler.pokemonChargement.Pokemon_Species;
import com.example.testmixapirecycler.pokemonChargement.RetrofitClientPokeCharge;
import com.example.testmixapirecycler.recyclerView.PokeAdapter;
import com.example.testmixapirecycler.recyclerView.ItemPokemon;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    private ArrayList<ItemPokemon> mPokemonList ;
    private RecyclerView mRecyclerView ;
    private RecyclerView.LayoutManager mLayoutManager ;
    private PokeAdapter mPokeAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createPokeList();
        chargementPokeList();
        buildRecyclerView();

    }
    public void chargementPokeList()
    {
        Retrofit retrofit= RetrofitClientPokeCharge.getInstance();
        IPokeGeneration iPokeGeneration = retrofit.create(IPokeGeneration.class);
        Call<Pokemon_Species> call = iPokeGeneration.GetPokeGen(1);
        call.enqueue(new Callback<Pokemon_Species>() {
            @Override
            public void onResponse(Call<Pokemon_Species> call, Response<Pokemon_Species> response) {
                if(!response.isSuccessful())
                {
                    Log.d("debug", String.valueOf(response.code()));
                    return;
                }
                Pokemon_Species pokemon_species = response.body();
                for(int i=0 ; i<pokemon_species.getPokemon_species().size();i++)
                {
                    addPokeList(i);
                }

            }

            @Override
            public void onFailure(Call<Pokemon_Species> call, Throwable t) {
            Log.d("debug",t.getMessage());
            }
        });


    }
    public void createPokeList()
    {
        mPokemonList = new ArrayList<>();


    }
    public void addPokeList(int pos){

        mPokemonList.add(new ItemPokemon(pos));
        mRecyclerView.getAdapter().notifyDataSetChanged();
    }
    public void buildRecyclerView()
    {
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new GridLayoutManager(this,3);
        mPokeAdapter = new PokeAdapter(mPokemonList,this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mPokeAdapter);

        mPokeAdapter.setOnItemClickListener(new PokeAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent( MainActivity.this,Activity_Pokemon_Details.class);
                intent.putExtra("id_poke",position+1);
                startActivity(intent);
            }
        });

    }
}