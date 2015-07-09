package com.forfun.dadisthataferret.service.impl;

import com.forfun.dadisthataferret.service.AnimalNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static com.forfun.dadisthataferret.utils.Utils.randomElement;

@Service
public class ResourceAnimalNameService implements AnimalNameService {

    private final List<String> animals;

    @Autowired
    public ResourceAnimalNameService(Resource resource) throws IOException {
        this.animals = loadAnimalNamesFrom(resource);
    }

    public static List<String> loadAnimalNamesFrom(Resource resource) throws IOException {
        try(BufferedReader reader = readerFrom(resource)) {
            return linesToList(reader);
        }
    }

    private static BufferedReader readerFrom(Resource resource) throws IOException {
        return new BufferedReader(new InputStreamReader(resource.getInputStream()));
    }

    private static List<String> linesToList(BufferedReader reader) throws IOException {
        List<String> animals = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            animals.add(line);
        }
        return animals;
    }

    @Override
    public String getRandomName() {
        return randomElement(animals);
    }
}
