package com.forfun.dadisthataferret.service.impl;

import com.forfun.dadisthataferret.model.Animal;
import com.forfun.dadisthataferret.service.AnimalNameService;
import com.forfun.dadisthataferret.service.AnimalService;
import com.forfun.dadisthataferret.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnimalServiceImpl implements AnimalService {

    private final AnimalNameService animalNameService;
    private final ImageService imageService;

    @Autowired
    public AnimalServiceImpl(AnimalNameService animalNameService, ImageService imageService) {
        this.animalNameService = animalNameService;
        this.imageService = imageService;
    }

    @Override
    public Animal getRandomAnimal() {
        String name = animalNameService.getRandomName();
        String imageUrl = imageService.imageUrlFor(name);
        return new Animal(name, imageUrl);
    }
}
