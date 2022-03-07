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
public class Photo {

    private String id;
    private String title;
    private String description;
    @JsonProperty("published_at")
    private String publishedAt;
    @JsonProperty("last_collected_at")
    private String lastCollectedAt;
    @JsonProperty("updated_at")
    private String updatedAt;
    private Boolean featured;
    @JsonProperty("total_photos")
    private Integer totalPhotos;
    @JsonProperty("private")
    private Boolean privates;
    private Urls urls;


}
