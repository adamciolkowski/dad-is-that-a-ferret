package com.forfun.dadisthataferret.controller;

import com.forfun.dadisthataferret.model.Animal;
import com.forfun.dadisthataferret.service.AnimalService;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class AnimalControllerTest {

    AnimalService animalService = mock(AnimalService.class);

    AnimalController animalController = new AnimalController(animalService);

    MockMvc mockMvc = standaloneSetup(animalController).build();

    String animalName = "raccoon";
    String imageUrl = "http://www.example.url.com/raccoon.jpg";

    Animal animal = new Animal(animalName, imageUrl);

    @Test
    public void shouldReturnViewWithGivenAnimalNAmeAndImageUrl() throws Exception {
        when(animalService.getRandomAnimal()).thenReturn(animal);

        mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attribute("animalName", "a " + animalName))
                .andExpect(model().attribute("imageUrl", imageUrl));
    }
}