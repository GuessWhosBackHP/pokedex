package com.example.testmixapirecycler.pokemonChargement;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

public interface IPokeChargement {
    @GET("api/v2/pokemon?offset=0&limit=10")
    Call<Results> GetPokeCharge();
}
