package com.example.demo;

import java.util.*;

import com.mashape.unirest.http.JsonNode;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;

@Controller
public class CartesController {

    @Autowired
    private PokemonService pokemonService;

    @GetMapping("/mescartes")
    public String showCartes(Model model) {

        ArrayList<ArrayList<String>> pokemonCardsSTR = null;
        pokemonCardsSTR = pokemonService.getCards();
        JSONObject json;

        ArrayList<ArrayList<JSONObject>> pokemonCardsData = new ArrayList<>();
        ArrayList<JSONObject> communesData = new ArrayList<>();
        ArrayList<JSONObject> raresData = new ArrayList<>();

        pokemonCardsData.add(communesData);
        pokemonCardsData.add(raresData);

        // chaque carte est un string, on les transorme en objets JSON
        ArrayList<String> communes = pokemonCardsSTR.get(0);
        for (int i = 0; i<communes.size(); i++) {
            json = new JSONObject(communes.get(i));
            //on ne garde que l'image au format large
            JSONObject image = (JSONObject) json.get("images");
            json.put("images",image.get("large"));
            pokemonCardsData.get(0).add(json);
        }
        ArrayList<String> rares = pokemonCardsSTR.get(1);
        for (int i = 0; i<rares.size(); i++) {
            json = new JSONObject(rares.get(i));
            JSONObject image = (JSONObject) json.get("images");
            json.put("images",image.get("large"));
            pokemonCardsData.get(1).add(json);
        }
        System.out.println("Pokemon cards data : " + pokemonCardsData);
        model.addAttribute("pokemonCardsCommon", pokemonCardsData.get(0));
        model.addAttribute("pokemonCardsRare", pokemonCardsData.get(1));

        return "mescartes";
    }

}