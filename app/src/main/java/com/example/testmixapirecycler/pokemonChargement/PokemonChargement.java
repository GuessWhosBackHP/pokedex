package com.example.testmixapirecycler.pokemonChargement;

public class PokemonChargement {
    public String name;
    public String url;
    public int id ;



    public PokemonChargement(String name, String url) {
        this.name = name;
        this.url = url;

    }

    public int getId() {
        separationUrlID(url);
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public void separationUrlID(String url)
    {
        int fin = url.length();

        String tmp = url.substring(34,fin-1);
        int res = Integer.parseInt(tmp) ;
        id = res ;
        //return res ;

    }
}
