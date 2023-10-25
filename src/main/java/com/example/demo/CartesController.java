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

    @Autowired
    private MesCartesRepository mesCartesRepo;

    @GetMapping("/mescartes")
    public String showCartes(Model model) {

        List<Carte> mesCartes = mesCartesRepo.findAll();

        model.addAttribute("mesCartes", mesCartes);


        return "mescartes";
    }

}