package com.citi.oscar.movie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/")
	public String home() {
		return "index";
	}
	
	@GetMapping("/searchmovie")
	public String movie() {
		return "movielist";
	}
	
	@GetMapping("/addmovie")
	public String addmovie() {
		return "add-movie";
	}
	
	
}
