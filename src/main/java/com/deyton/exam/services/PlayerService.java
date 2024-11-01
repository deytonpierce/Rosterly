package com.deyton.exam.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deyton.exam.models.Player;
import com.deyton.exam.repositories.PlayerRepository;

@Service
public class PlayerService {
	@Autowired
	PlayerRepository pRepo; // Injecting the PlayerRepository dependency

	// Retrieves all players from the database
	public List<Player> getAll() {
		return pRepo.findAll();
	}

	// Creates a new player
	public void createPlayer(Player player) {
		// Ensure player ID is null for new players
		if (player.getId() != null) {
			player.setId(null); // This ensures that a new player gets a new ID
		}
		pRepo.save(player); // Saves the player to the database
	}

	// Retrieves a player by its ID
	public Player getOnePlayer(Long id) {
		Optional<Player> optionalPlayer = pRepo.findById(id); // Finds player by ID
		if (optionalPlayer.isPresent()) {
			return optionalPlayer.get(); // Returns the player if present
		} else {
			return null; // Returns null if player not found
		}
	}

	// Updates an existing player
	public Player update(Player player) {
		return pRepo.save(player); // Saves the updated player to the database
	}

	// Deletes a player by its ID
	public void deletePlayer(Long id) {
		pRepo.deleteById(id); // Deletes the player from the database
	}
}