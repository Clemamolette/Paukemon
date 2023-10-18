package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CartesController {

    @GetMapping("/mescartes")
    public String showCartes(Model model) {
        return "mescartes";
    }

}