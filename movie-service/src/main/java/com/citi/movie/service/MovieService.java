package com.citi.movie.service;

import java.util.List;

import com.citi.movie.entity.Movie;


public interface MovieService {
	List<Movie> getAllMovie();
	Movie getMovieById(Long id);
	Movie saveMovie(Movie movie);
	String deleteMovie(Long id);
	Movie updateMovie(Long id, Movie movie);
	List<Movie> getMoviesByGenre(Long genreId);
}
