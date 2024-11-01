package com.deyton.exam.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.deyton.exam.models.LoggedInUser;
import com.deyton.exam.models.User;
import com.deyton.exam.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class UserController {
	@Autowired
	UserService users; // Service for user-related operations

	// Display the main page with registration and login forms
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("newUser", new User()); // Add a new User object to the model for registration
		model.addAttribute("newLogin", new LoggedInUser()); // Add a new LoggedInUser object to the model for login
		return "index.jsp"; // Return the index view
	}

	// Handle user registration
	@PostMapping("/register/user")
	public String registerUser(@Valid @ModelAttribute("newUser") User newUser, BindingResult result,
			HttpSession session, Model model) {
		users.register(newUser, result); // Call the service to register the user
		if (result.hasErrors()) {
			model.addAttribute("newLogin", new LoggedInUser()); // If there are errors, add a new LoggedInUser object to
																// the model
			return "index.jsp"; // Return the index view with errors
		} else {
			session.setAttribute("userId", newUser.getId()); // Set the user ID in the session
			return "redirect:/teams"; // Redirect to the teams page after successful registration
		}
	}

	// Handle user login
	@PostMapping("/login/user")
	public String loginUser(@Valid @ModelAttribute("newLogin") LoggedInUser newLogin, BindingResult result,
			HttpSession session, Model model) {
		User user = users.login(newLogin, result); // Call the service to log in the user
		if (result.hasErrors()) {
			model.addAttribute("newUser", new User()); // If there are errors, add a new User object to the model
			return "index.jsp"; // Return the index view with errors
		} else {
			session.setAttribute("userId", user.getId()); // Set the user ID in the session
			System.out.println(user.getId()); // Print the user ID (for debugging purposes)
			return "redirect:/teams"; // Redirect to the teams page after successful login
		}
	}

	// Handle user logout
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate(); // Invalidate the session to log out the user
		return "redirect:/"; // Redirect to the home page
	}
}