package com.example.demo.Controler;


import com.example.demo.Singleton.CartesSingleton;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AccueilController {
    @GetMapping("/accueil")
    public String showAccueil(Model model) {
        CartesSingleton cartesData = CartesSingleton.getInstance();
        return "accueil";
    }
}