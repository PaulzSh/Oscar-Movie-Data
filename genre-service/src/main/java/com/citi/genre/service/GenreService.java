package com.citi.genre.service;

import java.util.List;

import com.citi.genre.model.Genre;


public interface GenreService {
	
	List<Genre> getAllGenre();
	Genre getGenreById(Integer id);
	Genre saveGenre(Genre genre);
	String deleteGenre(Integer id);
	Genre updateGenre(Integer id, Genre genre);
	

}
