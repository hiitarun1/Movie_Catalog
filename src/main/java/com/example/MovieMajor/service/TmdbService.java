package com.example.MovieMajor.service;

import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class TmdbService {

    private final WebClient webClient;
    private final String apiKey;
    private final String imageBaseUrl;

    public TmdbService(WebClient.Builder webClientBuilder,
            @Value("${tmdb.api.base-url}") String baseUrl,
            @Value("${tmdb.api.key}") String apiKey,
            @Value("${tmdb.image.base-url}") String imageBaseUrl) {
        this.webClient = webClientBuilder.baseUrl(baseUrl).build();
        this.apiKey = apiKey;
        this.imageBaseUrl = imageBaseUrl;
    }

    public String fetchMoviePosterUrl(String movieTitle) {
        try {
            Map response = webClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/search/movie")
                            .queryParam("api_key", apiKey)
                            .queryParam("query", movieTitle)
                            .build())
                    .retrieve()
                    .bodyToMono(Map.class)
                    .block();

            List<?> results = (List<?>) response.get("results");
            if (results != null && !results.isEmpty()) {
                Map<?, ?> first = (Map<?, ?>) results.get(0);
                String posterPath = (String) first.get("poster_path");
                if (posterPath != null) return imageBaseUrl + "w500" + posterPath;
            }
        } catch (Exception e) {
            log.warn("Failed to fetch poster for movie: {}", movieTitle, e);
        }
        return null;
    }

    public String fetchPersonImageUrl(String personName) {
        try {
            Map response = webClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/search/person")
                            .queryParam("api_key", apiKey)
                            .queryParam("query", personName)
                            .build())
                    .retrieve()
                    .bodyToMono(Map.class)
                    .block();

            List<?> results = (List<?>) response.get("results");
            if (results != null && !results.isEmpty()) {
                Map<?, ?> first = (Map<?, ?>) results.get(0);
                String profilePath = (String) first.get("profile_path");
                if (profilePath != null) return imageBaseUrl+ "w500" + profilePath;
            }
        } catch (Exception e) {
            log.warn("Failed to fetch image for person: {}", personName, e);
        }
        return null;
    }

}
