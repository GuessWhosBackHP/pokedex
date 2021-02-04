package com.example.testmixapirecycler.pokemon_Details;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IPokeDetails {
    @GET("api/v2/pokemon/{id}")
    Call<Pokemon> GetPokemon(@Path("id") int id

    );
}
