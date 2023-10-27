package com.example.demo.Controler;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoostersController {

    @GetMapping("/boosters")
    public String showBoosters(Model model) {
        return "boosters";
    }

}