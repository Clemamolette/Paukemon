package com.example.demo;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@Controller
public class AccueilController {
    @GetMapping("/accueil")
    public String showAccueil(Model model) {
        CartesSingleton cartesData = CartesSingleton.getInstance();
        return "accueil";
    }
}