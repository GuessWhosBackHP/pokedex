package com.example.testmixapirecycler.activityPokemonDetails;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.testmixapirecycler.MainActivity;
import com.example.testmixapirecycler.R;
import com.example.testmixapirecycler.frenchPokemon.Flavor_text_entries;
import com.example.testmixapirecycler.frenchPokemon.IPokeFrench;
import com.example.testmixapirecycler.frenchPokemon.IPokeFrenchName;
import com.example.testmixapirecycler.frenchPokemon.ListNames;
import com.example.testmixapirecycler.frenchPokemon.RetrofitClientPokeFrench;
import com.example.testmixapirecycler.pokemonChargement.RetrofitClientPokeCharge;
import com.example.testmixapirecycler.pokemon_Details.IPokeDetails;
import com.example.testmixapirecycler.pokemon_Details.Pokemon;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Activity_Pokemon_Details extends AppCompatActivity {
    private ImageView imageView ;
    private TextView textViewTaille ;
    private TextView textViewDesc ;
    private TextView textViewNom ;
    private TextView textViewPoids ;
    private TextView textViewType ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_pokemon);
        //recuperation id pokemon cliqué
        Intent intent = getIntent();
        int id = 0;
        if(intent.hasExtra("id_poke"))
        {
        id=intent.getIntExtra("id_poke",0);
        }

        //Remplissage
        setButtons();
        chargementDescFrench(id);
        chargementDetailsPokemon(id);
        chargementNameFrench(id);
        Glide.with(this).load("https://pokeres.bastionbot.org/images/pokemon/"+id+".png").into(imageView);
        

    }
    public void setButtons()
    {
        imageView = findViewById(R.id.ImageView_Details);
        textViewDesc = findViewById(R.id.textView_Description);
        textViewNom = findViewById(R.id.textView_Nom);
        textViewPoids = findViewById(R.id.textView_Poids);
        textViewTaille = findViewById(R.id.textView_Taille);
        textViewType = findViewById(R.id.textView_Type);



    }
    public double ajoutvirguleValeurAPI (int sansvirgule)
    {
        double res = 0 ;
        res = (double)sansvirgule /10 ;
        return res ;
    }

    public void chargementDetailsPokemon(int id )
    {
        Retrofit retrofit = RetrofitClientPokeCharge.getInstance();
        IPokeDetails iPokeDetails = retrofit.create(IPokeDetails.class);
        Call<Pokemon> call = iPokeDetails.GetPokemon(id);
        call.enqueue(new Callback<Pokemon>() {
            @Override
            public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                if(!response.isSuccessful())
                {
                    Log.d("debug", String.valueOf(response.code()));
                    return;
                }
                Pokemon pokemon = response.body();
                double poids , taille ;
                poids = ajoutvirguleValeurAPI(Integer.parseInt(pokemon.getWeight()));
                taille = ajoutvirguleValeurAPI(Integer.parseInt(pokemon.getHeight()));
                textViewPoids.setText("Poids : "+ poids + " Kg" );
                textViewTaille.setText("Taille : " + taille + " m");
                String content="Types : ";
                for(int i=0; i<pokemon.getTypes().size();i++)
                {
                    if(i==0)
                        content+=pokemon.getTypes().get(i).getType().name;
                    else
                        content+= "-"+pokemon.getTypes().get(i).getType().name  ;
                }
                textViewType.setText(content);

            }

            @Override
            public void onFailure(Call<Pokemon> call, Throwable t) {
                Log.d("debug",t.getMessage());
            }
        });


    }
    public void chargementNameFrench(int id )
    {
        Retrofit retrofit = RetrofitClientPokeFrench.getInstance();
        IPokeFrenchName iPokeFrenchName = retrofit.create(IPokeFrenchName.class);
        Call<ListNames> call = iPokeFrenchName.GetPokemonFrenchName(id);
        call.enqueue(new Callback<ListNames>() {
            @Override
            public void onResponse(Call<ListNames> call, Response<ListNames> response) {
                if(!response.isSuccessful())
                {
                    Log.d("debug", String.valueOf(response.code()));
                    return;
                }
                ListNames listNames = response.body();
                for(int j = 0;j<listNames.getListNames().size();j++)
                {
                    if(listNames.getListNames().get(j).getLanguage().getName().equals("fr"))
                    {
                        textViewNom.setText(listNames.getListNames().get(j).getName());
                        break;
                    }
                }
            }

            @Override
            public void onFailure(Call<ListNames> call, Throwable t) {
                Log.d("debug",t.getMessage());
            }
        });
        
    }
    public void chargementDescFrench(int id )
    {
        Retrofit retrofit = RetrofitClientPokeFrench.getInstance() ;
        IPokeFrench iPokeFrench = retrofit.create(IPokeFrench.class);
        Call<Flavor_text_entries> call= iPokeFrench.GetPokemonFrench(id);
        call.enqueue(new Callback<Flavor_text_entries>() {
            @Override
            public void onResponse(Call<Flavor_text_entries> call, Response<Flavor_text_entries> response) {
                if(!response.isSuccessful())
                {
                    Log.d("debug", String.valueOf(response.code()));
                    return;
                }
                Flavor_text_entries flavor_text_entries = response.body();
                for(int i = 0 ; i < flavor_text_entries.getFlavor_text_entries().size();i++)
                {
                    String tmp = flavor_text_entries.getFlavor_text_entries().get(i).getLanguage().getName() ;
                        if(tmp.equals("fr")) {
                            textViewDesc.setText(flavor_text_entries.getFlavor_text_entries().get(i).getFlavor_text());
                
                             break ; 
                        }
                }
            }



            @Override
            public void onFailure(Call<Flavor_text_entries> call, Throwable t) {
                Log.d("debug",t.getMessage());
            }
        });

    }
}