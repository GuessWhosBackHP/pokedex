package com.example.testmixapirecycler.pokemonChargement;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IPokeGeneration {
    @GET("api/v2/generation/{id}")
    Call<Pokemon_Species> GetPokeGen(@Path("id")int id);
}
