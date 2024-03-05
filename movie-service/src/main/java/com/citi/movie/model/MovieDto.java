package com.citi.movie.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data               // This single annotation replaces getters, setters, toString, equals, and hashCode
@NoArgsConstructor  // Generates a no-argument constructor
@AllArgsConstructor // Generates a constructor with all arguments
@ToString
public class MovieDto {

    private Long id;
    private String name;
    private String movieDescription;
    private String cast;
    private Integer genreId;
    private String genre;
    private String description;

  
}