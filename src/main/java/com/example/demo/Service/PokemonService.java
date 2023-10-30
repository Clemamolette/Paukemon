package com.example.demo.Service;

import com.example.demo.Model.Pokemon;
import com.mashape.unirest.http.*;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.*;

@Service @SessionScope
public class PokemonService {

    private String apiURLBW1 = "https://api.pokemontcg.io/v2/cards?select=id,name,hp,types,nationalPokedexNumbers,images,rarity,supertype&q=set.id:bw1";
    private String apiURLBW2 = "https://api.pokemontcg.io/v2/cards?select=id,name,hp,types,nationalPokedexNumbers,images,rarity,supertype&q=set.id:bw2";
    private String apiURLBase1 = "https://api.pokemontcg.io/v2/cards?select=id,name,hp,types,nationalPokedexNumbers,images,rarity,supertype&q=set.id:base1";
    private String apiURLBase2 = "https://api.pokemontcg.io/v2/cards?select=id,name,hp,types,nationalPokedexNumbers,images,rarity,supertype&q=set.id:base2";
    private String apiKEY = "e030d28b-a210-4552-bec5-3b63be6b970b";
    private ArrayList<ArrayList<String>> data;

    protected boolean initialized = false;
    protected ArrayList<JSONObject> communesBWData = new ArrayList<>();
    protected ArrayList<JSONObject> raresBWData = new ArrayList<>();
    protected ArrayList<JSONObject> communesBaseData = new ArrayList<>();
    protected ArrayList<JSONObject> raresBaseData = new ArrayList<>();

    public void initialize() {
        if (!(initialized)) {
            data = getCards();

            JSONObject json;
            // chaque carte est un string, on les transforme en objets JSON

            ArrayList<String> communesBWSTR = data.get(0);
            for (int i = 0; i<communesBWSTR.size(); i++) {
                json = new JSONObject(communesBWSTR.get(i));
                JSONObject image = (JSONObject) json.get("images");
                json.put("images",image.get("large"));
                communesBWData.add(json);
            }

            ArrayList<String> raresBWSTR = data.get(1);
            for (int i = 0; i<raresBWSTR.size(); i++) {
                json = new JSONObject(raresBWSTR.get(i));
                JSONObject image = (JSONObject) json.get("images");
                json.put("images",image.get("large"));
                raresBWData.add(json);
            }

            ArrayList<String> communesBaseSTR = data.get(2);
            for (int i = 0; i<communesBaseSTR.size(); i++) {
                json = new JSONObject(communesBaseSTR.get(i));
                JSONObject image = (JSONObject) json.get("images");
                json.put("images",image.get("large"));
                communesBaseData.add(json);
            }

            ArrayList<String> raresBaseSTR = data.get(3);
            for (int i = 0; i<raresBaseSTR.size(); i++) {
                json = new JSONObject(raresBaseSTR.get(i));
                JSONObject image = (JSONObject) json.get("images");
                json.put("images",image.get("large"));
                raresBaseData.add(json);
            }

            initialized = true;
        }
    }

    public ArrayList<ArrayList<String>> getCards() {
        HttpResponse<JsonNode> jsonResponse;
        {
            ArrayList<ArrayList<String>> data;
            List<List<Pokemon>> pokemonList;
            try {
                // première requête avec une extension de carte
                jsonResponse = Unirest.get(apiURLBW1)
                        .header("X-Api-Key", apiKEY)
                        .asJson();

                // création des tableaux pour contenir les cartes rares et communes
                data = new ArrayList<>();
                ArrayList<String> communesBW = new ArrayList<String>();
                ArrayList<String> raresBW = new ArrayList<String>();

                //récupération des données de la réponse et classement dans les deux tableaux selon la rareté
                JSONArray data_json = (JSONArray) jsonResponse.getBody().getObject().get("data");
                int len = data_json.length();
                dispatchRarity(data_json, communesBW, raresBW, len);


                // Deuxième requête avec une autre extension de cartes
                jsonResponse = Unirest.get(apiURLBW2)
                        .header("X-Api-Key", apiKEY)
                        .asJson();
                data_json = (JSONArray) jsonResponse.getBody().getObject().get("data");
                len = data_json.length();
                dispatchRarity(data_json, communesBW, raresBW, len);


                ArrayList<String> communesBase = new ArrayList<String>();
                ArrayList<String> raresBase = new ArrayList<String>();
                // Troisième requête
                jsonResponse = Unirest.get(apiURLBase1)
                        .header("X-Api-Key", apiKEY)
                        .asJson();
                data_json = (JSONArray) jsonResponse.getBody().getObject().get("data");
                len = data_json.length();
                dispatchRarity(data_json, communesBase, raresBase, len);


                // Quatrième requête
                jsonResponse = Unirest.get(apiURLBase2)
                        .header("X-Api-Key", apiKEY)
                        .asJson();
                data_json = (JSONArray) jsonResponse.getBody().getObject().get("data");
                len = data_json.length();
                dispatchRarity(data_json, communesBase, raresBase, len);


                // on regroupe les quatres listes dans une même autre liste
                data.add(communesBW);
                data.add(raresBW);
                data.add(communesBase);
                data.add(raresBase);
            } catch (UnirestException e) {
                throw new RuntimeException(e);
            }
            return data;
        }
    }

    private void dispatchRarity(JSONArray data_json, ArrayList<String> communes, ArrayList<String> rares, int len) {
        for (int i=0;i<len;i++){
            JSONObject card = (JSONObject) data_json.get(i);
            if (card.get("supertype").toString().contains("mon")) {
                if (card.get("rarity").toString().contains("ommon")) {
                    communes.add(card.toString());
                } else {
                    rares.add(card.toString());
                }
            }
        }
    }
    public ArrayList<JSONObject> getCommunesBW() {
        initialize();
        return communesBWData;
    }

    public ArrayList<JSONObject> getRaresBW() {
        initialize();
        return raresBWData;
    }

    public ArrayList<JSONObject> getCommunesBase() {
        initialize();
        return communesBaseData;
    }

    public ArrayList<JSONObject> getRaresBase() {
        initialize();
        return raresBaseData;
    }
}
