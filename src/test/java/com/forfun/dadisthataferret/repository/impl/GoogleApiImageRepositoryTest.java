package com.forfun.dadisthataferret.repository.impl;

import com.forfun.dadisthataferret.model.Item;
import com.forfun.dadisthataferret.model.SearchResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.client.MockRestServiceServer.createServer;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.anything;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RepositoryConfig.class)
public class GoogleApiImageRepositoryTest {

    RestTemplate restTemplate = new RestTemplate();

    GoogleApiImageRepository animalImageService = new GoogleApiImageRepository("1234", "abcd", restTemplate);

    MockRestServiceServer mockServer = createServer(restTemplate);

    @Test
    public void shouldReturnImageUrlForGivenAnimal() throws Exception {
        mockServer.expect(anything())
                .andRespond(withSuccess(new ClassPathResource("/response.json"), APPLICATION_JSON));

        String imageUrl = animalImageService.imageUrlFor("raccoon");

        mockServer.verify();
        assertThat(imageUrl).isEqualTo("http://www.example.url.com/raccoon.jpg");
    }

    @Test
    public void shouldCacheImageSearches() {
        RestTemplate restTemplate = Mockito.mock(RestTemplate.class);
        SearchResult searchResult = new SearchResult();
        searchResult.setItems(Collections.singletonList(new Item()));
        when(restTemplate.getForObject(anyString(), any())).thenReturn(searchResult);
        GoogleApiImageRepository animalImageService = new GoogleApiImageRepository("1234", "abcd", restTemplate);

        animalImageService.imageUrlFor("rabbit");
        animalImageService.imageUrlFor("rabbit");

        verify(restTemplate, times(1)).getForObject(anyString(), any());
    }
}