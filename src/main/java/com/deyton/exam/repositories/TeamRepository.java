package com.deyton.exam.repositories;

import org.springframework.data.repository.ListCrudRepository;

import com.deyton.exam.models.Team;

// Repository interface for managing Team entities
public interface TeamRepository extends ListCrudRepository<Team, Long> {

}