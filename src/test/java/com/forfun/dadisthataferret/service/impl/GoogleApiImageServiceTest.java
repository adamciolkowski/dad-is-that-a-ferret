package com.forfun.dadisthataferret.service.impl;

import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.client.MockRestServiceServer.createServer;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.anything;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

public class GoogleApiImageServiceTest {

    RestTemplate restTemplate = new RestTemplate();

    GoogleApiImageService animalImageService = new GoogleApiImageService("1234", "abcd", restTemplate);

    MockRestServiceServer mockServer = createServer(restTemplate);

    @Test
    public void shouldReturnImageUrlForGivenAnimal() throws Exception {
        mockServer.expect(anything())
                .andRespond(withSuccess(new ClassPathResource("/response.json"), APPLICATION_JSON));

        String imageUrl = animalImageService.imageUrlFor("raccoon");

        mockServer.verify();
        assertThat(imageUrl).isEqualTo("http://www.example.url.com/raccoon.jpg");
    }
}