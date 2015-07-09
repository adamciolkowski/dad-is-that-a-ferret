package com.forfun.dadisthataferret.controller;

import com.forfun.dadisthataferret.service.ImageService;
import com.forfun.dadisthataferret.service.AnimalNameService;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class AnimalControllerTest {

    AnimalNameService animalNameService = mock(AnimalNameService.class);
    ImageService imageService = mock(ImageService.class);

    AnimalController animalController = new AnimalController(animalNameService, imageService);

    MockMvc mockMvc = standaloneSetup(animalController).build();

    String animalName = "raccoon";
    String imageUrl = "http://www.example.url.com/raccoon.jpg";

    @Test
    public void shouldReturnViewWithGivenAnimalNAmeAndImageUrl() throws Exception {
        when(animalNameService.getRandomName()).thenReturn(animalName);
        when(imageService.imageUrlFor("raccoon")).thenReturn(imageUrl);

        mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attribute("animalName", "a " + animalName))
                .andExpect(model().attribute("imageUrl", imageUrl));
    }
}