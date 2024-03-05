package com.citi.oscar.movie.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.citi.oscar.movie.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
   User findByEmail(String email);
}