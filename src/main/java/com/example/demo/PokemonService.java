package com.example.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.mashape.unirest.http.*;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PokemonService {

    private String apiURL1 = "https://api.pokemontcg.io/v2/cards?select=id,name,hp,types,nationalPokedexNumbers,images,rarity,supertype&q=set.id:bw1";
    private String apiURL2 = "https://api.pokemontcg.io/v2/cards?select=id,name,hp,types,nationalPokedexNumbers,images,rarity,supertype&q=set.id:bw2";
    private String apiKEY = "";

    public ArrayList<ArrayList<String>> getCards() {
        HttpResponse<JsonNode> jsonResponse;
        {
            ArrayList<ArrayList<String>> data;
            List<List<Pokemon>> pokemonList;
            try {
                // première requête avec une extension de carte
                jsonResponse = Unirest.get(apiURL1)
                        .header("X-Api-Key", apiKEY)
                        .asJson();

                // création des tableaux pour contenir les cartes rares et communes
                data = new ArrayList<>();
                ArrayList<String> communes = new ArrayList<String>();
                ArrayList<String> rares = new ArrayList<String>();


                //récupération des données de la réponse et classement dans les deux tableaux selon la rareté
                JSONArray data_json = (JSONArray) jsonResponse.getBody().getObject().get("data");
                int len = data_json.length();
                dispatchRarity(data_json, communes, rares, len);

                // Deuxième requête avec une autre extension de cartes
                jsonResponse = Unirest.get(apiURL2)
                        .header("X-Api-Key", apiKEY)
                        .asJson();
                data_json = (JSONArray) jsonResponse.getBody().getObject().get("data");
                len = data_json.length();
                dispatchRarity(data_json, communes, rares, len);

                // on regroupe les deux listes dans une même autre liste
                data.add(communes);
                data.add(rares);


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
}
