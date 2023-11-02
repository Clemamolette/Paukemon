package com.example.demo.Controller;

import java.util.*;

import com.example.demo.Model.Carte;
import com.example.demo.Model.Type;
import com.example.demo.Repository.MesCartesRepository;
import com.example.demo.Repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class addCarteController {

    @Autowired
    TypeRepository typeRepo;
    @Autowired
    MesCartesRepository mesCartesRepo;

    @GetMapping("/addCarte")
    public String showCarteForm(Model model) {
        List<Type> types = typeRepo.findAll();
        model.addAttribute("types",types);
        return "addCarte";
    }

    @PostMapping("/addCarte")
    public String addCarte(@ModelAttribute Carte carte, Model model) {

        try {
            List<Type> types = typeRepo.findAll();
            // vérifier si tous les champs sont remplis
            if (areFieldsEmpty(carte)) {
                model.addAttribute("failMessage", "Veuillez remplir tous les champs.");
                model.addAttribute("types", types);
                return "addCarte";
            }

            int next_size = mesCartesRepo.findAll().size() + 1;
            String id = carte.getSerie() + next_size;
            carte.setId(id);

            mesCartesRepo.save(carte);
            model.addAttribute("types",types);
            model.addAttribute("successMessage", "La carte a été ajoutée avec succès !");

            return "addCarte";
        } catch (DataIntegrityViolationException e) {
            List<Type> types = typeRepo.findAll();
            model.addAttribute("types",types);
            model.addAttribute("failMessage", "Chaînes de caractères trop longues.");
            return "addCarte";
        }
    }

    private boolean areFieldsEmpty(Carte carte) {
        return carte.getName().isEmpty() || carte.getHp().isEmpty() || carte.getType().isEmpty() || carte.getRarity().isEmpty() || carte.getImages().isEmpty() || carte.getSerie().isEmpty();
    }
}