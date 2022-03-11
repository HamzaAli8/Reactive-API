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
public class Images {

    private Integer id;
    private Integer width;
    private Integer height;
    private String url;
    private String photographer;
    @JsonProperty("photographer_url")
    private String photographerUrl;
    @JsonProperty("photographer_id")
    private Integer photographerId;
    @JsonProperty("avg_color")
    private String avgColor;
    private Src src;

}
