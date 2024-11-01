package com.deyton.exam.repositories;

import org.springframework.data.repository.ListCrudRepository;

import com.deyton.exam.models.Player;

// Repository interface for managing Player entities
public interface PlayerRepository extends ListCrudRepository<Player, Long> {

}