package com.example.testmixapirecycler.evolution;

import java.util.List;

public class PokeEvo {
    int id;
    int evolutionStage ;
    List<String> evolutionLine;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEvolutionStage() {
        return evolutionStage;
    }

    public void setEvolutionStage(int evolutionStage) {
        this.evolutionStage = evolutionStage;
    }

    public List<String> getEvolutionLine() {
        return evolutionLine;
    }

    public void setEvolutionLine(List<String> evolutionLine) {
        this.evolutionLine = evolutionLine;
    }


}
