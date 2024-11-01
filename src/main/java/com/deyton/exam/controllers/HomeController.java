package com.deyton.exam.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.deyton.exam.models.Player;
import com.deyton.exam.models.Team;
import com.deyton.exam.models.User;
import com.deyton.exam.services.PlayerService;
import com.deyton.exam.services.TeamService;
import com.deyton.exam.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class HomeController {

	@Autowired
	UserService users; // Service for user-related operations
	@Autowired
	TeamService teams; // Service for team-related operations
	@Autowired
	PlayerService players; // Service for player-related operations

	// Display the homepage with a list of teams and player counts
	@GetMapping("/teams")
	public String homepage(HttpSession session, Model model) {
		Long userId = (Long) session.getAttribute("userId");
		if (userId == null) {
			return "redirect:/"; // Redirect to the home page if the user is not logged in
		} else {
			List<Team> teamsList = teams.getAll(); // Fetch all teams
			Map<Long, Integer> playerCounts = new HashMap<>();

			for (Team team : teamsList) {
				int playerCount = team.getPlayers() != null ? team.getPlayers().size() : 0;
				playerCounts.put(team.getId(), playerCount); // Count players for each team
			}

			model.addAttribute("user", users.getLoggedInUser(userId)); // Add logged-in user to model
			model.addAttribute("teams", teamsList); // Add list of teams to model
			model.addAttribute("playerCounts", playerCounts); // Add player counts to model
			return "homepage.jsp"; // Return the homepage view
		}
	}

	// Display form to create a new team
	@GetMapping("/new/team")
	public String teamForm(@ModelAttribute("team") Team team, HttpSession session) {
		Long userId = (Long) session.getAttribute("userId");
		if (userId == null) {
			return "redirect:/"; // Redirect to the home page if the user is not logged in
		} else {
			return "teamForm.jsp"; // Return the team form view
		}
	}

	// Handle form submission to create a new team
	@PostMapping("/teams/new/process")
	public String createTeam(@Valid @ModelAttribute("team") Team team, BindingResult result, Model model,
			HttpSession session) {
		Long userId = (Long) session.getAttribute("userId");
		if (userId == null) {
			return "redirect:/"; // Redirect to the home page if the user is not logged in
		}
		if (result.hasErrors()) {
			return "teamForm.jsp"; // Return the form view if there are validation errors
		} else {
			User user = users.getLoggedInUser(userId);
			team.setUser(user); // Set the user who is creating the team
			teams.createTeam(team); // Save the new team
			return "redirect:/teams"; // Redirect to the teams list
		}
	}

	// Display form to edit an existing team
	@GetMapping("/teams/{id}/edit")
	public String editForm(@PathVariable("id") Long id, Model model, HttpSession session) {
		Long userId = (Long) session.getAttribute("userId");
		Team team = teams.getOneTeam(id); // Fetch the team to be edited

		if (userId == null || !userId.equals(team.getUser().getId())) {
			return "redirect:/teams/{id}"; // Redirect if the user is not authorized to edit the team
		}

		model.addAttribute("team", team); // Add the team to the model
		return "editTeam.jsp"; // Return the edit team form view
	}

	// Handle form submission to update an existing team
	@PutMapping("/teams/{id}/edit/process")
	public String updateTeam(@Valid @ModelAttribute("team") Team team, BindingResult result, Model model,
			HttpSession session, RedirectAttributes redirectAttributes) {
		Long userId = (Long) session.getAttribute("userId");
		if (userId == null) {
			return "redirect:/"; // Redirect to the home page if the user is not logged in
		} else if (result.hasErrors()) {
			redirectAttributes.addFlashAttribute("error", "No fields can be blank"); // Add error message to redirect
			model.addAttribute("team", team); // Add team to the model
			return "redirect:/teams/" + team.getId() + "/edit"; // Redirect to the edit page with errors
		} else {
			team.setUser(users.getLoggedInUser(userId)); // Update the team with the logged-in user
			teams.update(team); // Save the updated team
			return "redirect:/teams"; // Redirect to the teams list
		}
	}

	// Handle deletion of a team
	@DeleteMapping("/teams/{id}/delete")
	public String deleteTeam(@PathVariable("id") Long id, HttpSession session) {
		Long userId = (Long) session.getAttribute("userId");
		Team team = teams.getOneTeam(id); // Fetch the team to be deleted

		if (userId == null || !userId.equals(team.getUser().getId())) {
			return "redirect:/teams/{id}"; // Redirect if the user is not authorized to delete the team
		}

		teams.deleteTeam(id); // Delete the team
		return "redirect:/teams"; // Redirect to the teams list
	}

	// Display details of a single team
	@GetMapping("/teams/{id}")
	public String viewTeam(@PathVariable("id") Long id, Model model, HttpSession session,
			@ModelAttribute("player") Player player) {
		Long userId = (Long) session.getAttribute("userId");
		if (userId == null) {
			return "redirect:/"; // Redirect to the home page if the user is not logged in
		} else {
			model.addAttribute("team", teams.getOneTeam(id)); // Add team details to the model
			return "oneTeam.jsp"; // Return the team details view
		}
	}

	// Handle form submission to add a player to a team
	@PostMapping("/teams/{id}/addplayer")
	public String createPlayer(@Valid @ModelAttribute("player") Player player, BindingResult result,
			HttpSession session, @PathVariable("id") Long id, Model model) {
		Long userId = (Long) session.getAttribute("userId");
		if (userId == null) {
			return "redirect:/"; // Redirect to the home page if the user is not logged in
		}

		Team oneTeam = teams.getOneTeam(id); // Fetch the team to which the player will be added
		if (oneTeam.getPlayers().size() >= 9) {
			// Set an error message if the player limit is reached
			result.rejectValue("name", "error.player", "Cannot add more than 9 players to a team.");
		}

		if (result.hasErrors()) {
			model.addAttribute("team", oneTeam); // Add the team to the model if there are errors
			return "oneTeam.jsp"; // Return the team details view with errors
		} else {
			User loggedInUser = users.getLoggedInUser(userId);
			player.setTeam(oneTeam); // Set the team for the player
			player.setUser(loggedInUser); // Set the user who is adding the player
			players.createPlayer(player); // Save the new player
			return "redirect:/teams/" + id; // Redirect to the team details view
		}
	}
}