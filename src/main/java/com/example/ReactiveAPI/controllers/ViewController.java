package com.example.ReactiveAPI.controllers;

import com.example.ReactiveAPI.models.SearchInfo;
import com.example.ReactiveAPI.service.PexelService;
import com.example.ReactiveAPI.service.SearchService;
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

    @Autowired
    SearchService searchService;

    @GetMapping("/")
    public String displayIndex(Model model) {
        model.addAttribute("searchInfo", new SearchInfo());
        return "index";
    }

    @PostMapping("/search")
    public String performSearch(@ModelAttribute("searchInfo") SearchInfo searchInfo, Model model) {

                ReactiveDataDriverContextVariable reactiveData =

                        new ReactiveDataDriverContextVariable(searchService.getImages(searchInfo), 1);

                model.addAttribute("photos", reactiveData);
                model.addAttribute("searchText", searchInfo.getText());

                if(searchInfo.getSelection().equals("pexel")  || searchInfo.getSelection().equals("both")){

                model.addAttribute("pexel", true);
        }

        return "index";
    }
}