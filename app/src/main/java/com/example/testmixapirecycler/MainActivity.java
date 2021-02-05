package com.example.testmixapirecycler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.example.testmixapirecycler.activityPokemonDetails.Activity_Pokemon_Details;
import com.example.testmixapirecycler.pokemonChargement.IPokeGeneration;
import com.example.testmixapirecycler.pokemonChargement.Pokemon_Species;
import com.example.testmixapirecycler.pokemonChargement.RetrofitClientPokeCharge;
import com.example.testmixapirecycler.recyclerView.PokeAdapter;

import java.util.ArrayList;
import java.util.Collections;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    private int idBidouillage = 0 ;
    private ArrayList<Integer> mPokemonList;
    private RecyclerView mRecyclerView ;
    private RecyclerView.LayoutManager mLayoutManager ;
    private PokeAdapter mPokeAdapter;
    private ImageButton imageButton1 ,imageButton2,imageButton3,imageButton4,imageButton5,imageButton6,imageButton7,imageButton8 ;
    private String gen ="Génération de Pokémon";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("1ère " + gen);
        setContentView(R.layout.activity_main);
        setButton();
        createPokeList();
        chargementPokeList(1);
        buildRecyclerView();

    }
    public void chargementPokeList(int id)
    {   idBidouillage = 2000;
        Retrofit retrofit= RetrofitClientPokeCharge.getInstance();
        IPokeGeneration iPokeGeneration = retrofit.create(IPokeGeneration.class);
        Call<Pokemon_Species> call = iPokeGeneration.GetPokeGen(id);
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
                     int valeurIdActuelle =pokemon_species.getPokemon_species().get(i).getId();
                     if(idBidouillage>valeurIdActuelle)
                         idBidouillage=valeurIdActuelle ;
                   /*if(idPremierPokemon>valeurIdActuelle)
                        idPremierPokemon= valeurIdActuelle ;

                    if(idDernierPokemon<valeurIdActuelle)
                        idDernierPokemon=valeurIdActuelle ;*/
                   // addPokeList(i);
                    addPokeList(pokemon_species.getPokemon_species().get(i).getId());
                }
                    trieListPokemon();

            }

            @Override
            public void onFailure(Call<Pokemon_Species> call, Throwable t) {
            Log.d("debug",t.getMessage());
            }
        });


    }
    public void setButton()
    {
        imageButton1 = findViewById(R.id.imageButton1);
        imageButton2 = findViewById(R.id.imageButton2);
        imageButton3 = findViewById(R.id.imageButton3);
        imageButton4 = findViewById(R.id.imageButton4);
        imageButton5 = findViewById(R.id.imageButton5);
        imageButton6 = findViewById(R.id.imageButton6);
        imageButton7 = findViewById(R.id.imageButton7);
        imageButton8 = findViewById(R.id.imageButton8);
        actionClickButton();

    }
    public void actionClickButton()
    {
        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createPokeList();
                chargementPokeList(1);
                buildRecyclerView();
                setTitle("1ère "+gen);

            }
        });
        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createPokeList();
                chargementPokeList(2);
                buildRecyclerView();
                setTitle("2ème "+gen);

            }
        });
        imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createPokeList();
                chargementPokeList(3);
                buildRecyclerView();
                setTitle("3ème "+gen);

            }
        });
        imageButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createPokeList();
                chargementPokeList(4);
                buildRecyclerView();
                setTitle("4ème "+gen);

            }
        });
        imageButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createPokeList();
                chargementPokeList(5);
                buildRecyclerView();
                setTitle("5ème "+gen);

            }
        });
        imageButton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createPokeList();
                chargementPokeList(6);
                buildRecyclerView();
                setTitle("6ème "+gen);

            }
        });
        imageButton7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createPokeList();
                chargementPokeList(7);
                buildRecyclerView();
                setTitle("7ème "+gen);

            }
        });
        imageButton8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createPokeList();
                chargementPokeList(8);
                buildRecyclerView();
                setTitle("8ème "+gen);

            }
        });


    }
    public void createPokeList()
    {
        mPokemonList = new ArrayList<>();


    }
    public void addPokeList(int pos){
        mPokemonList.add(pos);

        mRecyclerView.getAdapter().notifyDataSetChanged();
    }
    public void trieListPokemon()
    {
        Collections.sort(mPokemonList);
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

                intent.putExtra("id_poke",idBidouillage+position);
                startActivity(intent);

            }
        });

    }
}