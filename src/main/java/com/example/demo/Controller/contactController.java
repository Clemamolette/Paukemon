package com.example.demo.Controller;

import com.example.demo.Model.Carte;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;


@Controller
public class contactController {

    @GetMapping("/contact")
    public String contact(Model model) {
        return "contact";
    }
    @PostMapping("/contact")
    public String submitForm(
            @RequestParam String prenom,
            @RequestParam String situation,
            @RequestParam String email,
            @RequestParam String demande,
            @RequestParam String contenu,
            Model model
    ) {
        if (prenom.isEmpty() || email.isEmpty() || demande.isEmpty() || contenu.isEmpty() || situation.isEmpty()) {
            model.addAttribute("failMessage", "Tous les champs sont obligatoires.");
            return "contact";
        }

        // Envoi fictif du mail

        model.addAttribute("successMessage", "Votre message a été envoyé avec succès !");
        return "contact";
    }
}