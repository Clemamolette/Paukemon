package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BWOpeningController {

    @GetMapping("/openingBW")
    public String showOpeningBW(Model model) {
        return "openingBW";
    }

}