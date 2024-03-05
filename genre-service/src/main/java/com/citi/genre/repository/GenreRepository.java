package com.citi.genre.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.citi.genre.model.Genre;


public interface GenreRepository extends CrudRepository<Genre, Integer> {
}
