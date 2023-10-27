package com.example.demo.Singleton;

import com.example.demo.Service.PokemonService;
import org.json.JSONObject;

import java.util.*;

public class CartesSingleton {
    private static CartesSingleton instance;
    private final ArrayList<JSONObject> communesBWData;
    private final ArrayList<JSONObject> raresBWData;
    private final ArrayList<JSONObject> communesBaseData;
    private final ArrayList<JSONObject> raresBaseData;

    private CartesSingleton() {

        PokemonService pokemonService = new PokemonService();
        ArrayList<ArrayList<String>> pokemonCardsSTR = pokemonService.getCards();

        communesBWData = new ArrayList<>();
        raresBWData = new ArrayList<>();
        communesBaseData = new ArrayList<>();
        raresBaseData = new ArrayList<>();

        JSONObject json;
        // chaque carte est un string, on les transorme en objets JSON

        ArrayList<String> communesBWSTR = pokemonCardsSTR.get(0);
        for (int i = 0; i<communesBWSTR.size(); i++) {
            json = new JSONObject(communesBWSTR.get(i));
            //on ne garde que l'image au format large
            JSONObject image = (JSONObject) json.get("images");
            json.put("images",image.get("large"));
            communesBWData.add(json);
        }

        ArrayList<String> raresBWSTR = pokemonCardsSTR.get(1);
        for (int i = 0; i<raresBWSTR.size(); i++) {
            json = new JSONObject(raresBWSTR.get(i));
            JSONObject image = (JSONObject) json.get("images");
            json.put("images",image.get("large"));
            raresBWData.add(json);
        }

        ArrayList<String> communesBaseSTR = pokemonCardsSTR.get(2);
        for (int i = 0; i<communesBaseSTR.size(); i++) {
            json = new JSONObject(communesBaseSTR.get(i));
            //on ne garde que l'image au format large
            JSONObject image = (JSONObject) json.get("images");
            json.put("images",image.get("large"));
            communesBaseData.add(json);
        }

        ArrayList<String> raresBaseSTR = pokemonCardsSTR.get(3);
        for (int i = 0; i<raresBaseSTR.size(); i++) {
            json = new JSONObject(raresBaseSTR.get(i));
            JSONObject image = (JSONObject) json.get("images");
            json.put("images",image.get("large"));
            raresBaseData.add(json);
        }
    }

    public static CartesSingleton getInstance() {
        if (instance == null) {
            instance = new CartesSingleton();
        }
        return instance;
    }

    public ArrayList<JSONObject> getCommunesBW() {
        return communesBWData;
    }

    public ArrayList<JSONObject> getRaresBW() {
        return raresBWData;
    }

    public ArrayList<JSONObject> getCommunesBase() {
        return communesBaseData;
    }

    public ArrayList<JSONObject> getRaresBase() {
        return raresBaseData;
    }


}

