package com.forfun.dadisthataferret.repository.impl;

import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.util.List;

import static org.fest.assertions.api.Assertions.assertThat;

public class ResourceAnimalNameRepositoryTest {

    @Test
    public void shouldLoadAnimalNames() throws Exception {
        Resource resource = new ClassPathResource("/animals.txt");
        List<String> animalNames = ResourceAnimalNameRepository.loadAnimalNamesFrom(resource);

        assertThat(animalNames).containsOnly("raccoon", "elephant", "beaver");
    }
}