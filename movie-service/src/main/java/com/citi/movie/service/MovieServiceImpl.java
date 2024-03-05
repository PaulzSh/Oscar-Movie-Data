package com.citi.movie.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citi.movie.entity.Movie;
import com.citi.movie.repository.*;


@Service
public class MovieServiceImpl implements MovieService {
	
	@Autowired //inject repository dependency
	MovieRepository MovieRepository;
	
	@Override
	public Movie saveMovie(Movie movie) {
		return MovieRepository.save(movie); //save Movie
	}
	
	@Override
	public Movie getMovieById(Long id) {
		Optional<Movie> movie = MovieRepository.findById(id);
		if(movie.isPresent()) return movie.get();
		return null;
	}
	

	@Override
	public List<Movie> getAllMovie() {
		List<Movie> movies = new ArrayList<Movie>(); //create list of Movies variable
		MovieRepository.findAll().forEach(Movie ->movies.add(Movie)); // keep each Movie in Movies list
		System.out.println("Movies - list - "+ movies.size());
		
		return movies;
	}
	
	@Override
	public Movie updateMovie(Long id, Movie movie) {
		Optional<Movie> MovieById = MovieRepository.findById(id);
		
		if(MovieById.isPresent()) { 
			Movie updatedMovie = MovieById.get();
		
			System.out.println("Movie id - " + movie.getId());
			System.out.println("Movie getName - " + movie.getName());
			System.out.println("Movie getMovieDescription - " + movie.getMovieDescription());
			System.out.println("Movie getCast - " + movie.getCast());
			System.out.println("Movie getGenreId - " + movie.getGenreId());
			
			
			updatedMovie.setId(movie.getId());
			updatedMovie.setName(movie.getName());;
			updatedMovie.setMovieDescription(movie.getMovieDescription());
			updatedMovie.setCast(movie.getCast());;
			updatedMovie.setGenreId(movie.getGenreId());
			
			
			return MovieRepository.save(updatedMovie);
		}
		
		return null;
	}

	@Override
	public String deleteMovie(Long id) {
		MovieRepository.deleteById(id); //delete Movie by id
		return "id" + id + "is deleted successfully"; 
	}
	@Override
	public List<Movie> getMoviesByGenre(Long genreId) {
        return MovieRepository.findByGenreId(genreId);
    }
}
