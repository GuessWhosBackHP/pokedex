package com.example.testmixapirecycler.frenchPokemon;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IPokeFrenchName {
    @GET("api/v2/pokemon-species/{id}")
    Call<ListNames> GetPokemonFrenchName(@Path("id") int id );
}
