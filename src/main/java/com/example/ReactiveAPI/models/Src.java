package com.example.ReactiveAPI.models;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Src {

    private String original;
    private String large;
    private String medium;
    private String small;
    private String portrait;
    private String landscape;
    @JsonProperty("tiny")
    private String thumb;



}
