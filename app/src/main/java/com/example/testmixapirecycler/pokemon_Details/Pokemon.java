package com.example.testmixapirecycler.pokemon_Details;

import java.util.List;

public class Pokemon {
    private int id;
    private String name;
    // private String img;
    private List<Types> types;
    private String height;
    private String weight;
    //private String desc;

    public Pokemon(int id, String name, List<Types> type, String height, String weight) {
        this.id = id;
        this.name = name;
        //this.img = img; String img,
        this.types = type;
        this.height = height;
        this.weight = weight;
        // this.desc = desc;, String desc
    }

    public int getId() {
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
    public List<Types> getTypes() {
        return types;
    }

    public void setTypes(List<Types> type) {
        this.types = type;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }
}
