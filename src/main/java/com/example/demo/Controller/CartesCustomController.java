package com.example.demo.Controller;


import com.example.demo.Model.Carte;
import com.example.demo.Model.Type;
import com.example.demo.Repository.MesCartesRepository;
import com.example.demo.Repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CartesCustomController {

    @Autowired
    TypeRepository typeRepo;
    @Autowired
    MesCartesRepository mesCartesRepo;

    @GetMapping("/gestionCartes")
    public String showCustomCards(Model model) {
        ArrayList<Carte> cartesCustom = mesCartesRepo.getCustom();
        model.addAttribute("cartesCustom", cartesCustom);
        return "gestionCartes";
    }

    @GetMapping("/modifierCarte/{id}")
    public String showModifyCardForm(@PathVariable String id, Model model) {
        Carte carte = mesCartesRepo.findCarteByID(id);
        ArrayList<String> types = mesCartesRepo.getTypes();
        model.addAttribute("carte", carte);
        model.addAttribute("types", types);
        return "modifierCarte";
    }

    @PostMapping("/modifierCarte")
    public String modifyCustomCard(@ModelAttribute Carte carte, Model model) {

        String id = carte.getId();
        mesCartesRepo.updateName(id, carte.getName());
        mesCartesRepo.updateHp(id, carte.getHp());
        mesCartesRepo.updateType(id, carte.getType());
        mesCartesRepo.updateImage(id, carte.getImages());
        mesCartesRepo.updateRarity(id, carte.getRarity());
        mesCartesRepo.updateSerie(id, carte.getSerie());

        mesCartesRepo.save(carte);
        model.addAttribute("successMessage", "La carte a bien été modifiée !");
        ArrayList<Carte> cartesCustom = mesCartesRepo.getCustom();
        model.addAttribute("cartesCustom", cartesCustom);
        return "gestionCartes";
    }

    @PostMapping("/supprimerCarte/{id}")
    public String deleteCustomCard(@PathVariable String id, Model model) {
        mesCartesRepo.deleteById(id);
        model.addAttribute("successMessage", "La carte a bien été supprimée !");
        ArrayList<Carte> cartesCustom = mesCartesRepo.getCustom();
        model.addAttribute("cartesCustom", cartesCustom);
        return "gestionCartes";
    }

}