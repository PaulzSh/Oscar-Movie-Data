package com.citi.genre.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.citi.genre.exception.ErrorDetails;
import com.citi.genre.model.Genre;
import com.citi.genre.service.GenreService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController	
@RequestMapping("/genre") // Consolidated request mapping
@CrossOrigin(origins = "http://localhost:8083") 
@Tag(name = "Genres", description = "Operations related to genres in the Genre Management System")
public class GenreController {
    
    private static final Logger logger = LoggerFactory.getLogger(GenreController.class);
    
    @Autowired
    GenreService genreService;
    
    @PostMapping("/")
    @Operation(summary = "Add a new genre", description = "Creates a new genre in the system and returns it.",
        responses = {
            @ApiResponse(responseCode = "200", description = "The created genre", 
                         content = @Content(schema = @Schema(implementation = Genre.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input", 
                         content = @Content(schema = @Schema(implementation = ErrorDetails.class)))
        })
    public ResponseEntity<Genre> saveGenre(@RequestBody Genre genre) {
        logger.info("Attempting to save genre: {}", genre);
        try {
            Genre savedGenre = genreService.saveGenre(genre);
            return new ResponseEntity<>(savedGenre, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error saving genre: ", e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error saving genre: " + e.getMessage(), e);
        }
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Get a genre by its ID", description = "Fetches a genre by its unique identifier from the system.",
        responses = {
            @ApiResponse(responseCode = "200", description = "The found genre", 
                         content = @Content(schema = @Schema(implementation = Genre.class))),
            @ApiResponse(responseCode = "404", description = "Genre not found",
                         content = @Content(schema = @Schema(implementation = ErrorDetails.class)))
        })
    public ResponseEntity<Genre> getGenre(@PathVariable("id") Integer id) {
        logger.info("Fetching genre with ID: {}", id);
        try {
            Genre genre = genreService.getGenreById(id);
            if (genre == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Genre not found with id: " + id);
            }
            return ResponseEntity.ok(genre);
        } catch (Exception e) {
            logger.error("Error retrieving genre with id {}: ", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @GetMapping("/")
    @Operation(summary = "Get list of all genres", description = "Returns list of all genres in the system.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of genres",
                         content = @Content(array = @ArraySchema(schema = @Schema(implementation = Genre.class))))
        })
    public ResponseEntity<List<Genre>> getAllGenre() {
        logger.info("Fetching all genres");
        List<Genre> genres = genreService.getAllGenre();
        return ResponseEntity.ok(genres);
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Update a genre", description = "Updates the specified genre's details in the system.",
        responses = {
            @ApiResponse(responseCode = "200", description = "The genre was successfully updated",
                         content = @Content(schema = @Schema(implementation = Genre.class))),
            @ApiResponse(responseCode = "404", description = "The genre with the specified ID was not found",
                         content = @Content(schema = @Schema(implementation = ErrorDetails.class)))
        })
    public ResponseEntity<Genre> updateGenre(@PathVariable("id") Integer id, @RequestBody Genre genre) {
        logger.info("Updating genre with ID: {}", id);
        try {
            Genre updatedGenre = genreService.updateGenre(id, genre);
            return ResponseEntity.ok(updatedGenre);
        } catch (Exception e) {
            logger.error("Error updating genre with id {}: ", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a genre", description = "Deletes the specified genre from the system.",
        responses = {
            @ApiResponse(responseCode = "200", description = "The genre was successfully deleted"),
            @ApiResponse(responseCode = "404", description = "The genre with the specified ID was not found",
                         content = @Content(schema = @Schema(implementation = ErrorDetails.class)))
        })
    public ResponseEntity<Void> deleteGenre(@PathVariable("id") Integer id) {
        logger.info("Deleting genre with ID: {}", id);
        try {
            genreService.deleteGenre(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            logger.error("Error deleting genre with id {}: ", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
