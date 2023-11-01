package com.example.demo.Controller;


import com.example.demo.Model.Carte;
import com.example.demo.Model.Type;
import com.example.demo.Repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class TypeController {

    @Autowired
    TypeRepository typeRepo;

    @GetMapping("/addType")
    public String showPokemonForm(Model model) {
        return "addType";
    }

    @PostMapping("/addType")
    public String addType(@ModelAttribute Type type, Model model) {

        if (areFieldsEmpty(type)) {
            model.addAttribute("failMessage", "Veuillez remplir tous les champs.");
            return "addType";
        }

        if (typeRepo.findNoms().contains(type.getNom())) {
            model.addAttribute("warningMessage", "Ce type existe déjà !");
            return "addType";
        }

        typeRepo.save(type);
        model.addAttribute("successMessage", "Le type a été créé avec succès !");

        return "addType";
    }

    private boolean areFieldsEmpty(Type type) {
        return type.getNom().isEmpty();
    }
}