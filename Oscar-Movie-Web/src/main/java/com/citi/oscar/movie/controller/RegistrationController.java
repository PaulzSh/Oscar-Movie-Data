package com.citi.oscar.movie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.citi.oscar.movie.model.UserRegistration;
import com.citi.oscar.movie.service.UserService;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

   private UserService userService;

   public RegistrationController(UserService userService) {
      super();
      this.userService = userService;
   }

   @ModelAttribute("user")
   public UserRegistration userRegistrationDto() {
      return new UserRegistration();
   }

   @GetMapping
   public String showRegistrationForm() {
      return "registration";
   }

   @PostMapping
   public String registerUserAccount(@ModelAttribute("user") 
                  UserRegistration registrationDto) {

      try {
         userService.save(registrationDto);
      }catch(Exception e)
      {
         System.out.println(e);
         return "redirect:/registration?email_invalid";
      }
      return "redirect:/registration?success";
   }
}