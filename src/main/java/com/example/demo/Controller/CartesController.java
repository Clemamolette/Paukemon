package com.example.demo.Controller;

import java.util.*;

import com.example.demo.Model.Carte;
import com.example.demo.Repository.MesCartesRepository;
import com.example.demo.Service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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