package com.example.ReactiveAPI.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhotoInfo {

    private String url;
    private String description;
}
