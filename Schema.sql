-- Assuming a single database for simplicity
CREATE DATABASE IF NOT EXISTS oscar_movies;
USE oscar_movies;

-- Drop existing tables if they exist to recreate them
DROP TABLE IF EXISTS movies;
DROP TABLE IF EXISTS genres;

DROP TABLE IF EXISTS role;
DROP TABLE IF EXISTS my_user;
DROP TABLE IF EXISTS users_roles;

-- Genre Table
CREATE TABLE IF NOT EXISTS genres (
    genre_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    UNIQUE KEY unique_genre_name (name)
);

-- Movie Table, now including movie_description
CREATE TABLE IF NOT EXISTS movies (
    movie_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    cast TEXT NOT NULL,
    genre_id INT,
    movie_description TEXT NOT NULL,
    FOREIGN KEY (genre_id) REFERENCES genres(genre_id)
);


CREATE TABLE role (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);
CREATE TABLE my_user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);
CREATE TABLE users_roles (
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES my_user(id),
    FOREIGN KEY (role_id) REFERENCES role(id)
);
INSERT INTO role (name) VALUES ('ROLE_USER');