package com.example.ReactiveAPI.controllers;

import com.example.ReactiveAPI.models.SearchKeyword;
import com.example.ReactiveAPI.service.PexelService;
import com.example.ReactiveAPI.service.UnsplashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.thymeleaf.spring5.context.webflux.ReactiveDataDriverContextVariable;


@Controller
public class ViewController {

    @Autowired
    UnsplashService unsplashService;

    @Autowired
    PexelService pexelService;

    @GetMapping("/")
    public String displayIndex(Model model) {
        model.addAttribute("searchKeyword", new SearchKeyword());
        return "index";
    }

    @PostMapping("/search")
    public String performSearch(@ModelAttribute("searchKeyword") SearchKeyword searchKeyword, Model model) {
        switch(searchKeyword.getSelection()) {
            case "both":

                ReactiveDataDriverContextVariable reactiveData =
                        new ReactiveDataDriverContextVariable(unsplashService.getPhotos(searchKeyword.getText(),searchKeyword.getOrientation()), 1);

                ReactiveDataDriverContextVariable reactiveData2 =
                        new ReactiveDataDriverContextVariable(pexelService.getImages(searchKeyword.getText(),searchKeyword.getOrientation()), 1);

                model.addAttribute("photos", reactiveData2);
                model.addAttribute("photos", reactiveData);
                model.addAttribute("searchText", searchKeyword.getText());
                break;

            case "pexel":
                ReactiveDataDriverContextVariable reactiveData3 =
                        new ReactiveDataDriverContextVariable(pexelService.getImages(searchKeyword.getText(),searchKeyword.getOrientation()), 1);

                model.addAttribute("photos", reactiveData3);
                model.addAttribute("searchText", searchKeyword.getText());
                break;

            case "unsplash":

                ReactiveDataDriverContextVariable reactiveData4 =
                        new ReactiveDataDriverContextVariable(unsplashService.getPhotos(searchKeyword.getText(),searchKeyword.getOrientation()), 1);
                model.addAttribute("photos", reactiveData4);
                model.addAttribute("searchText", searchKeyword.getText());
                break;
        }

        return "index";
    }


}