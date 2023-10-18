package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PersonnalisationController {

    @GetMapping("/personnalisation")
    public String showPersonnalisation(Model model) {
        return "personnalisation";
    }

}