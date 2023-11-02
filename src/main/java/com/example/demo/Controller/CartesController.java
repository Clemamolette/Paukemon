package com.example.demo.Controller;

import java.util.*;

import com.example.demo.Model.Carte;
import com.example.demo.Repository.MesCartesRepository;
import com.example.demo.Repository.TypeRepository;
import com.example.demo.Service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CartesController {

    @Autowired
    private MesCartesRepository mesCartesRepo;
    @Autowired
    private TypeRepository typeRepo;

    @GetMapping("/mescartes")
    public String showCartes(Model model,
                             @RequestParam(required = false) String type,
                             @RequestParam(required = false) String rarity,
                             @RequestParam(required = false) String serie,
                             @RequestParam(required = false) String sort,
                             @RequestParam(required = false) boolean showNonAcquired) {

        ArrayList<Carte> mesCartes = new ArrayList<Carte>();

        if ((sort != null) && (serie != null) && (rarity != null) && (type != null)) {
            if (sort.equals("id")) {
                mesCartes = mesCartesRepo.orderByID();
            } else if (sort.equals("name")) {
                mesCartes = mesCartesRepo.orderByName();
            } else if (sort.equals("hp")) {
                mesCartes = mesCartesRepo.orderByHP();
            } else if (sort.equals("quantity")) {
                mesCartes = mesCartesRepo.orderByQuantity();
            }

            if (!showNonAcquired) {
                mesCartes.removeIf(carte -> !carte.isAcquired());
            }

            if (!serie.isEmpty()) { // si la série choisie ne correspond pas à l'option "Tous"
                mesCartes.removeIf(carte -> !serie.equals(carte.getSerie()));
            }
            if (!rarity.isEmpty()) {
                mesCartes.removeIf(carte -> !rarity.equals(carte.getRarity()));
            }
            if (!type.isEmpty()) {
                mesCartes.removeIf(carte -> !type.equals(carte.getType()));
            }
        } else {
            mesCartes = mesCartesRepo.findAcquired();
        }
        model.addAttribute("mesCartes", mesCartes);
        model.addAttribute("types", typeRepo.findNoms());

        return "mescartes";
    }
}