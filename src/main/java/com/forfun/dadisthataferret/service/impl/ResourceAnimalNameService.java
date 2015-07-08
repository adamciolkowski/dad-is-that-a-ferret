package com.forfun.dadisthataferret.service.impl;

import com.forfun.dadisthataferret.service.AnimalNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

import static com.forfun.dadisthataferret.utils.Utils.randomElement;
import static java.nio.file.Files.lines;
import static java.nio.file.Paths.get;
import static java.util.stream.Collectors.toList;

@Service
public class ResourceAnimalNameService implements AnimalNameService {

    private final List<String> animals;

    @Autowired
    public ResourceAnimalNameService(Resource resource) throws IOException {
        this.animals = loadAnimalNamesFrom(resource);
    }

    public static List<String> loadAnimalNamesFrom(Resource resource) throws IOException {
        try(Stream<String> lines = lines(get(resource.getURI()))) {
            return lines.collect(toList());
        }
    }

    @Override
    public String getRandomName() {
        return randomElement(animals);
    }
}
