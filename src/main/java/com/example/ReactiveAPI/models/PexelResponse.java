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
public class PexelResponse {

    @JsonProperty("total_results")
    private Integer totalResults;
    private Integer page;
    @JsonProperty("per_page")
    private Integer perPage;
    private List<Images> photos;



}
