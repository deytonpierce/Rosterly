package com.deyton.exam.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deyton.exam.models.Team;
import com.deyton.exam.repositories.TeamRepository;

@Service
public class TeamService {

	@Autowired
	TeamRepository tRepo; // Injecting the TeamRepository dependency

	// Retrieves all teams from the database
	public List<Team> getAll() {
		return tRepo.findAll();
	}

	// Creates a new team
	public void createTeam(Team team) {
		tRepo.save(team); // Saves the team to the database
	}

	// Retrieves a team by its ID
	public Team getOneTeam(Long id) {
		Optional<Team> optionalTeam = tRepo.findById(id); // Finds team by ID
		if (optionalTeam.isPresent()) {
			return optionalTeam.get(); // Returns the team if present
		} else {
			return null; // Returns null if team not found
		}
	}

	// Updates an existing team
	public Team update(Team team) {
		return tRepo.save(team); // Saves the updated team to the database
	}

	// Deletes a team by its ID
	public void deleteTeam(Long id) {
		tRepo.deleteById(id); // Deletes the team from the database
	}

}