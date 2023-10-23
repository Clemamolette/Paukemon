package com.example.demo;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Controller
public class BaseOpeningController {

    @GetMapping("/openingBase")
    public String showOpeningBase(Model model) {


        CartesSingleton cartesData = CartesSingleton.getInstance();
        ArrayList<JSONObject> communesBase = cartesData.getCommunesBase();
        ArrayList<JSONObject> raresBase = cartesData.getRaresBase();

        int taille_communes = communesBase.size();
        int taille_rares = raresBase.size();

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


        model.addAttribute("commune1", communesBase.get(communes.get(0)));
        model.addAttribute("commune2", communesBase.get(communes.get(1)));
        model.addAttribute("commune3", communesBase.get(communes.get(2)));
        model.addAttribute("commune4", communesBase.get(communes.get(3)));
        model.addAttribute("rare", raresBase.get(rare));


        return "openingBase";
    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

}