package com.example.testmixapirecycler.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.testmixapirecycler.R;
import com.example.testmixapirecycler.evolution.Family;
import com.example.testmixapirecycler.evolution.IPokeEvo;
import com.example.testmixapirecycler.evolution.RetrofitClientPokeEvo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class EvoFragment extends Fragment {
    ImageView imageViewEvo1;
    ImageView imageViewEvo2;
    int id ;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      View v = inflater.inflate(R.layout.fragmentevo,container,false);
      imageViewEvo1= v.findViewById(R.id.ImageView_Details2);
      imageViewEvo2 = v.findViewById(R.id.ImageView_Details3);


      return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundleId = getArguments();
        id = bundleId.getInt("Desc");
        chargementImageEvo(id,this);
     //  Glide.with(this).load("https://pokeres.bastionbot.org/images/pokemon/"+id+".png").into(imageViewEvo2);
    }

    public void chargementImageEvo(int id, Fragment mContext)
    {
        Retrofit retrofit = RetrofitClientPokeEvo.getInstance();
        IPokeEvo iPokeEvo = retrofit.create(IPokeEvo.class);
        Call<List<Family>> call = iPokeEvo.GetPokemonFrench(id);
        call.enqueue(new Callback<List<Family>>() {
            @Override
            public void onResponse(Call<List<Family>> call, Response<List<Family>> response) {
                if(!response.isSuccessful())
                {
                    Log.d("debug", String.valueOf(response.code()));
                    return;
                }
                List<Family> family = response.body();
                int tailleEvoLine =family.get(0).getFamily().getEvolutionLine().size();
                int evoStage = family.get(0).getFamily().getEvolutionStage() ;
                switch(tailleEvoLine)    //getFamily().getEvolutionLine().size())
                {
                    case 1 :
                        break;
                    case 2 :
                        if(evoStage==1)
                        {
                            Glide.with(mContext).load("https://pokeres.bastionbot.org/images/pokemon/"+(id+1)+".png").into(imageViewEvo1);
                        }
                        else
                            {
                                Glide.with(mContext).load("https://pokeres.bastionbot.org/images/pokemon/"+(id-1)+".png").into(imageViewEvo1);
                            }
                        break;
                    case 3:
                        if(evoStage==1)
                        {
                            Glide.with(mContext).load("https://pokeres.bastionbot.org/images/pokemon/"+(id+1)+".png").into(imageViewEvo2);
                            Glide.with(mContext).load("https://pokeres.bastionbot.org/images/pokemon/"+(id+2)+".png").into(imageViewEvo1);
                        }
                        else if(evoStage==2)
                        {
                            Glide.with(mContext).load("https://pokeres.bastionbot.org/images/pokemon/"+(id-1)+".png").into(imageViewEvo2);
                            Glide.with(mContext).load("https://pokeres.bastionbot.org/images/pokemon/"+(id+1)+".png").into(imageViewEvo1);
                        }
                        else
                        {
                            Glide.with(mContext).load("https://pokeres.bastionbot.org/images/pokemon/"+(id-1)+".png").into(imageViewEvo1);
                            Glide.with(mContext).load("https://pokeres.bastionbot.org/images/pokemon/"+(id-2)+".png").into(imageViewEvo2);
                        }



                }





            }

            @Override
            public void onFailure(Call<List<Family>> call, Throwable t) {
                Log.d("debug",t.getMessage());
            }
        });



    }
}
