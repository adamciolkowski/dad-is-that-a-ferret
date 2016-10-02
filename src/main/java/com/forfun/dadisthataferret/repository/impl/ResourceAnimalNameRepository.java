package com.forfun.dadisthataferret.repository.impl;

import com.forfun.dadisthataferret.repository.AnimalNameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

import static com.forfun.dadisthataferret.utils.Utils.randomElement;
import static java.nio.file.Files.readAllLines;

@Service
public class ResourceAnimalNameRepository implements AnimalNameRepository {

    private final List<String> animals;

    @Autowired
    public ResourceAnimalNameRepository(Resource resource) throws IOException {
        this.animals = loadAnimalNamesFrom(resource);
    }

    public static List<String> loadAnimalNamesFrom(Resource resource) throws IOException {
        return readAllLines(resource.getFile().toPath());
    }

    @Override
    public String getRandomName() {
        return randomElement(animals);
    }
}
