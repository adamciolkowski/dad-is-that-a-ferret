package com.forfun.dadisthataferret.service.impl;

import com.forfun.dadisthataferret.model.SearchResult;
import com.forfun.dadisthataferret.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import static org.springframework.web.util.UriComponentsBuilder.fromHttpUrl;

@Service
public class GoogleApiImageService implements ImageService {

    private static final String BASE_URL = "https://www.googleapis.com/customsearch/v1";

    private final String apiKey;
    private final String cxKey;

    private final RestTemplate restTemplate;

    private final ConcurrentMap<String, String> imageUrlCache = new ConcurrentHashMap<>();

    @Autowired
    public GoogleApiImageService(@Value("${google.api.key}") String apiKey,
                                 @Value("${google.api.cx}") String cxKey,
                                 RestTemplate restTemplate) {
        this.apiKey = apiKey;
        this.cxKey = cxKey;
        this.restTemplate = restTemplate;
    }

    @Override
    public String imageUrlFor(String animal) {
        return imageUrlCache.computeIfAbsent(animal, this::imageUrlFromGoogleApi);
    }

    private String imageUrlFromGoogleApi(String animal) {
        String uri = getUriWithParams(animal);
        SearchResult result = restTemplate.getForObject(uri, SearchResult.class);
        return linkToFirstItem(result);
    }

    private String linkToFirstItem(SearchResult result) {
        return result.getItems().get(0).getLink();
    }

    private String getUriWithParams(String animal) {
        return fromHttpUrl(BASE_URL)
                .queryParam("q", animal)
                .queryParam("searchType", "image")
                .queryParam("num", 1)
                .queryParam("cx", cxKey)
                .queryParam("key", apiKey)
                .toUriString();
    }
}
