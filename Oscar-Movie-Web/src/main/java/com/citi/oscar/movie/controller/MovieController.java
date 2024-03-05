package com.citi.oscar.movie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.citi.oscar.movie.service.MovieService;

@Controller
public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping("/movies")
    public String listMovies(Model model) {
        model.addAttribute("movies", movieService.getAllMovies());
        return "movies"; // Name of the Thymeleaf template
    }
}
