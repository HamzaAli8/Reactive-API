package com.example.ReactiveAPI.service;


import com.example.ReactiveAPI.models.Photo;
import com.example.ReactiveAPI.models.UnsplashResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UnsplashService {

    @Autowired
    WebClient webClient;

    public Flux<Photo> getPhotos(String searchText, String orientation) {
        return getTotalPages(searchText)
                .flatMapMany(t -> Flux.range(1, t > 5 ? 5 : t))
                .flatMap(f -> searchUnsplash(searchText, f, orientation)
                        .flatMapIterable(UnsplashResponse::getResults), 5);
    }

    public Mono<Integer> getTotalPages(String searchText) {
        return webClient.get()
                .uri(uri -> uri
                        .queryParam("page", "1")
                        .queryParam("query", searchText).build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve().bodyToMono(UnsplashResponse.class)
                .map(UnsplashResponse::getTotalPages)
                .map(Integer::valueOf);
    }

    public Mono<UnsplashResponse> searchUnsplash( String searchText, int pageNumber, String orientation) {
        return webClient.get()
                .uri(uri -> uri
                        .queryParam("page", pageNumber)
                        .queryParam("query", searchText)
                        .queryParam("orientation", orientation)
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(UnsplashResponse.class);
    }
}



