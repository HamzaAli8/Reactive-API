package com.example.ReactiveAPI.controllers;

import com.example.ReactiveAPI.models.SearchKeyword;
import com.example.ReactiveAPI.service.UnsplashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.spring5.context.webflux.ReactiveDataDriverContextVariable;

@Controller
public class ViewController {

    @Autowired
    UnsplashService unsplashService;

    @GetMapping("/")
    public String displayIndex(Model model) {
        model.addAttribute("searchKeyword", new SearchKeyword());
        return "index";
    }

    @PostMapping("/search")
    public String performSearch(@ModelAttribute("searchKeyword") SearchKeyword searchKeyword, Model model,
    @RequestParam(value = "orientation",required = false ) String orientation) {
        ReactiveDataDriverContextVariable reactiveData =
                new ReactiveDataDriverContextVariable(unsplashService.getPhotos(searchKeyword.getText(),orientation), 1);
        model.addAttribute("photos", reactiveData);
        model.addAttribute("searchText", searchKeyword.getText());
        return "index";
    }
}