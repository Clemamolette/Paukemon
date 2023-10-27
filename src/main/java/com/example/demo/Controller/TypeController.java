package com.example.demo.Controler;


import com.example.demo.Model.Type;
import com.example.demo.Repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TypeController {

    @Autowired
    TypeRepository typeRepo;

    @GetMapping("/addType")
    public String showPokemonForm(Model model) {
        return "addType";
    }

    @PostMapping("/addType")
    public String addType(@ModelAttribute Type type) {
        System.out.println("type:"+type.toString());
        typeRepo.save(type);
        return "redirect:/addType";
    }
}