package com.forfun.dadisthataferret.repository.impl;

import com.forfun.dadisthataferret.model.SearchResult;
import com.forfun.dadisthataferret.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;

import static org.springframework.web.util.UriComponentsBuilder.fromHttpUrl;

@Service
public class GoogleApiImageRepository implements ImageRepository {

    private static final String BASE_URL = "https://www.googleapis.com/customsearch/v1";

    private final String apiKey;
    private final String cxKey;

    private final RestOperations restOperations;

    @Autowired
    public GoogleApiImageRepository(@Value("${google.api.key}") String apiKey,
                                    @Value("${google.api.cx}") String cxKey,
                                    RestOperations restOperations) {
        this.apiKey = apiKey;
        this.cxKey = cxKey;
        this.restOperations = restOperations;
    }

    @Cacheable("imageUrls")
    @Override
    public String imageUrlFor(String animal) {
        String uri = getUriWithParams(animal);
        SearchResult result = restOperations.getForObject(uri, SearchResult.class);
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
                .queryParam("fields", "items(link)")
                .queryParam("cx", cxKey)
                .queryParam("key", apiKey)
                .toUriString();
    }
}
