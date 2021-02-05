package com.example.testmixapirecycler.activityPokemonDetails;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.testmixapirecycler.MainActivity;
import com.example.testmixapirecycler.R;
import com.example.testmixapirecycler.fragment.DetailsFragment;
import com.example.testmixapirecycler.fragment.EvoFragment;
import com.example.testmixapirecycler.frenchPokemon.Flavor_text_entries;
import com.example.testmixapirecycler.frenchPokemon.IPokeFrench;
import com.example.testmixapirecycler.frenchPokemon.IPokeFrenchName;
import com.example.testmixapirecycler.frenchPokemon.ListNames;
import com.example.testmixapirecycler.frenchPokemon.RetrofitClientPokeFrench;
import com.example.testmixapirecycler.pokemonChargement.RetrofitClientPokeCharge;
import com.example.testmixapirecycler.pokemon_Details.IPokeDetails;
import com.example.testmixapirecycler.pokemon_Details.Pokemon;
import com.example.testmixapirecycler.pokemon_Details.RetrofitClientPoke;
import com.example.testmixapirecycler.pokemon_Details.Type;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Activity_Pokemon_Details extends AppCompatActivity {
    private ImageView imageView ;
    private TextView textViewNom ;
    private LinearLayout linearLayout;
    private int id ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__pokemon__details);

        //recuperation id pokemon cliqué
        Intent intent = getIntent();
        setButtons();
        id = 0;
        if(intent.hasExtra("id_poke"))
        {
        id=intent.getIntExtra("id_poke",0);
        }
        setTitle("Pokemon n° "+id);


        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);




        ChangeNavBavColor(id);



        //--------------Bundle Details par defaut

        DetailsFragment DetailsFrag = new DetailsFragment();
        Bundle bundleDesc = new Bundle();
        bundleDesc.putInt("Desc",id);
        DetailsFrag.setArguments(bundleDesc);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction t=manager.beginTransaction();
       t.replace(R.id.fragment_container, DetailsFrag).commit();



       //Remplissage elements
        chargementNameFrench(id);

        Glide.with(this).load("https://pokeres.bastionbot.org/images/pokemon/"+id+".png").into(imageView);


    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            switch (item.getItemId())
            {
                case R.id.nav_details:
                    selectedFragment = new DetailsFragment();
                    break;
                case R.id.nav_evo:
                    selectedFragment = new EvoFragment();
                    break;

            }

            Bundle bundleDesc = new Bundle();
            bundleDesc.putInt("Desc",id);
            selectedFragment.setArguments(bundleDesc);
            FragmentManager manager = getSupportFragmentManager();

            FragmentTransaction t=manager.beginTransaction();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();

            return true;
        }
    };
    public void ChangeNavBavColor(int id)
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
                String res="#797C47";
                Pokemon pokemon = response.body();
                String TypeColor = pokemon.getTypes().get(0).getType().getName() ;
        linearLayout = findViewById(R.id.rectanglenul);
                switch (TypeColor)
                {
                    case "normal" :
                        res = "#797C47";
                        linearLayout.setBackgroundResource(R.drawable.rect_normal);
                        break;
                    case "grass" :
                        res = "#1CD41C";
                        linearLayout.setBackgroundResource(R.drawable.rect_grass);
                        break;
                    case "water" :
                        res = "#073AB1";
                        linearLayout.setBackgroundResource(R.drawable.rect_water);
                        break;
                    case "fire" :
                        res = "#D74107";
                        linearLayout.setBackgroundResource(R.drawable.rect_fire);
                        break;
                    case "fighting" :
                        res = "#731B1B";
                        linearLayout.setBackgroundResource(R.drawable.rect_fighting);
                        break;
                    case "electric" :
                        res = "#E9D307";
                        linearLayout.setBackgroundResource(R.drawable.rect_electric);
                        break;
                    case "ice" :
                        res = "#4CE8E5";
                        linearLayout.setBackgroundResource(R.drawable.rect_ice);
                        break;
                    case "poison" :
                        res = "#79048F";
                        linearLayout.setBackgroundResource(R.drawable.rect_poison);
                        break;
                    case "ground" :
                        res = "#B0BA28";
                        linearLayout.setBackgroundResource(R.drawable.rect_ground);
                        break;
                    case "flying" :
                        res = "#87B8DE";
                        linearLayout.setBackgroundResource(R.drawable.rect_flying);
                        break;
                    case "psychic" :
                        res = "#E644EB";
                        linearLayout.setBackgroundResource(R.drawable.rect_psychic);
                        break;
                    case "bug" :
                        res = "#77C63A";
                        linearLayout.setBackgroundResource(R.drawable.rect_bug);
                        break;
                    case "rock" :
                        res = "#92B042";
                        linearLayout.setBackgroundResource(R.drawable.rect_rock);
                        break;
                    case "ghost" :
                        res = "#705698";
                        linearLayout.setBackgroundResource(R.drawable.rect_ghost);
                        break;
                    case "dark" :
                        res = "#37353C";
                        linearLayout.setBackgroundResource(R.drawable.rect_dark);
                        break;
                    case "dragon" :
                        res = "#2B0E75";
                        linearLayout.setBackgroundResource(R.drawable.rect_dragon);
                        break;
                    case "steel" :
                        res = "#79787D";
                        linearLayout.setBackgroundResource(R.drawable.rect_steel);
                        break;
                    case "fairy" :
                        res = "#CCB8C8";
                        linearLayout.setBackgroundResource(R.drawable.rect_fairy);
                        break;


                }
                BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
                bottomNav.setBackgroundColor(Color.parseColor(res));
            }

            @Override
            public void onFailure(Call<Pokemon> call, Throwable t) {
                Log.d("debug",t.getMessage());
            }
        });



    }
    public void setButtons()
    {
        imageView = findViewById(R.id.ImageView_Details);
        textViewNom = findViewById(R.id.textView_Nom);
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

}