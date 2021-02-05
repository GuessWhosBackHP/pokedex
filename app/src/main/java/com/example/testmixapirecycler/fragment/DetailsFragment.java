package com.example.testmixapirecycler.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.testmixapirecycler.R;
import com.example.testmixapirecycler.frenchPokemon.Flavor_text_entries;
import com.example.testmixapirecycler.frenchPokemon.IPokeFrench;
import com.example.testmixapirecycler.frenchPokemon.RetrofitClientPokeFrench;
import com.example.testmixapirecycler.pokemonChargement.RetrofitClientPokeCharge;
import com.example.testmixapirecycler.pokemon_Details.IPokeDetails;
import com.example.testmixapirecycler.pokemon_Details.Pokemon;
import com.example.testmixapirecycler.pokemon_Details.RetrofitClientPoke;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DetailsFragment extends Fragment {
    TextView textDesc ;
    TextView textTaille;
    TextView textPoids;
    TextView textTypes;
    int id ;
    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragmentdetails,container,false);
        textDesc = v.findViewById(R.id.textView_Description);
        textTaille = v.findViewById(R.id.textView_Taille);
        textPoids =v.findViewById(R.id.textView_Poids);
        textTypes = v.findViewById(R.id.textView_Type);
        return v ;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundleDesc = getArguments();

            id= bundleDesc.getInt("Desc") ;


        chargementDescFrench(id);
        chargementDetailsPokemon(id);

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
                        textDesc.setText(flavor_text_entries.getFlavor_text_entries().get(i).getFlavor_text());

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
                textPoids.setText("Poids : "+ poids + " Kg" );
                textTaille.setText("Taille : " + taille + " m");
                String content="Types : ";
                for(int i=0; i<pokemon.getTypes().size();i++)
                {
                    if(i==0)
                        content+=pokemon.getTypes().get(i).getType().name;
                    else
                        content+= "-"+pokemon.getTypes().get(i).getType().name  ;
                }
                textTypes.setText(content);

            }

            @Override
            public void onFailure(Call<Pokemon> call, Throwable t) {
                Log.d("debug",t.getMessage());
            }
        });}
    public double ajoutvirguleValeurAPI (int sansvirgule)
    {
        double res = 0 ;
        res = (double)sansvirgule /10 ;
        return res ;
    }

}
