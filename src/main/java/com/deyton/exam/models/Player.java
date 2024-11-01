package com.deyton.exam.models;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "players") // Specifies the name of the table in the database
public class Player {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generates the ID
	private Long id;

	@NotEmpty(message = "Name is required!") // Ensures the name field is not empty
	private String name;

	@Column(updatable = false) // Indicates that this field should not be updated after creation
	@DateTimeFormat(pattern = "yyyy-MM-dd") // Formats the date for display
	private Date createdAt;

	@DateTimeFormat(pattern = "yyyy-MM-dd") // Formats the date for display
	private Date updatedAt;

	// Automatically sets the createdAt field to the current date when the entity is
	// first persisted
	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}

	// Automatically sets the updatedAt field to the current date when the entity is
	// updated
	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}

	@ManyToOne(fetch = FetchType.LAZY) // Many players can be associated with one user
	@JoinColumn(name = "user_id") // Foreign key column in the players table
	private User user;

	@ManyToOne(fetch = FetchType.LAZY) // Many players can be associated with one team
	@JoinColumn(name = "team_id") // Foreign key column in the players table
	private Team team;

	// Default constructor
	public Player() {
	}

	// Getter and setter for id
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	// Getter and setter for name
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	// Getter and setter for createdAt
	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	// Getter and setter for updatedAt
	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	// Getter and setter for user
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	// Getter and setter for team
	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}
}