package com.example.testmixapirecycler.frenchPokemon;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IPokeFrench {
    @GET("api/v2/pokemon-species/{id}")
    Call<Flavor_text_entries> GetPokemonFrench(@Path("id") int id );
}
