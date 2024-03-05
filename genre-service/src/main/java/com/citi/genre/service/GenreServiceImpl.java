package com.citi.genre.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citi.genre.model.Genre;
import com.citi.genre.repository.*;


@Service
public class GenreServiceImpl implements GenreService {
	
	@Autowired //inject repository dependency
	GenreRepository GenreRepository;
	
	@Override
	public Genre saveGenre(Genre genre) {
		return GenreRepository.save(genre); //save Genre
	}
	
	@Override
	public Genre getGenreById(Integer id) {
		Optional<Genre> genre = GenreRepository.findById(id);
		if(genre.isPresent()) return genre.get();
		return null;
	}
	

	@Override
	public List<Genre> getAllGenre() {
		List<Genre> genres = new ArrayList<Genre>(); //create list of Genres variable
		GenreRepository.findAll().forEach(Genre ->genres.add(Genre)); // keep each Genre in Genres list
		System.out.println("Genres - list - "+ genres.size());
		
		return genres;
	}
	
	@Override
	public Genre updateGenre(Integer id, Genre genre) {
		Optional<Genre> GenreById = GenreRepository.findById(id);
		
		if(GenreById.isPresent()) { 
			Genre updatedGenre = GenreById.get();
		
			updatedGenre.setId(genre.getId());
			updatedGenre.setName(genre.getName());;
			updatedGenre.setDescription(genre.getDescription());;
			
			return GenreRepository.save(updatedGenre);
		}
		
		return null;
	}

	@Override
	public String deleteGenre(Integer id) {
		GenreRepository.deleteById(id); //delete Genre by id
		return "id" + id + "is deleted successfully"; 
	}
}
