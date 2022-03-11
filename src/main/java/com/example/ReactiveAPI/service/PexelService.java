package com.example.ReactiveAPI.service;

import com.example.ReactiveAPI.models.Images;
import com.example.ReactiveAPI.models.PexelResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PexelService {

    @Autowired
    @Qualifier("pexel")
    WebClient webClient;

    public Flux<Images> getImages(String searchText, String orientation) {
        return getTotalResults(searchText)
                .flatMapMany(t -> Flux.range(1, t > 5 ? 5 : t))
                .flatMap(f -> searchPexel(searchText, f, orientation)
                        .flatMapIterable(PexelResponse::getPhotos), 5);
    }

    public Mono<Integer> getTotalResults(String searchText) {
        return webClient.get()
                .uri(uri -> uri
                        .queryParam("page", "1")
                        .queryParam("query", searchText).build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve().bodyToMono(PexelResponse.class)
                .map(PexelResponse::getTotalResults)
                .map(Integer::valueOf);
    }


    public Mono<PexelResponse> searchPexel(String searchText, int pageNumber, String orientation) {
        return webClient.get()
                .uri(uri -> uri
                        .queryParam("page", pageNumber)
                        .queryParam("query", searchText)
                        .queryParam("orientation", orientation)
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(PexelResponse.class);
    }


}
