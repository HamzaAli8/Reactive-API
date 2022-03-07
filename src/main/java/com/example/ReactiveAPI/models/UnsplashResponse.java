package com.example.ReactiveAPI.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UnsplashResponse {

    private Integer total;
    @JsonProperty("total_pages")
    private Integer totalPages;
    private List<Photo> results;



}
