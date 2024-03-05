package com.citi.movie.exception;

public class MovieNotFoundException extends RuntimeException {
    public MovieNotFoundException(String message) {
        super(message);
    }
    // You can add more constructors or methods as needed
}
