package com.forfun.dadisthataferret.controller;

import com.forfun.dadisthataferret.service.AnimalNameService;
import com.forfun.dadisthataferret.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class AnimalController {

    private final AnimalNameService animalNameService;
    private final ImageService imageService;

    @Autowired
    public AnimalController(AnimalNameService animalNameService, ImageService imageService) {
        this.animalNameService = animalNameService;
        this.imageService = imageService;
    }

    @RequestMapping(value = "/", method = GET)
    public String index(Model model) {
        String animal = animalNameService.getRandomName();
        model.addAttribute("animalName", animal);
        model.addAttribute("imageUrl", imageService.imageUrlFor(animal));
        return "index";
    }
}
