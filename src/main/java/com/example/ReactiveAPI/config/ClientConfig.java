package com.example.ReactiveAPI.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URI;

@Configuration
public class ClientConfig {

    @Value("${search.uri.unsplash}")
    private URI unsplashSearchUri;

    @Value("${api.key.unsplash}")
    private String unsplashSecret;

    @Value("${search.uri.pexel}")
    private URI pexelSearchUri;

    @Value("${api.key.pexel}")
    private String pexelSecret;



    @Bean
    @Qualifier("unsplash")
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl(unsplashSearchUri.toString())
                .defaultHeader(HttpHeaders.AUTHORIZATION, unsplashSecret)
                .build();
    }


    @Bean
    @Qualifier("pexel")
    public WebClient webClient2() {
        return WebClient.builder()
                .baseUrl(pexelSearchUri.toString())
                .defaultHeader(HttpHeaders.AUTHORIZATION, pexelSecret)
                .build();
    }







}
