package com.citi.movie.controller;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.citi.movie.entity.Genre;
import com.citi.movie.entity.Movie;
import com.citi.movie.exception.ErrorDetails;
import com.citi.movie.exception.MovieNotFoundException;
import com.citi.movie.model.MovieDto;
import com.citi.movie.service.MovieService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@RestController
@CrossOrigin(origins = "http://localhost:8083") 
@Tag(name = "Movies", description = "Operations related to movies in the Movie Management System")
public class MovieController {

	private static final Logger logger = LoggerFactory.getLogger(MovieController.class);

	@Autowired
	MovieService movieService;

	@PostMapping("/addmovie")
	@Operation(summary = "Add a new movie", description = "Creates a new movie in the system and returns it.", responses = {
			@ApiResponse(description = "The created movie", responseCode = "200", content = @Content(schema = @Schema(implementation = Movie.class))),
			@ApiResponse(description = "Error saving the movie", responseCode = "500", content = @Content(schema = @Schema(implementation = ErrorDetails.class))) })
	public Movie saveMovie(@RequestBody MovieDto movieDto) {
		logger.info("saveMovie entered with movie: {}", movieDto);
		try {
			
			Movie movie = new Movie();
			movie.setGenreId(movieDto.getGenreId());
			movie.setName(movieDto.getName());
			movie.setCast(movieDto.getCast());
			movie.setMovieDescription(movieDto.getMovieDescription());
			
			return movieService.saveMovie(movie);
		} catch (Exception e) {
			logger.error("Error saving movie: ", e);
			throw new MovieNotFoundException("Error saving movie: " + e.getMessage());
		}
	}

	@GetMapping("/movie/{id}")
	@Operation(summary = "Get a movie by its ID", description = "Fetches a movie by its unique identifier from the system.", responses = {
			@ApiResponse(responseCode = "200", description = "The found movie", content = @Content(schema = @Schema(implementation = Movie.class))),
			@ApiResponse(responseCode = "404", description = "Movie not found", content = @Content(schema = @Schema(implementation = ErrorDetails.class))) })
	public ResponseEntity<MovieDto> getMovie(@PathVariable("id") Long id) {
		logger.info("GET /movie/{} - getMovie entered", id);
		try {
			MovieDto movieDto = new MovieDto();
			Movie movie = movieService.getMovieById(id);
			
			movieDto.setId(movie.getId());
			movieDto.setName(movie.getName());
			movieDto.setCast(movie.getCast());
			movieDto.setMovieDescription(movie.getMovieDescription());
			Genre genre = null;
			OkHttpClient client = new OkHttpClient();
			Request request = new Request.Builder().url("http://localhost:8080/genre/" + movie.getGenreId()).build();
			Gson gson = new Gson();
			try (Response response = client.newCall(request).execute()) {
				if (!response.isSuccessful()) {
					throw new RuntimeException("Failed : HTTP error code : " + response.code());
				}
				genre = gson.fromJson(response.body().charStream(), Genre.class);
				movieDto.setGenreId(genre.getId());
				movieDto.setGenre(genre.getName());
				movieDto.setDescription(genre.getDescription());
				

			} catch (Exception e) {
				e.printStackTrace();
			}

			if (movie == null) {
				logger.error("Movie with id {} not found", id);
				throw new MovieNotFoundException("Movie not found with id: " + id);
			}
			return ResponseEntity.ok(movieDto);
		} catch (MovieNotFoundException e) {
			logger.error("Movie not found: ", e);
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			logger.error("Error retrieving movie: ", e);
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
					"Error retrieving movie: " + e.getMessage(), e);
		}
	}

	@Operation(summary = "Get list of the movies", description = "Returns a list of movies from the database, including their genres.", responses = {
			@ApiResponse(responseCode = "200", description = "Successfully retrieved the list of movies", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Movie.class)))),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorDetails.class))) })
	@GetMapping("/movies")
	public ResponseEntity<List<MovieDto>> getAllMovie() {
		logger.info("Fetching all movies");
		List<Movie> movies = movieService.getAllMovie();
		
		
		List<MovieDto> movieDto = new ArrayList<MovieDto>();
		

		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder().url("http://localhost:8080/genre/").build();

		try (Response response = client.newCall(request).execute()) {
			if (!response.isSuccessful()) {
				logger.error("Failed to fetch genres: HTTP error code: {}", response.code());
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
						"Failed to fetch genres: HTTP error code: " + response.code());
			}
			Type genreListType = new TypeToken<ArrayList<Genre>>() {
			}.getType();
			List<Genre> genres = new Gson().fromJson(response.body().charStream(), genreListType);
//			movies.forEach(movie -> movie.setGenre(genres.stream()
//					.filter(genre -> genre.getId().equals(movie.getGenreId())).findFirst().orElse(null)));
			
			for(int i=0;i < movies.size(); i++) {
				MovieDto movie = new MovieDto();
				movie.setId(movies.get(i).getId());
				movie.setGenreId(movies.get(i).getGenreId());
				movie.setName(movies.get(i).getName());
				movie.setCast(movies.get(i).getCast());
				movie.setMovieDescription(movies.get(i).getMovieDescription());
				
				for(int j=0; j<genres.size();j++) {
					if(movies.get(i).getGenreId().toString().equalsIgnoreCase(genres.get(j).getId().toString())){
						movie.setGenre(genres.get(j).getName());
						movie.setDescription(genres.get(j).getDescription());
					}
				}
				movieDto.add(movie);
			}
		} catch (Exception e) {
			logger.error("Error fetching genres: ", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return ResponseEntity.ok(movieDto);
	}

	@Operation(summary = "Update a movie", description = "Updates the specified movie's details in the system.", responses = {
			@ApiResponse(responseCode = "200", description = "The movie was successfully updated", content = @Content(schema = @Schema(implementation = Movie.class))),
			@ApiResponse(responseCode = "404", description = "The movie with the specified ID was not found", content = @Content(schema = @Schema(implementation = ErrorDetails.class))),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorDetails.class))) })
	@PutMapping("/movies/{id}")
	public ResponseEntity<MovieDto> updateMovie(@PathVariable("id") Long id, @RequestBody MovieDto movieDto) {
		logger.info("Updating movie with id: {}", id);
		try {
			Movie movie = new Movie();
			movie.setId(id);
			movie.setGenreId(movieDto.getGenreId());
			movie.setName(movieDto.getName());
			movie.setCast(movieDto.getCast());
			movie.setMovieDescription(movieDto.getMovieDescription());
			
			movieService.updateMovie(id, movie);
			
			
			return ResponseEntity.ok(movieDto);
		} catch (MovieNotFoundException e) {
			logger.error("Movie not found for id: {}", id, e);
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			logger.error("Error updating movie for id: {}: ", id, e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@Operation(summary = "Delete a movie", description = "Deletes the specified movie from the system.", responses = {
			@ApiResponse(responseCode = "200", description = "The movie was successfully deleted"),
			@ApiResponse(responseCode = "404", description = "The movie with the specified ID was not found", content = @Content(schema = @Schema(implementation = ErrorDetails.class))),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorDetails.class))) })
	@DeleteMapping("/movies/{id}")
	public ResponseEntity<Void> deleteMovie(@PathVariable("id") Long id) {
		logger.info("Deleting movie with id: {}", id);
		try {
			movieService.deleteMovie(id);
			return ResponseEntity.ok().build();
		} catch (MovieNotFoundException e) {
			logger.error("Movie not found for id: {}", id, e);
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			logger.error("Error deleting movie for id: {}: ", id, e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@Operation(summary = "Get list of the movies by Genre", description = "Returns a list of movies from the database, including their genres.", responses = {
			@ApiResponse(responseCode = "200", description = "Successfully retrieved the list of movies", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Movie.class)))),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorDetails.class))) })
	@GetMapping("/movies/genre/{id}")
	public ResponseEntity<List<MovieDto>> getAllMovieByGenre(@PathVariable("id") Long genreId) {
		logger.info("Fetching all movies");
		List<Movie> movies = movieService.getMoviesByGenre(genreId);
		List<MovieDto> movieDto = new ArrayList<MovieDto>();
		

		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder().url("http://localhost:8080/genre/").build();

		try (Response response = client.newCall(request).execute()) {
			if (!response.isSuccessful()) {
				logger.error("Failed to fetch genres: HTTP error code: {}", response.code());
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
						"Failed to fetch genres: HTTP error code: " + response.code());
			}
			Type genreListType = new TypeToken<ArrayList<Genre>>() {
			}.getType();
			List<Genre> genres = new Gson().fromJson(response.body().charStream(), genreListType);
//			movies.forEach(movie -> movie.setGenre(genres.stream()
//					.filter(genre -> genre.getId().equals(movie.getGenreId())).findFirst().orElse(null)));
			
			for(int i=0;i < movies.size(); i++) {
				MovieDto movie = new MovieDto();
				movie.setId(movies.get(i).getId());
				movie.setGenreId(movies.get(i).getGenreId());
				movie.setName(movies.get(i).getName());
				movie.setCast(movies.get(i).getCast());
				movie.setMovieDescription(movies.get(i).getMovieDescription());
				
				for(int j=0; j<genres.size();j++) {
					if(movies.get(i).getGenreId().toString().equalsIgnoreCase(genres.get(j).getId().toString())){
						movie.setGenre(genres.get(j).getName());
						movie.setDescription(genres.get(j).getDescription());
					}
				}
				movieDto.add(movie);
			}
		} catch (Exception e) {
			logger.error("Error fetching genres: ", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return ResponseEntity.ok(movieDto);
	}

}
