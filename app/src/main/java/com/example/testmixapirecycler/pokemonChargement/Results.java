package com.example.testmixapirecycler.pokemonChargement;

import java.util.List;

public class Results {
    List<PokemonChargement> results ;

    public List<PokemonChargement> getPokemonChargementList() {
        return results;
    }

    public void setPokemonChargementList(List<PokemonChargement> pokemonChargementList) {
        this.results = pokemonChargementList;
    }
}
