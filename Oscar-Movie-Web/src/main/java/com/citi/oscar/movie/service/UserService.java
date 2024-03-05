package com.citi.oscar.movie.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.citi.oscar.movie.entity.User;
import com.citi.oscar.movie.model.UserRegistration;

import java.util.List;

public interface UserService extends UserDetailsService {
   
   User save(UserRegistration registration);
   List<User> getAll();
}