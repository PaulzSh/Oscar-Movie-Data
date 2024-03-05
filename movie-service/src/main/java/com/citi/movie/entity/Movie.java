package com.citi.movie.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;  // Generates the all-args constructor
import lombok.Data;       // Lombok annotation for getters, setters, toString, equals, and hashCode
import lombok.NoArgsConstructor; // Generates the no-args constructor
import lombok.ToString;


@Entity
@Table(name = "movies")
@Data               // This single annotation replaces getters, setters, toString, equals, and hashCode
@NoArgsConstructor  // Generates a no-argument constructor
@AllArgsConstructor // Generates a constructor with all arguments
@ToString
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;
    
    @Column(name = "movie_description", nullable = false)
    private String movieDescription;
    
    @Column(name = "\"cast\"", nullable = false)
    private String cast;

    @Column(name = "genre_id", nullable = false)
    private Integer genreId;
    
    
    
    @Transient
    private Genre genre;
}