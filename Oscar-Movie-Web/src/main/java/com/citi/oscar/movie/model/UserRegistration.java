package com.citi.oscar.movie.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserRegistration {
	private String firstName;
	private String lastName;
	private String email;
	private String password;
}
