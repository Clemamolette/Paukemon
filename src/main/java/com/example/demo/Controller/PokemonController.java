package com.example.demo.Controler;

import java.util.*;

import com.example.demo.Model.Pokemon;
import com.example.demo.Repository.PokemonRepository;
import com.example.demo.Model.Type;
import com.example.demo.Repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PokemonController {

    @Autowired
    PokemonRepository pokemonRepo;
    @Autowired
    TypeRepository typeRepo;

    @GetMapping("/addPokemon")
    public String showPokemonForm(Model model) {
        List<Type> types = typeRepo.findAll();
        model.addAttribute("types",types);
        return "addPokemon";
    }

    @PostMapping("/addPokemon")
    public String addPokemon(@ModelAttribute Pokemon pokemon) {

        System.out.println("pokemon:"+pokemon.toString());

        pokemonRepo.save(pokemon);

        return "redirect:/addPokemon";
    }
}