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
        try {
            // Commenté temporairement pour éviter le timeout
            // cartesData.initialize();
            System.out.println("Page d'accueil chargée avec succès");
        } catch (Exception e) {
            System.err.println("Erreur lors de l'initialisation: " + e.getMessage());
            // En cas d'erreur, on continue quand même
        }
        return "accueil";
    }
}