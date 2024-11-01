package com.deyton.exam.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class LoggedInUser {
	@NotEmpty(message = "Email is required!") // Ensures the email field is not empty
	@Email(message = "Please enter a valid email!") // Validates that the email is in the correct format
	private String email;

	@NotEmpty(message = "Password is required!") // Ensures the password field is not empty
	@Size(min = 8, max = 128, message = "Password must be between 8 and 128 characters") // Validates the password
																							// length
	private String password;

	// Default constructor
	public LoggedInUser() {
	}

	// Getter for email
	public String getEmail() {
		return email;
	}

	// Setter for email
	public void setEmail(String email) {
		this.email = email;
	}

	// Getter for password
	public String getPassword() {
		return password;
	}

	// Setter for password
	public void setPassword(String password) {
		this.password = password;
	}
}