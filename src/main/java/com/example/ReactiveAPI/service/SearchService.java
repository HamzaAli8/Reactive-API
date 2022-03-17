package com.example.ReactiveAPI.service;

import com.example.ReactiveAPI.models.Photo;
import com.example.ReactiveAPI.models.PhotoInfo;
import com.example.ReactiveAPI.models.SearchInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class SearchService {

    @Autowired
    UnsplashService unsplashService;

    @Autowired
    PexelService pexelService;

    public Flux<PhotoInfo> pexelImages(SearchInfo searchInfo){

        return pexelService.getImages(searchInfo.getText(), searchInfo.getOrientation())
                .map(i -> new PhotoInfo(i.getUrls().getThumb(), i.getDescription()));
    }

    public Flux<PhotoInfo> unsplashImages(SearchInfo searchInfo){

        return unsplashService.getPhotos(searchInfo.getText(), searchInfo.getOrientation())
                .map(p -> new PhotoInfo(p.getUrls().getThumb(), p.getDescription()));
    }

    public Flux<PhotoInfo> getImages(SearchInfo searchInfo){

        switch(searchInfo.getSelection()) {

            case "pexel":
                pexelImages(searchInfo);

            case "unsplash":

                unsplashImages(searchInfo);
            default:
                return
                        pexelImages(searchInfo).mergeWith(unsplashImages(searchInfo));
        }

    }
}
