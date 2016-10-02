package com.forfun.dadisthataferret.controller;

import com.forfun.dadisthataferret.model.Animal;
import com.forfun.dadisthataferret.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.forfun.dadisthataferret.utils.LanguageUtils.articleFor;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class AnimalController {

    private final AnimalService animalService;

    @Autowired
    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @RequestMapping(value = "/", method = GET)
    public String index(Model model) {
        Animal animal = animalService.getRandomAnimal();
        String name = animal.getName();
        model.addAttribute("animalName", articleFor(name) + " " + name);
        model.addAttribute("imageUrl", animal.getImageUrl());
        return "index";
    }
}
