package com.citi.movie.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.citi.movie.entity.Movie;


public interface MovieRepository extends CrudRepository<Movie, Long> {
	 List<Movie> findByGenreId(Long genreId);
}
