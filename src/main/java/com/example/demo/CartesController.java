package com.example.demo;

import com.mashape.unirest.http.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CartesController {

    @Autowired
    private PokemonService pokemonService;

    @GetMapping("/mescartes")
    public String showCartes(Model model) {

        JsonNode pokemonCardsData = null;
        pokemonCardsData = pokemonService.getCards();

        model.addAttribute("pokemonCards", pokemonCardsData);

        System.out.println(pokemonCardsData);


        return "mescartes";
    }

}