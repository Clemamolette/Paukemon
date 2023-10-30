package com.example.demo.Controller;

import com.example.demo.Model.Carte;
import com.example.demo.Service.PokemonService;
import com.example.demo.Repository.MesCartesRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Controller
public class BWOpeningController {

    @Autowired
    MesCartesRepository mesCartesRepo;

    @Autowired
    PokemonService cartesData;

    @GetMapping("/openingBW")
    public String showOpeningBW(Model model) {

        ArrayList<JSONObject> communesBW = cartesData.getCommunesBW();
        ArrayList<JSONObject> raresBW = cartesData.getRaresBW();

        int taille_communes = communesBW.size();
        int taille_rares = raresBW.size();

        ArrayList<Integer> communes = new ArrayList<>();
        Integer rare;
        int tmp;

        while (communes.size() <4) {
            tmp = getRandomNumber(0, taille_communes);
            if (!(communes.contains(tmp))) {
                communes.add(tmp);
            }
        }
        rare = getRandomNumber(0, taille_rares);


        model.addAttribute("commune1", communesBW.get(communes.get(0)));
        model.addAttribute("commune2", communesBW.get(communes.get(1)));
        model.addAttribute("commune3", communesBW.get(communes.get(2)));
        model.addAttribute("commune4", communesBW.get(communes.get(3)));
        model.addAttribute("rare", raresBW.get(rare));

        saveCarte(communesBW.get(communes.get(0)));
        saveCarte(communesBW.get(communes.get(1)));
        saveCarte(communesBW.get(communes.get(2)));
        saveCarte(communesBW.get(communes.get(3)));
        saveCarte(raresBW.get(rare));

        return "openingBW";
    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
    public void saveCarte(JSONObject carte) {
        String id = carte.get("id").toString();
        if (mesCartesRepo.findIDS().contains(id)) { // on ajoute 1 à la quantité de cette carte possédée
            mesCartesRepo.updateQuantityById(id, mesCartesRepo.findQuantityByID(id)+1);
        } else {  // ou on créer cette nouvelle carte dans nos possessions
            Carte c = new Carte();
            c.setId(id);
            c.setName(carte.get("name").toString());
            c.setImages(carte.get("images").toString());
            c.setHp(carte.get("hp").toString());
            c.setRarity(carte.get("rarity").toString());
            c.setType(carte.get("types").toString().substring(2,carte.get("types").toString().length()-2));
            c.setQuantity(1);
            c.setAcquired(true);

            mesCartesRepo.save(c);
        }
    }
}