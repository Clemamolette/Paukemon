package com.example.demo;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BWOpeningController {

    @GetMapping("/openingBW")
    public String showOpeningBW(Model model) {

        CartesSingleton cartesData = CartesSingleton.getInstance();
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


        return "openingBW";
    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

}