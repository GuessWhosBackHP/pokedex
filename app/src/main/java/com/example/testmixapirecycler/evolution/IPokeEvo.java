package com.example.testmixapirecycler.evolution;

import com.example.testmixapirecycler.frenchPokemon.Flavor_text_entries;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IPokeEvo {
    @GET("v1/pokemon/{id}")
    Call<List<Family>> GetPokemonFrench(@Path("id") int id );
    //https://pokeapi.glitch.me/v1/pokemon/1
}
