package com.example.demo.Controller;


import com.example.demo.Service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AccueilController {
    @Autowired
    PokemonService cartesData;
    @GetMapping("/accueil")
    public String showAccueil(Model model) {
        cartesData.initialize();
        return "accueil";
    }
}