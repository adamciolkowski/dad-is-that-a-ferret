package com.forfun.dadisthataferret.service.impl;

import com.forfun.dadisthataferret.model.Animal;
import com.forfun.dadisthataferret.repository.AnimalNameRepository;
import com.forfun.dadisthataferret.repository.ImageRepository;
import com.forfun.dadisthataferret.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnimalServiceImpl implements AnimalService {

    private final AnimalNameRepository animalNameRepository;
    private final ImageRepository imageRepository;

    @Autowired
    public AnimalServiceImpl(AnimalNameRepository animalNameRepository, ImageRepository imageRepository) {
        this.animalNameRepository = animalNameRepository;
        this.imageRepository = imageRepository;
    }

    @Override
    public Animal getRandomAnimal() {
        String name = animalNameRepository.getRandomName();
        String imageUrl = imageRepository.imageUrlFor(name);
        return new Animal(name, imageUrl);
    }
}
