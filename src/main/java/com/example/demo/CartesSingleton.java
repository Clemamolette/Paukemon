package com.example.demo;

import org.json.JSONObject;

import java.util.*;

public class CartesSingleton {
    private static CartesSingleton instance;
    private final ArrayList<JSONObject> communesData;
    private final ArrayList<JSONObject> raresData;

    private CartesSingleton() {

        PokemonService pokemonService = new PokemonService();
        ArrayList<ArrayList<String>> pokemonCardsSTR = pokemonService.getCards();

        communesData = new ArrayList<>();
        raresData = new ArrayList<>();

        JSONObject json;
        // chaque carte est un string, on les transorme en objets JSON
        ArrayList<String> communesSTR = pokemonCardsSTR.get(0);
        for (int i = 0; i<communesSTR.size(); i++) {
            json = new JSONObject(communesSTR.get(i));
            //on ne garde que l'image au format large
            JSONObject image = (JSONObject) json.get("images");
            json.put("images",image.get("large"));
            communesData.add(json);
        }
        ArrayList<String> raresSTR = pokemonCardsSTR.get(1);
        for (int i = 0; i<raresSTR.size(); i++) {
            json = new JSONObject(raresSTR.get(i));
            JSONObject image = (JSONObject) json.get("images");
            json.put("images",image.get("large"));
            raresData.add(json);
        }
    }

    public static CartesSingleton getInstance() {
        if (instance == null) {
            instance = new CartesSingleton();
        }
        return instance;
    }

    public ArrayList<JSONObject> getCommunes() {
        return communesData;
    }

    public ArrayList<JSONObject> getRares() {
        return raresData;
    }
}

