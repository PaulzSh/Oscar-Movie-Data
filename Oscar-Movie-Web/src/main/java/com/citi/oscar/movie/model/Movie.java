package com.citi.oscar.movie.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data               // This single annotation replaces getters, setters, toString, equals, and hashCode
@NoArgsConstructor  // Generates a no-argument constructor
@AllArgsConstructor // Generates a constructor with all arguments
@ToString
public class Movie {
    private Long id;
    private String name;
    private String cast;
    private Genre genre;
}