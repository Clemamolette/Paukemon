package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BaseOpeningController {

    @GetMapping("/openingBase")
    public String showOpeningBase(Model model) {
        return "openingBase";
    }

}