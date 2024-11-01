package com.deyton.exam.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.deyton.exam.models.LoggedInUser;
import com.deyton.exam.models.User;
import com.deyton.exam.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepo; // Injecting the UserRepository dependency

	// Registers a new user, performs validation, and saves to the database
	public User register(User newUser, BindingResult result) {

		// Check if a user with the same email already exists
		Optional<User> potentialUser = userRepo.findByEmail(newUser.getEmail());
		if (potentialUser.isPresent()) {
			result.rejectValue("email", "Matches", "An Account already exists please login");
		}

		// Validate that password and confirm password match
		if (!newUser.getPassword().equals(newUser.getConfirm())) {
			result.rejectValue("confirm", "Matches", "Passwords don't match");
		}

		// Return null if there are validation errors
		if (result.hasErrors()) {
			return null;
		}

		// Hash the password before saving the user
		String hashedPassword = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
		newUser.setPassword(hashedPassword);
		return userRepo.save(newUser); // Save the new user to the database
	}

	// Authenticates a user based on email and password
	public User login(LoggedInUser newLoginObject, BindingResult result) {

		// Find user by email
		Optional<User> potentialUser = userRepo.findByEmail(newLoginObject.getEmail());
		if (!potentialUser.isPresent()) {
			result.rejectValue("email", "Matches", "Email not found try registering");
			return null;
		}
		User user = potentialUser.get();

		// Validate password
		if (!BCrypt.checkpw(newLoginObject.getPassword(), user.getPassword())) {
			result.rejectValue("password", "Matches", "Invalid login attempt try again");
		}
		// Return user if login is successful
		if (result.hasErrors()) {
			return null;
		} else {
			return user;
		}

	}

	// Retrieves a user by their ID
	public User getLoggedInUser(Long id) {
		Optional<User> potentialUser = userRepo.findById(id);
		if (potentialUser.isPresent()) {
			return potentialUser.get(); // Return the user if found
		}
		return null; // Return null if user is not found
	}

}